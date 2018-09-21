package com.winterbe.java8.samples.misc;

/**
 * @author Benjamin Winterberg
 */
public class Math1 {

    public static void main(String[] args) {
        testMathExact();
        testUnsignedInt();
    }

    private static void testMathExact() {
        System.out.println("Integer.MAX_VALUE is:" + Integer.MAX_VALUE);
        System.out.println("Long.MAX_VALUE is:" + Long.MAX_VALUE);

        try {
            System.out.println(Integer.MAX_VALUE + 1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            Math.addExact(Integer.MAX_VALUE, 1);
        } catch (ArithmeticException e) {
            System.err.println(e.getMessage());
        }

        try {
            Math.toIntExact(Long.MAX_VALUE);
        } catch (ArithmeticException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void testUnsignedInt() {
        try {
            Integer.parseUnsignedInt("-123", 10);
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }

        long maxUnsignedInt = (1l << 32) - 1;
        System.out.println("maxUnsignedInt:" + maxUnsignedInt);

        String string = String.valueOf(maxUnsignedInt);
        int unsignedInt = Integer.parseUnsignedInt(string, 10);
        System.out.println("unsignedInt:" + unsignedInt);

        String string2 = Integer.toUnsignedString(unsignedInt, 10);
        System.out.println("unsignedString:" + string2);

        try {
            Integer.parseInt(string, 10);
        } catch (NumberFormatException e) {
            System.err.println("could not parse signed int of " + maxUnsignedInt);
        }
    }


}
