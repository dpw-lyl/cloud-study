package com.dpw.lyl.join.good.job.foundation.security.annotation;

import lombok.Getter;

/**
 * @Author: dengpw
 * @createTime: 2023年01月04日 16:32:18
 * @version: 1.0.0
 * @Description: 脱敏类型
 */
@Getter
public enum PrivacyTypeEnum {

    /** 自定义（此项需设置脱敏的范围）*/
    CUSTOMER,

    /** 姓名 */
    NAME,

    /** 身份证号 */
    ID_CARD,

    /** 手机号 */
    PHONE,

    /** 邮箱 */
    EMAIL,
}