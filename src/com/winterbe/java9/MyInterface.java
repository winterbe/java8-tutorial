package com.winterbe.java9;

/**
 * 接口支持抽象方法
 */
interface MyInterface {
    //jdk 7 : 只能声明全局常量(public static final)和抽象方法(public abstract)
    void method1();

    // jdk 8 : 声明静态方法 和 默认方法
    public static void method2(){
        System.out.println("method2");
    }

    default void method3(){
        System.out.println("method3");
        method4();
    }

    //jdk 9 : 声明私有方法
    private void method4(){
        System.out.println("私有方法");
    }

}
