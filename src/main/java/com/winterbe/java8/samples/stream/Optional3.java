package com.winterbe.java8.samples.stream;

import java.util.Optional;

/**
 * @author edliao on 2017/5/27.
 * @description handle 'null' in 'get/find/select' situation
 */
public class Optional3 {
    /**
     * simulate 'get' method which probably return null
     */
    public Object getObj(int i) {
        return i / 2 == 0 ? new Object() : null;
    }

    /**
     * instead of optional
     */
    public Optional<Object> getOptionalWithObj(int i) {
        return Optional.ofNullable(getObj(i));
    }


    public static void main(String[] a) {
        Optional3 service = new Optional3();

        //usually
        Object result = service.getObj(3);
        if (result != null) {
            //do something
            result.toString();
        }

        //with optional
        Optional<Object> resultOpt = service.getOptionalWithObj(4);
        resultOpt.ifPresent(o -> {
            //do something
            o.toString();
        });

    }
}
