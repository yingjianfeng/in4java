package com.in4java;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>>  ans = new ArrayList<>();
        ans.add(Arrays.asList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> temp = new ArrayList<>();
            temp.add(1);
            List<Integer> lastList = ans.get(i - 1);
            if(lastList.size()>1){
                for (int j = 1; j < i; j++) {
                    temp.add(lastList.get(j-1)+lastList.get(j));
                }
            }
            temp.add(1);
            ans.add(temp);
        }
        return ans;
    }

    public static void main(String[] args) {
        Main main = new Main();

        System.out.println(main.generate(5));
    }
}



//HELLONowcoder123
//o