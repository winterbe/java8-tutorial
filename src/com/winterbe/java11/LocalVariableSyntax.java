package com.winterbe.java11;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class LocalVariableSyntax {

    public static void main(String[] args) {
        var text = "Banana";
//      Incompatible types:
//      text = 1;


//        Cannot infer type:
//        var a;
//        var nothing = null;
//        var bla = () -> System.out.println("Hallo");
//        var method = LocalVariableSyntax::someMethod;

        var list1 = new ArrayList<>();   // ArrayList<Object>

        var list2 = new ArrayList<Map<String, List<Integer>>>();

        for (var current : list2) {
            // current is of type: Map<String, List<Integer>>
            System.out.println(current);
        }

        Predicate<String> predicate1 = (@Deprecated var a) -> false;

    }

    void someMethod() {}

}
