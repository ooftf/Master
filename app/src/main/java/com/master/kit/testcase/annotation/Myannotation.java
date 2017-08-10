package com.master.kit.testcase.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.master.kit.R.color.red;

/**
 * Created by master on 2016/11/29.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Myannotation {
    int sssssss() default -1;
}
