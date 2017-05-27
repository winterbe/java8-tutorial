package com.winterbe.java8.samples.nashorn;

import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import javax.script.SimpleScriptContext;

/**
 * @author Benjamin Winterberg
 */
public class Nashorn11 {

    public static void main(String[] args) throws Exception {
//        test1();
//        test2();
//        test3();
//        test4();
//        test5();
//        test6();
//        test7();
        test8();
    }

    private static void test8() throws ScriptException {
        NashornScriptEngine engine = createEngine();

        engine.eval("var obj = { foo: 23 };");

        ScriptContext defaultContext = engine.getContext();
        Bindings defaultBindings = defaultContext.getBindings(ScriptContext.ENGINE_SCOPE);

        SimpleScriptContext context1 = new SimpleScriptContext();
        context1.setBindings(defaultBindings, ScriptContext.ENGINE_SCOPE);

        SimpleScriptContext context2 = new SimpleScriptContext();
        context2.getBindings(ScriptContext.ENGINE_SCOPE).put("obj", defaultBindings.get("obj"));

        engine.eval("obj.foo = 44;", context1);
        engine.eval("print(obj.foo);", context1);
        engine.eval("print(obj.foo);", context2);
    }

    private static void test7() throws ScriptException {
        NashornScriptEngine engine = createEngine();

        engine.eval("var foo = 23;");

        ScriptContext defaultContext = engine.getContext();
        Bindings defaultBindings = defaultContext.getBindings(ScriptContext.ENGINE_SCOPE);

        SimpleScriptContext context1 = new SimpleScriptContext();
        context1.setBindings(defaultBindings, ScriptContext.ENGINE_SCOPE);

        SimpleScriptContext context2 = new SimpleScriptContext();
        context2.getBindings(ScriptContext.ENGINE_SCOPE).put("foo", defaultBindings.get("foo"));

        engine.eval("foo = 44;", context1);
        engine.eval("print(foo);", context1);
        engine.eval("print(foo);", context2);
    }

    private static void test6() throws ScriptException {
        NashornScriptEngine engine = createEngine();

        ScriptContext defaultContext = engine.getContext();
        defaultContext.getBindings(ScriptContext.GLOBAL_SCOPE).put("foo", "hello");

        ScriptContext customContext = new SimpleScriptContext();
        customContext.setBindings(defaultContext.getBindings(ScriptContext.ENGINE_SCOPE), ScriptContext.ENGINE_SCOPE);

        Bindings bindings = new SimpleBindings();
        bindings.put("foo", "world");
        customContext.setBindings(bindings, ScriptContext.GLOBAL_SCOPE);

//        engine.eval("foo = 23;");                 // overrides foo in all contexts, why???

        engine.eval("print(foo)");                  // hello
        engine.eval("print(foo)", customContext);   // world
        engine.eval("print(foo)", defaultContext);  // hello
    }

    private static void test5() throws ScriptException {
        NashornScriptEngine engine = createEngine();

        engine.eval("var obj = { foo: 'foo' };");
        engine.eval("function printFoo() { print(obj.foo) };");

        ScriptContext defaultContext = engine.getContext();
        Bindings defaultBindings = defaultContext.getBindings(ScriptContext.ENGINE_SCOPE);

        SimpleScriptContext context1 = new SimpleScriptContext();
        context1.setBindings(defaultBindings, ScriptContext.ENGINE_SCOPE);

        SimpleScriptContext context2 = new SimpleScriptContext();
        context2.setBindings(defaultBindings, ScriptContext.ENGINE_SCOPE);

        engine.eval("obj.foo = 'bar';", context1);
        engine.eval("printFoo();", context1);
        engine.eval("printFoo();", context2);
    }

    private static void test4() throws ScriptException {
        NashornScriptEngine engine = createEngine();

        engine.eval("function foo() { print('bar') };");

        ScriptContext defaultContext = engine.getContext();
        Bindings defaultBindings = defaultContext.getBindings(ScriptContext.ENGINE_SCOPE);

        SimpleScriptContext context = new SimpleScriptContext();
        context.setBindings(defaultBindings, ScriptContext.ENGINE_SCOPE);

        engine.eval("foo();", context);
        System.out.println(context.getAttribute("foo"));
    }

    private static void test3() throws ScriptException {
        NashornScriptEngine engine = createEngine();

        ScriptContext defaultContext = engine.getContext();
        Bindings defaultBindings = defaultContext.getBindings(ScriptContext.ENGINE_SCOPE);

        SimpleScriptContext context = new SimpleScriptContext();
        context.setBindings(defaultBindings, ScriptContext.ENGINE_SCOPE);

        engine.eval("function foo() { print('bar') };", context);
        engine.eval("foo();", context);

        Bindings bindings = context.getBindings(ScriptContext.ENGINE_SCOPE);
        System.out.println(bindings.get("foo"));
        System.out.println(context.getAttribute("foo"));
    }

    private static void test2() throws ScriptException {
        NashornScriptEngine engine = createEngine();
        engine.eval("function foo() { print('bar') };");

        SimpleScriptContext context = new SimpleScriptContext();
        engine.eval("print(Function);", context);
        engine.eval("foo();", context);
    }

    private static void test1() throws ScriptException {
        NashornScriptEngine engine = createEngine();
        engine.eval("function foo() { print('bar') };");
        engine.eval("foo();");
    }

    private static NashornScriptEngine createEngine() {
        return (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
    }

}
