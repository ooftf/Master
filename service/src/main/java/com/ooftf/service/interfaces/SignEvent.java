package com.ooftf.service.interfaces;

import android.support.annotation.StringDef;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ooftf
 * @email 994749769@qq.com
 * @date 2018/10/22 0022
 */
@Documented // 表示开启Doc文档
@StringDef({
        SignService.EVENT_LOGIN_FAIL,
        SignService.EVENT_LOGIN_SUCCESS
}) //限定为 FlagContants.OK, FlagContants.ERROR
@Target({
        ElementType.PARAMETER,
        ElementType.FIELD,
        ElementType.METHOD,
}) //表示注解作用范围，参数注解，成员注解，方法注解
@Retention(RetentionPolicy.SOURCE) //表示注解所存活的时间,在运行时,而不会存在 .class 文件中
public @interface SignEvent { //接口，定义新的注解类型
}