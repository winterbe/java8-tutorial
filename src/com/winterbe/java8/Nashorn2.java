package com.winterbe.java8;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileReader;

/**
 * Calling java methods from javascript with nashorn.
 *
 * @author Benjamin Winterberg
 */
public class Nashorn2 {

    public static String fun(String name) {
        System.out.format("Hi there from Java, %s", name);
        return "greetings from java";
    }

    public static void main(String[] args) throws Exception {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("res/nashorn2.js"));
    }

}