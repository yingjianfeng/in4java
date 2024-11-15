package com.in4java.base;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: yingjf
 * @date: 2024/10/29 10:08
 * @description:
 */
public class leetcode {

    public static void main(String[] args) {

        countKConstraintSubstrings("10110011001010",2,new int[][]{
                new int[]{5,11},new int[]{1,7}
        });
    }


    public static long[] countKConstraintSubstrings(String s, int k, int[][] queries) {
        long[] ans = new long[queries.length];
        Map<String, Long> map = new HashMap<>();
        for (int i = 0; i < queries.length; i++) {
            int l = queries[i][0];
            int r = queries[i][1];  // 5,11] 011001  11 [1,7],011001
            System.out.println(s.substring(l,r));
            /*if(map.containsKey(s.substring(l,r))){
                ans[i] = map.get(s.substring(l,r));
                continue;
            }*/
            long value = 0l;
            for (int j = l; j <= r; j++) {
                int one = 0;
                int zero = 0;
                for (int q = j; q <= r; q++) {
                    if (s.charAt(q) == '1') one++;
                    if (s.charAt(q) == '0') zero++;
                    if(s.substring(j,q).equals("11001")){
                        System.out.print(one+" "+zero);
                    }

                    if (one<=k || zero<=k){
                        value++;
                    }else{
                        break;
                    }
                }
            }
            System.out.println(" ");
            ans[i] = value;
            map.put(s.substring(l,r), value);
        }
        return ans;
    }




}
