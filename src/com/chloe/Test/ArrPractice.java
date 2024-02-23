package com.chloe.Test;

import java.util.Random;

//shuffle the data in one-dimensional array and add them to a two-dimensional array in groups of 4.
public class ArrPractice {
    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        //shuffle the data
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int index = r.nextInt(arr.length);
            arr[i] = arr[index];
            arr[index] = temp;
        }
        //check the effect of the shuffle function.
//printArr(arr);
        //generate a two-dimensional array
        int[][] arrTwo = new int[4][4];


        for (int i = 0; i < arr.length; i++) {

                arrTwo[i/4][i%4] = arr[i];

        }
        //check the function
//printArrTwo(arrTwo);
    }

    //generate function to print array to check effect of code.
    public static void printArr(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    //print two-dimensional array to check effect of code.
    public static void printArrTwo(int[][] arr) {
        System.out.println();
        for (int i = 0; i < 4; i++) {
            for (int a = 0; a < 4; a++) {
                System.out.print(arr[i][a] + " ");
            }
            System.out.println();
        }
    }

}
