package com.company;

import java.util.Scanner;

public class Main
{
    public int maxProfit(int[] prices, int n){
        int i = 0, j = 0;
        int max = 0;
        int temp1 = 0, temp2 = 0, l = 0;

        while(l < n){
            //前半部分
            for(i = 0; i < l+1; i++){
                for(j = i+1; j < l+1; j++){
                    if(prices[j]-prices[i] > temp1){
                        temp1 = prices[j] - prices[i];
                    }
                }
            }
            //后半部分
            for(i = l+1; i < n; i++){
                for(j = i+1; j < n; j++){
                    if(prices[j]-prices[i] > temp2){
                        temp2 = prices[j] - prices[i];
                    }
                }
            }

            if(temp1+temp2 > max){
                max = temp1 + temp2;
            }

            temp1 = 0;
            temp2 = 0;
            l++;
        }
        return max;
    }

    public static void main(String[] args){
        //write code here
        String str = null;
        int[] num = new int[256];
        String[] tmp = null;
        Main tst = new Main();

        Scanner scan = new Scanner(System.in);

        while(scan.hasNext()){
            str = scan.nextLine();
            tmp = str.split(",");
            for(int i = 0; i < tmp.length; i++){
                num[i] = Integer.parseInt(tmp[i]);
            }
            int res = tst.maxProfit(num, num.length);
            System.out.println(res);
        }
//        System.out.println(tst.maxProfit(num,num.length));
    }
}


