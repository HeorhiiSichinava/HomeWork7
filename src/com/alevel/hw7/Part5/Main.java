package com.alevel.hw7.Part5;

import java.awt.image.Kernel;
import java.util.Scanner;

///multiply In AKaratsuba №2
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("A:");
        String a = scanner.next();
        System.out.println("B:");
        String b = scanner.next();
        System.out.println("result A[" + a + "]*B[" + b + "] = " + multiplyInAKaratsuba(a, b));
    }

    //////////////////////////////////////////
    ///multiply In AKaratsuba
    public static String multiplyInAKaratsuba(String multiplicand, String multiplier) {
        if (multiplicand.length() == 1 || multiplier.length() == 1) {
            if (Integer.valueOf(multiplicand) == 0 || Integer.valueOf(multiplicand) == 0) {
                return "" + 0;
            }
        }
//        System.out.println("A:" + multiplicand + "\nB:" + multiplier + "\n");
        String resultMultiply = "";
        String A0B0 = "";
        String A1B1 = "";
        String A1B0A0B1 = "";
        String A1B0 = "";
        String A0B1 = "";
        String[] a = new String[2];
        String[] b = new String[2];

        a[0] = lengthToDivide(multiplicand, "L");
        a[1] = lengthToDivide(multiplicand, "R");
        b[0] = lengthToDivide(multiplier, "L");
        b[1] = lengthToDivide(multiplier, "R");

//        System.out.println("A0:" + a[0] + " A1:" + a[1]);
//        System.out.println("B0:" + b[0] + " B1:" + b[1] + "\n");

        if ((a[0].length() > 1) && (b[0].length() > 1)) {
            A0B0 = multiplyInAKaratsuba(String.valueOf(a[0]), String.valueOf(b[0]));
            A1B1 = multiplyInAKaratsuba(String.valueOf(a[1]), String.valueOf(b[1]));
            A1B0 = multiplyInAKaratsuba(String.valueOf(a[1]), String.valueOf(b[0])) + "" + extent10(String.valueOf(b[1]).length());
            A0B1 = multiplyInAKaratsuba(String.valueOf(a[0]), String.valueOf(b[1])) + "" + extent10(String.valueOf(a[1]).length());

            A1B0A0B1 = summ(A1B0, A0B1);
            resultMultiply = summ(A0B0 + "" + extent10(String.valueOf(a[1]).length() + String.valueOf(b[1]).length()), A1B1, A1B0A0B1);
//            System.out.println("result:" + resultMultiply + " resultA0B0:" + A0B0 + "" + extent10(String.valueOf(a[1]).length() + String.valueOf(b[1]).length()) + " resultA1B1:" + A1B1 + " resultA1B0A0B1:" + A0B1);
        } else {
            if (Integer.valueOf(a[0]) == 0 || Integer.valueOf(b[0]) == 0) {
                A0B0 = "";
            } else {
                A0B0 = Integer.valueOf(a[0]) * Integer.valueOf(b[0]) + "" + extent10(String.valueOf(a[1]).length() + String.valueOf(b[1]).length());
            }
            if (Integer.valueOf(a[1]) == 0 || Integer.valueOf(b[1]) == 0) {
                A1B1 = "";
            } else {
                A1B1 = Integer.valueOf(a[1]) * Integer.valueOf(b[1]) + "";
            }
            if (Integer.valueOf(a[0]) == 0 || Integer.valueOf(b[1]) == 0) {
                A0B1 = "";
            } else {
                A0B1 = Integer.valueOf(a[0]) * Integer.valueOf(b[1]) + "" + extent10(String.valueOf(a[1]).length());
            }
            if (Integer.valueOf(a[1]) == 0 || Integer.valueOf(b[0]) == 0) {
                A1B0 = "";
            } else {
                A1B0 = Integer.valueOf(a[1]) * Integer.valueOf(b[0]) + "" + extent10(String.valueOf(b[1]).length());
            }
            resultMultiply = summ(summ(A0B0, A1B1), summ(A1B0, A0B1));
//            System.out.println("result:" + resultMultiply);
        }
        return resultMultiply;
    }

    /////////////////////////////////////////
    /// 10 extent x
    public static String extent10(int x) {
        String result = "";
        for (int i = 0; i < x; i++) {
            result += 0;
        }
        return result;
    }

    public static String lengthToDivide(String number, String leftHalfRightHalf) {
        String newString = "";
        switch (leftHalfRightHalf) {
            case "L":
                if (number.length() < 2) {
                    newString = "0";
                } else {
                    for (int i = 0; i < number.length() / 2 + number.length() % 2; i++) {
                        newString += number.charAt(i);
                    }
                }
                break;
            case "R":
                if (number.length() > 1) {
                    for (int i = number.length() / 2 + number.length() % 2; i < number.length(); i++) {
                        newString += number.charAt(i);
                    }
                } else {
                    newString = number;
                }
                break;
        }
        return newString;
    }

    public static String summ(String a, String b) {
        String result = "";
        int length = a.length() > b.length() ? a.length() : b.length();
        int buffer = 0;
        int aN = a.length() - 1, bN = b.length() - 1;
        for (int i = length; i >= 0; i--) {
            if (aN >= 0 && bN >= 0) {
                buffer = (Integer.valueOf(a.charAt(aN)) + Integer.valueOf(b.charAt(bN))) - 48 * 2 + buffer;
                if (buffer < 10) {
                    result = buffer + result;
                    buffer /= 10;
                } else {
                    result = String.valueOf(buffer).charAt(1) + result;
                    buffer /= 10;
                }
            } else if (aN < 0 && bN >= 0 || bN < 0 && aN >= 0) {
                result = aN < 0 ? ((Integer.valueOf(b.charAt(bN)) - 48) + buffer) + result : ((Integer.valueOf(a.charAt(aN)) - 48) + buffer) + result;
                buffer /= 10;
            } else if (aN < 0 && bN < 0 && buffer > 0) {
                result = buffer + result;
                buffer /= 10;
            }
            aN--;
            bN--;
        }
        return result;
    }

    public static String summ(String a, String b, String c) {
        String result = "";
        int length = a.length() > b.length() ? a.length() : b.length();
        length = length > c.length() ? length : c.length();
        int buffer = 0;
        int aN = a.length() - 1, bN = b.length() - 1, cN = c.length() - 1;
        for (int i = length; i >= 0; i--) {
            if (aN >= 0 && bN >= 0 && cN >= 0) {
                buffer = ((Integer.valueOf(a.charAt(aN)) + Integer.valueOf(b.charAt(bN)) + Integer.valueOf(c.charAt(cN))) - 48 * 3) + buffer;
                if (buffer < 10) {
                    result = buffer + result;
                    buffer /= 10;
                } else {
                    result = String.valueOf(buffer).charAt(1) + result;
                    buffer /= 10;
                }
            } else if (cN < 0 && aN >= 0 && bN >= 0) {
                buffer += ((Integer.valueOf(a.charAt(aN)) + Integer.valueOf(b.charAt(bN))) - 48 * 2) + buffer;
                if (buffer < 10) {
                    result = buffer + result;
                    buffer /= 10;
                } else {
                    result = String.valueOf(buffer).charAt(1) + result;
                    buffer /= 10;
                }
            } else if (bN < 0 && aN >= 0 && cN >= 0) {
                buffer = ((Integer.valueOf(a.charAt(aN)) + Integer.valueOf(c.charAt(cN))) - 48 * 2) + buffer;
                if (buffer < 10) {
                    result = buffer + result;
                    buffer /= 10;
                } else {
                    result = String.valueOf(buffer).charAt(1) + result;
                    buffer /= 10;
                }
            } else if (aN < 0 && bN >= 0 && cN >= 0) {
                buffer = ((Integer.valueOf(b.charAt(bN)) + Integer.valueOf(c.charAt(cN))) - 48 * 2) + buffer;
                if (buffer < 9) {
                    result = buffer + result;
                    buffer /= 10;
                } else {
                    result = String.valueOf(buffer).charAt(1) + result;
                    buffer /= 10;
                }
            } else if (aN < 0 && bN < 0 && cN >= 0 || aN < 0 && cN < 0 && bN >= 0 || bN < 0 && cN < 0 && aN >= 0) {
                result = aN < 0 && bN < 0 ? ((Integer.valueOf(c.charAt(cN)) - 48) + buffer) + result :
                        (bN < 0) && (cN < 0) ? ((Integer.valueOf(a.charAt(aN)) - 48) + buffer) + result : ((Integer.valueOf(b.charAt(bN)) - 48) + buffer) + result;
                buffer /= 10;
            } else if (aN < 0 && bN < 0 && cN < 0 && buffer > 0) {
                result = buffer + result;
            }
            aN--;
            bN--;
            cN--;
        }
        return result;
    }
}
