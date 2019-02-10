package com.alevel.hw7.Part4;

///multiply In AKaratsuba№1
public class Main {
    public static void main(String[] args) {
        int a = 15654, b = 22546;
        System.out.println("result [" + a + "]*[" + b + "]: " + multiplyInAKaratsuba(a, b));
    }

    //////////////////////////////////////////
    ///multiply In AKaratsuba
    public static int multiplyInAKaratsuba(int multiplicand, int multiplier) {
        if (multiplicand == 0 || multiplier == 0) {
            return 0;
        }
        System.out.println("A:"+multiplicand + "\nB:" + multiplier+"\n");
        int result = 0;
        int resultA0B0 = 0;
        int resultA1B1 = 0;
        int resultA1B0A0B1=0;
        int[] a = new int[2];
        int[] b = new int[2];

        a[0] = multiplicand > 9 ? multiplicand / extent10(lengthNum(multiplicand) / 2) : 0;
        a[1] = multiplicand < 10 ? multiplicand : multiplicand - a[0] * extent10(lengthNum(multiplicand) / 2);
        b[0] = multiplier > 9 ? multiplier / extent10(lengthNum(multiplier) / 2) : 0;
        b[1] = multiplier < 10 ? multiplier : multiplier - b[0] * extent10(lengthNum(multiplier) / 2);
        System.out.println("A0:"+a[0] + " A1:" + a[1]);
        System.out.println("B0:"+b[0] + " B1:" + b[1]+"\n");

        if ((a[0] > 9 || a[1] > 9) && (b[0] > 9 || b[1] > 9)) {
            resultA0B0 = multiplyInAKaratsuba(a[0], b[0]);
            resultA1B1 = multiplyInAKaratsuba(a[1], b[1]);
            resultA1B0A0B1=(a[1] * b[0]) * extent10(lengthNum(b[1])) + (a[0] * b[1]) * extent10(lengthNum(a[1]));
            result = resultA0B0 * extent10(lengthNum(a[1]) + lengthNum(b[1])) + resultA1B1 + resultA1B0A0B1;
            System.out.println("result:" + result + " resultA0B0:" + resultA0B0* extent10(lengthNum(resultA1B1)) + " resultA1B1:" + resultA1B1 +" resultA1B0A0B1:"+ resultA1B0A0B1);
        } else {
            result = Integer.valueOf(a[0] * b[0] * extent10(lengthNum(a[1]) + lengthNum(b[1])) + a[1] * b[1]) + (a[1] * b[0]) * extent10(lengthNum(b[1])) + (a[0] * b[1]) * extent10(lengthNum(a[1]));
            System.out.println("result:" + result);
        }

        return result;
    }

    /////////////////////////////////////////
    /// 10 extent x
    public static int extent10(int x) {
        int result = 1;
        if (x >= 1) {
            for (int i = 0; i < x; i++) {
                result *= 10;
            }
        }
        return result;
    }

    public static int lengthNum(int number) {
        return String.valueOf(number).length();
    }
}
