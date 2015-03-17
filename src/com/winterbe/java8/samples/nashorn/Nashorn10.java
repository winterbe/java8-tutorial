package com.winterbe.java8.samples.nashorn;

import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.concurrent.TimeUnit;

/**
 * @author Benjamin Winterberg
 */
public class Nashorn10 {

    public static void main(String[] args) throws ScriptException, NoSuchMethodException {
        NashornScriptEngine engine = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("load('res/nashorn10.js')");

        long t0 = System.nanoTime();

        for (int i = 0; i < 100000; i++) {
            engine.invokeFunction("testPerf");
        }

        long took = System.nanoTime() - t0;
        System.out.format("Elapsed time: %d ms", TimeUnit.NANOSECONDS.toMillis(took));
    }
}
