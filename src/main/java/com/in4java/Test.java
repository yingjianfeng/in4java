package com.in4java;

import java.nio.CharBuffer;
import java.util.*;



public class Test {

    public static void main(String[] args) {
        Test test = new Test();


    }

    public boolean wordBreak(String s, List<String> wordDict) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if(wordDict.contains(sb.toString())) sb =  new StringBuffer();
        }
        return sb.length()==0;
    }



}


