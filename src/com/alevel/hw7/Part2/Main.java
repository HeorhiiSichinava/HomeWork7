package com.alevel.hw7.Part2;
////////////////////////////
//multiply In A Column #1
public class Main {
    public static void main(String[] args) {
        int x=1026884407;
        System.out.println(x+" "+lengthNumber(x));
        multiplyInAColumn(28951, 1596);
    }
    ////////////////////////////
    //multiply In A Column
    public static int multiplyInAColumn(int multiplicand, int multiplier) {
        int length = 10;
        int addZero = 1;
        int summ = 0;
        System.out.println(insertNSpacesN(multiplicand, length));
        System.out.println(insertNSpacesS("*", length));
        System.out.println(insertNSpacesN(multiplier, length));
        System.out.println("-----------------");
        for (int i = lengthNumber(multiplier) - 1; i >= 0; i--) {
            System.out.println(insertNSpacesN(multiplicand * numberDigit(multiplier, i) * addZero, length));
            summ += multiplicand * numberDigit(multiplier, i) * addZero;
            addZero *= 10;
            if (i > 0) System.out.println(insertNSpacesS("+", length));
        }
        System.out.println("-----------------");
        System.out.println(insertNSpacesN(summ, length));

        return summ;
    }
    ////////////////////////////////////////////////////////////
    //length number
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

    ////////////////////////////////////////////////////////////
    //insert n spaces
    public static String insertNSpacesN(int number, int length) {
        String numberPlusSpace = "";
        for (int i = lengthNumber(number); i < length; i++) {
            numberPlusSpace += " ";
        }
        return numberPlusSpace + number;
    }

    public static String insertNSpacesS(String simbol, int length) {
        String numberPlusSpace = "";
        for (int i = lengthNumber(simbol.length()); i < length; i++) {
            numberPlusSpace += " ";
        }
        return numberPlusSpace + simbol;
    }

    public static int numberDigit(int number, int index) {
        String[] indexNumber = String.valueOf(number).split("");
        return Integer.valueOf(indexNumber[index]);
    }
}
