package com.winterbe.java8.samples.lambda;

/**
 * @author Benjamin Winterberg
 */
public class Lambda4 {

    static int outerStaticNum = 30;

    int outerNum = 10;


    void testScopes() {
        /**
         * var in function scope
         *
         * it will be tread as final if it used in lambda ,
         * if assigned it ,lambda will error
         *
         */
        int num = 1;
        Lambda2.Converter<Integer, String> stringConverter = (from) -> String.valueOf(from + num);
        System.out.println("function var :" + stringConverter.convert(2));    // 3
        //num = 2; //cant assigned 'num'

        /**
         * var in class scope
         */
        Lambda2.Converter<Integer, String> stringConverter2 = (from) -> String.valueOf(from + outerNum);
        System.out.println("classbody var:" + stringConverter2.convert(1));
        outerNum = 20;
        System.out.println("classbody var changed:" + stringConverter2.convert(1));


        /**
         * array(other object) in function scope ,like final val
         * 'array' is final ,but element in it can changed
         */
        String[] array = new String[1];//array is final ,but object in it can be changed
        Lambda2.Converter<Integer, String> stringConverter3 = (from) -> {
            array[0] = "Hi there";
            return String.valueOf(from);
        };
        stringConverter3.convert(23);
        System.out.println("change function var:" + array[0]);

        /**
         * static var
         */
        Lambda2.Converter<Integer, String> stringConverter4 = (from) ->
                String.valueOf(outerStaticNum + from);
        System.out.println("body static var:" + stringConverter4.convert(12));
        outerStaticNum = 20;
        System.out.println("body static var changed:" + stringConverter4.convert(12));
    }

    public static void main(String[] args) {
        new Lambda4().testScopes();
    }

}