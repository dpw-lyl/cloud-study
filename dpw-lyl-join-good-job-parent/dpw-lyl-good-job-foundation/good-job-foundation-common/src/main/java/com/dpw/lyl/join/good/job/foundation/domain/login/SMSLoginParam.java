package com.dpw.lyl.join.good.job.foundation.domain.login;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * @Author: dengpw
 * @createTime: 2022年09月22日 11:02:40
 * @version: 1.0.0
 * @Description: 移动端（短信登录）登录入参
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class SMSLoginParam {

    /**
     * 短信验证码
     */
    @NotBlank(message = "短信验证码不能为空")
    private String smsCode;

    /**
     * 登录手机号
     */
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = "^[1][3,4,5,7,8,9][0-9]{9}$", message = "绑定手机号格式不正确")
    private String phoneNumber;


    /**
     * 商户Id
     */
    @NotBlank
    @JsonSerialize(using = ToStringSerializer.class)
    private Long merchantId;

    /**
     * 客户端
     */
    private String clientId;

    /**
     * 微信授权码
     */
    private String code;

}
