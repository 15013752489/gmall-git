package com.atguigu.gmall.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description:
 * ClassName: LoginRequired
 * date: 2019/12/13 0:55
 *
 * @author fancy Email:395765197@qq.com
 * @version 1.0
 * @since JDK 1.8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginRequired {
    boolean isMustSuccess() default true;
}
