package com.in4java.base;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: yingjf
 * @date: 2025/6/7 22:31
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.lexicalOrder(13);
    }
    public List<Integer> lexicalOrder(int n) {
        List<Integer> list = new ArrayList<>();
        for(int i=1;i<=n;i++){
            list.add(i);
        }
        list.sort((o1, o2) -> {
            char[] arr1 = o1.toString().toCharArray();
            char[] arr2 = o2.toString().toCharArray();
            int i=0;
            while (true){
                if(arr1.length>=i+1 && arr2.length<i+1) return 1;
                if(arr2.length>=i+1 && arr1.length<i+1) return -1;
                if(arr1[i]>arr2[i]) return 1;
                else if(arr1[i]<arr2[i]) return -1;
                i++;
            }
        });
        return list;
    }


}
