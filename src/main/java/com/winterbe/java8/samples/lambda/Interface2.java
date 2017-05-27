package com.winterbe.java8.samples.lambda;

import com.winterbe.java8.samples.lambda.beans.Child;
import com.winterbe.java8.samples.lambda.beans.Parent;
import com.winterbe.java8.samples.lambda.beans.Printer;

/**
 * @author edliao
 *         <p>
 *         Interface#default vs AbstractClass#abstract
 */
public class Interface2 {

    public static void main(String[] a) {
        /**
         * Interface with default can use lambda
         */
        Printer printer = str -> System.out.println("interface default : {" + str + "}");
        printer.printWelcome();
        printer.print(printer.getName());

        /**
         * abstract method is Long-winded
         */
        Parent parent = new Parent() {
            @Override
            public void print(String str) {
                System.out.println("abstract implement : {" + str + "}");
            }
        };
        parent.printWelcome();
        parent.print(parent.getName());


        Child child = new Child();
        child.printWelcome();
        child.print(child.getName());
    }

}
