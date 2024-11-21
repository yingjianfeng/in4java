package com.in4java.leetcode;

/**
 * @author: yingjf
 * @date: 2024/10/31 16:04
 * @description:
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        System.out.println(oddCells(2,3,new int[][]{new int[]{0,1},new int[]{1,1}} ));
    }


    public static int oddCells(int m, int n, int[][] indices) {
        int ans = 0;
        int[][] arr = new int[m][n];
        for (int i = 0; i <indices.length; i++) {
            int a = indices[i][0];
            int b = indices[i][1];
            for (int j = 0; j < n; j++) {
                indices[a][j] += 1;
                if(indices[a][j]%2==1)ans++;
            }
            for (int k = 0; k <m ; k++) {
                indices[k][b] += 1;
                if(indices[k][b]%2==1)ans++;
            }
        }
        return ans;
    }
}
