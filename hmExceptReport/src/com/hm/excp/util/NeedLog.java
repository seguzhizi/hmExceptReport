package com.hm.excp.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NeedLog {

    String logMsg();
    //模块名  
//    String moduleName();  
    //操作内容  
//    String option();
    
}
