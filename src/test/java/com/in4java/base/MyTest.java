package com.in4java.base;

/**
 * @author: yingjf
 * @date: 2024/10/23 10:24
 * @description:
 */
public class MyTest {

    public static void main(String[] args) {
        String corpid = "xbx$$123";
        System.out.println("main1:hashcode:"+corpid.hashCode());
        getAccessToken(corpid);
        System.out.println(corpid);
        System.out.println("main2:hashcode:"+corpid.hashCode());
    }

    static void getAccessToken(String str){
        System.out.println("getAccessToken1:hashcode:"+str.hashCode());
        str = setValue(str);
        removeValue(str);
        System.out.println("getAccessToken2:hashcode:"+str.hashCode());
    }
    static String setValue(String str){
        System.out.println("setValue1:hashcode:"+str.hashCode());
        str = str.replace("xbx$$", "");
        System.out.println("setValue2:hashcode:"+str.hashCode());
        return str;
    }

    static void removeValue(String str){
        str = "xbx$$"+str;
        System.out.println("removeValue:hashcode:"+str.hashCode());
    }
}
