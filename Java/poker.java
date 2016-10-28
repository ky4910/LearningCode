package com.company;

import java.util.Scanner;

public class Main
{
    public static void main(String[] args){
        //write code here
        Scanner scan = new Scanner(System.in);
        int groups = scan.nextInt();

        while(groups-- > 0){
            int n = scan.nextInt();
            int k = scan.nextInt();
            int[] res = new int[2*n];

            for(int i = 0; i < 2*n; i++){
                int tmp = i + 1;
                for(int j = 0; j < k; j++){
                    if(tmp <= n){
                        tmp = 2 * tmp -1;
                    }
                    else{
                        tmp = 2 * (tmp - n);
                    }
                }
                res[tmp-1] = scan.nextInt();
            }
            if(res.length > 0){
                System.out.print(res[0]);
            }
            for(int i = 1; i < 2*n; i++){
                System.out.print(" " + res[i]);
            }
            System.out.println();
        }
     }
}

