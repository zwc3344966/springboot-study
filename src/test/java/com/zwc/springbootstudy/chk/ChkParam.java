package com.zwc.springbootstudy.chk;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ChkParam {
    /**
     * 检查参数属性名称
     * @return
     */
    public String name() default "";

    /**
     * 参数类型
     * @return
     */
    public String type() default "";

    /**
     * 备用字段
     * @return
     */
    public String dac() default "";
}
