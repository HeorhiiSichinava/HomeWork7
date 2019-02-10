package com.alevel.hw7.Part1;

import java.util.Scanner;
//binary search
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] array = { 14, 15, 16, 25, 26, 27, 28, 29, 30};
        System.out.println("enter number from 14 to 30");
        int k = scanner.nextInt();
        System.out.println("found the number ["+k+"] under the index: -> [" + binFind(array, k, 0, array.length-1)+"]");
    }

    public static String binFind(int[] array, int k, int leftPoint, int rightPoint) {
        int index = divisionBy2((leftPoint - rightPoint));
        String result = "";
        if (array[leftPoint + index] != k && Math.abs(leftPoint-rightPoint)>0) {
            if (array[leftPoint + index] > k) {
                result = String.valueOf(binFind(array, k, leftPoint, rightPoint - index));
            } else if (array[leftPoint + index] < k) {
                result = String.valueOf(binFind(array, k, leftPoint + index + 1, rightPoint));
            }
        } else if (array[leftPoint + index] == k) {
            return result = "" + (leftPoint + index);
        }
        else if (array[leftPoint+index]!=k && Math.abs(leftPoint-rightPoint)==0){
            return "this number is not found";
        }
        return result;
    }

    public static int divisionBy2(int xNumber) {
        return xNumber = Math.abs(xNumber / 2 + xNumber % 2);
    }
}
