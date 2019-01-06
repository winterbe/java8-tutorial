package com.winterbe.java11;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.*;
import java.util.stream.Stream;

/**
 * @Author: LiuShishuang
 * @Description:TODO
 * @Date: 22:40 2019/1/6
 */
public class Java9Test {

    public static void main(String[] args) {
        // 钻石操作符 声明的是子类对象
        Set<String> set = new HashSet<>();
        Set<String> set2 = new HashSet<>() {
        };


        //  UnderScore(下划线)使用的限制
//        String _ = "hello";  //java8支持标识符独立使用


        //4. 只读集合
        List<Integer> list = Collections.unmodifiableList(Arrays.asList(1, 2)); //java8
        //java9
        List<Integer> listUnmodified = List.of(1, 2);
        Set<Integer> setUnmodified = Set.of(2, 3, 4);
        Map<String, Integer> mapUnmodified = Map.of("Tom", 23);


        // 全新的httpclient
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest req =
                HttpRequest.newBuilder(URI.create("http://www.atguigu.com"
                ))
                        .GET()
                        .build();

    }

    @Test
    public void testTry() {
        //java8
        try (InputStreamReader reader = new
                InputStreamReader(System.in)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        //java9
        InputStreamReader reader = new InputStreamReader(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        try (reader; writer) {
            //reader 是 final 的，不可再被赋值
            // reader = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testStream() {
        List<Integer> listForStream =
                Arrays.asList(45, 43, 76, 87, 42, 77, 90, 73, 67, 88);
        listForStream.stream()
                .takeWhile(x -> x < 50)
                .dropWhile(x -> x > 50)
                .forEach(System.out::println);

        //允许创建单元素Stream,并且元素为null ofNullable方法
        Stream<String> stringStream = Stream.of("AA", "BB", null);
        stringStream.count();  //3
        Stream<Object> stream1 = Stream.ofNullable(null);
        stream1.count();//0
        Stream<String> stream = Stream.ofNullable("hello world");
        stream.count();//1

        //原来的控制终止方式：
        Stream.iterate(1, i -> i + 1).limit(10)
                .forEach(System.out::println);
        // 现在的终止方式：
        Stream.iterate(1, i -> i < 100, i -> i + 1)
                .forEach(System.out::println);


        //通过stream获取optional
        Optional<List<String>> optional =
                Optional.ofNullable(Arrays.asList("a", "b"));
        optional.stream()
                .flatMap(Collection::stream)
                .forEach(System.out::println);
    }


}
