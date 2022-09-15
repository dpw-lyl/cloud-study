package com.dpw.lyl.join.good.job.system.server.controller;

import com.alibaba.fastjson.JSONObject;
import com.dpw.lyl.join.good.job.foundation.MsgResponse;
import com.dpw.lyl.join.good.job.foundation.constant.UserConstants;
import com.dpw.lyl.join.good.job.foundation.domain.R;
import com.dpw.lyl.join.good.job.foundation.domain.login.LoginUser;
import com.dpw.lyl.join.good.job.foundation.domain.system.SysFile;
import com.dpw.lyl.join.good.job.foundation.domain.system.SysUser;
import com.dpw.lyl.join.good.job.foundation.log.annotation.Log;
import com.dpw.lyl.join.good.job.foundation.log.enums.BusinessType;
import com.dpw.lyl.join.good.job.foundation.security.service.TokenService;
import com.dpw.lyl.join.good.job.foundation.security.utils.SecurityUtils;
import com.dpw.lyl.join.good.job.foundation.utils.StringUtils;
import com.dpw.lyl.join.good.job.foundation.utils.file.FileTypeUtils;
import com.dpw.lyl.join.good.job.foundation.utils.file.MimeTypeUtils;
import com.dpw.lyl.join.good.job.foundation.web.controller.BaseController;
import com.dpw.lyl.join.good.job.foundation.common.api.RemoteFileService;
import com.dpw.lyl.join.good.job.system.server.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

/**
 * 个人信息 业务处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/user/profile")
public class SysProfileController extends BaseController {
    @Autowired
    private ISysUserService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RemoteFileService remoteFileService;

    /**
     * 个人信息
     */
    @GetMapping
    public MsgResponse profile() {
        String username = SecurityUtils.getUsername();
        SysUser user = userService.selectUserByUserName(username);
        JSONObject ajax=new JSONObject();
        ajax.put("user",user);
        ajax.put("roleGroup", userService.selectUserRoleGroup(username));
        ajax.put("postGroup", userService.selectUserPostGroup(username));
        return MsgResponse.buildSuccess(ajax);
    }

    /**
     * 修改用户
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public MsgResponse updateProfile(@RequestBody SysUser user) {
        LoginUser loginUser = SecurityUtils.getLoginUser();
        SysUser sysUser = loginUser.getSysUser();
        user.setUserName(sysUser.getUserName());
        if (StringUtils.isNotEmpty(user.getPhonenumber())
                && UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            return MsgResponse.buildFail("修改用户'" + user.getUserName() + "'失败，手机号码已存在");
        } else if (StringUtils.isNotEmpty(user.getEmail())
                && UserConstants.NOT_UNIQUE.equals(userService.checkEmailUnique(user))) {
            return MsgResponse.buildFail("修改用户'" + user.getUserName() + "'失败，邮箱账号已存在");
        }
        user.setUserId(sysUser.getUserId());
        user.setPassword(null);
        user.setAvatar(null);
        user.setDeptId(null);
        if (userService.updateUserProfile(user) > 0) {
            // 更新缓存用户信息
            loginUser.getSysUser().setNickName(user.getNickName());
            loginUser.getSysUser().setPhonenumber(user.getPhonenumber());
            loginUser.getSysUser().setEmail(user.getEmail());
            loginUser.getSysUser().setSex(user.getSex());
            tokenService.setLoginUser(loginUser);
            return MsgResponse.buildSuccess();
        }
        return MsgResponse.buildFail("修改个人信息异常，请联系管理员");
    }

    /**
     * 重置密码
     */
    @Log(title = "个人信息", businessType = BusinessType.UPDATE)
    @PutMapping("/updatePwd")
    public MsgResponse updatePwd(String oldPassword, String newPassword) {
        String username = SecurityUtils.getUsername();
        SysUser user = userService.selectUserByUserName(username);
        String password = user.getPassword();
        if (!SecurityUtils.matchesPassword(oldPassword, password)) {
            return MsgResponse.buildFail("修改密码失败，旧密码错误");
        }
        if (SecurityUtils.matchesPassword(newPassword, password)) {
            return MsgResponse.buildFail("新密码不能与旧密码相同");
        }
        if (userService.resetUserPwd(username, SecurityUtils.encryptPassword(newPassword)) > 0) {
            // 更新缓存用户密码
            LoginUser loginUser = SecurityUtils.getLoginUser();
            loginUser.getSysUser().setPassword(SecurityUtils.encryptPassword(newPassword));
            tokenService.setLoginUser(loginUser);
            return MsgResponse.buildSuccess();
        }
        return MsgResponse.buildFail("修改密码异常，请联系管理员");
    }

    /**
     * 头像上传
     */
    @Log(title = "用户头像", businessType = BusinessType.UPDATE)
    @PostMapping("/avatar")
    public MsgResponse avatar(@RequestParam("avatarfile") MultipartFile file) {
        if (!file.isEmpty()) {
            LoginUser loginUser = SecurityUtils.getLoginUser();
            String extension = FileTypeUtils.getExtension(file);
            if (!StringUtils.equalsAnyIgnoreCase(extension, MimeTypeUtils.IMAGE_EXTENSION)) {
                return MsgResponse.buildFail("文件格式不正确，请上传" + Arrays.toString(MimeTypeUtils.IMAGE_EXTENSION) + "格式");
            }
            MsgResponse<SysFile> fileResult = remoteFileService.upload(file);
            if (StringUtils.isNull(fileResult) || StringUtils.isNull(fileResult.getData())) {
                return MsgResponse.buildFail("文件服务异常，请联系管理员");
            }
            String url = fileResult.getData().getUrl();
            if (userService.updateUserAvatar(loginUser.getUsername(), url)) {
                JSONObject ajax = new JSONObject();
                ajax.put("imgUrl", url);
                // 更新缓存用户头像
                loginUser.getSysUser().setAvatar(url);
                tokenService.setLoginUser(loginUser);
                return MsgResponse.buildSuccess(ajax);
            }
        }
        return MsgResponse.buildFail("上传图片异常，请联系管理员");
    }
}
