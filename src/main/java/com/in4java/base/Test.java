package com.in4java.base;

import java.util.Arrays;

/**
 * @author: yingjf
 * @date: 2024/3/13 19:07
 * @description:
 */
public class Test {
    public static void main(String[] args) {
        int[][] arr = new int[5][3];
        arr[0] = new int[]{2,3,4};
        arr[1] = new int[]{5,6,7};
        arr[2] = new int[]{8,9,10};
        arr[3] = new int[]{11,12,13};
        arr[4] = new int[]{14,15,16};
        int[][] ints = imageSmoother(arr);
        System.out.println(Arrays.toString(ints));
    }

    public static int[][] imageSmoother(int[][] img) {
        int m = img.length;   // 竖
        int n = img[0].length;  // 横
        int[][] res = new int[m][n];
        for(int i = 0; i < n; i++){   // 横
            for(int k = 0; k < m; k++){  // 竖
                res[k][i] = getAvg(img,i,k);
            }
        }
        return res;
    }

    public static int getAvg(int[][] img, int i, int k){
        int[] img1 = get(img,i-1,k-1);
        int[] img2 = get(img,i-1,k);
        int[] img3 = get(img,i-1,k+1);
        int[] img4 = get(img,i,k-1);
        int[] img5 = get(img,i,k);
        int[] img6 = get(img,i,k+1);
        int[] img7 = get(img,i+1,k-1);
        int[] img8 = get(img,i+1,k);
        int[] img9 = get(img,i+1,k+1);
        int value = img1[0]+img2[0]+img3[0]+img4[0]+img5[0]+img6[0]+img7[0]+img8[0]+ img9[0];
        int times = img1[1]+img2[1]+img3[1]+img4[1]+img5[1]+img6[1]+img7[1]+img8[1]+img9[1];
        if(times==0){
            return 0;
        }
        return value/times ;
    }

    public static int[] get(int[][] img, int k, int i){
        int[] res = new int[2];
        int m = img.length;   // 竖
        int n = img[0].length;  // 横
        if(i<0||i>=m-1){
            return res;
        }
        if(k<0||k>=n-1){
            return res;
        }
        System.out.println("i:"+i+" k:"+k +" m:"+m+" n:"+n);
        res[0]=img[i][k];
        res[1]=1;
        return res;
    }



}



class NumArray {
    private int[] t;
    public NumArray(int[] nums) {
        Arrays.sort(nums);
    }

    public int sumRange(int left, int right) {
        int sum = 0;
        for(int i=0;i<t.length;i++){
            if(left>=i&&right<=i){
                sum+=t[i];
            }
        }
        return sum;
    }
}
