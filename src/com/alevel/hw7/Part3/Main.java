package com.alevel.hw7.Part3;
////////////////////////////
//multiply In A Column #2
public class Main {
    public static void main(String[] args) {
        System.out.println("result: " + multiplyInAColumn(28951, 1596) + " <-multiply in a column");
    }
    ////////////////////////////
    //multiply In A Column
    public static int multiplyInAColumn(int multiplicand, int multiplier) {
        int summFirst = 0;
        int summNext = 0;
        int addZero = 1;
        int addZero2 = 1;
        System.out.println(multiplicand + "\n*\n" + multiplier + "\n------------------------");
        for (int i = lengthNumber(multiplier) - 1; i >= 0; i--) {
            summNext = 0;
            for (int j = lengthNumber(multiplicand) - 1; j >= 0; j--) {
                summNext += numberDigit(multiplicand, j) * numberDigit(multiplier, i) * addZero;
                addZero *= 10;
            }
            if (i != lengthNumber(multiplier) - 1)
                System.out.println("+");
            addZero = 1;
            summFirst += summNext * addZero2;
            addZero2 *= 10;
            System.out.println(summNext * addZero2);
        }
        System.out.println("------------------------");
        return summFirst;
    }
    ////////////////////////////
    //length Number
    public static int lengthNumber(int number) {
        int lNumber = 0;
        number = Math.abs(number);
        while (number >= 0) {
            lNumber += number > 0 ? 1 : 0;
            number /= 10;
            if(number==0)break;
        }
        return lNumber;
    }

    public static int numberDigit(int number, int index) {
        String[] indexNumber = String.valueOf(number).split("");
        return Integer.valueOf(indexNumber[index]);
    }
}
