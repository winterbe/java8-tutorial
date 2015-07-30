package com.winterbe.java8.samples.nashorn;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * @author Benjamin Winterberg
 */
public class Nashorn7 {

    public static class Person {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getLengthOfName() {
            return name.length();
        }
    }

    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("function foo(predicate, obj) { return !!(eval(predicate)); };");

        Invocable invocable = (Invocable) engine;

        Person person = new Person();
        person.setName("Hans");

        String predicate = "obj.getLengthOfName() >= 4";
        Object result = invocable.invokeFunction("foo", predicate, person);
        System.out.println(result);
    }

}
