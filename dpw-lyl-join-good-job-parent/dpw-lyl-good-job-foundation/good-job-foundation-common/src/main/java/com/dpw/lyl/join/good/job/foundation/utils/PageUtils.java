package com.dpw.lyl.join.good.job.foundation.utils;

import com.dpw.lyl.join.good.job.foundation.utils.sql.SqlUtil;
import com.dpw.lyl.join.good.job.foundation.web.page.PageDomain;
import com.dpw.lyl.join.good.job.foundation.web.page.TableSupport;
import com.github.pagehelper.PageHelper;

/**
 * 分页工具类
 *
 * @author ruoyi
 */
public class PageUtils extends PageHelper {
    /**
     * 设置请求分页数据
     */
    public static void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
        Boolean reasonable = pageDomain.getReasonable();
        PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
    }

    /**
     * 清理分页的线程变量
     */
    public static void clearPage() {
        PageHelper.clearPage();
    }
}
