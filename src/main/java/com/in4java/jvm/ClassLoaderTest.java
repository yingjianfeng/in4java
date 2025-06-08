package com.in4java.jvm;

import org.junit.Test;

/**
 * @author: yingjf
 * @date: 2025/5/28 09:16
 * @description: 类加载器
 */

public class ClassLoaderTest {

    @Test
    public void test(){
        // 输出为 null，说明该类是由启动类加载器加载的。
        ClassLoader stringClassLoader = String.class.getClassLoader();
        System.out.println(stringClassLoader);
        // 应用类加载器  sun.misc.Launcher$AppClassLoader
        System.out.println(ClassLoader.getSystemClassLoader().getClass());
        // class sun.misc.Launcher$ExtClassLoader
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getClass());
        System.out.println(ClassLoader.getSystemClassLoader().getParent().getParent());
    }


}
