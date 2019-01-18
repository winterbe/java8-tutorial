package com.winterbe.mine;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.WebSocket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Author: LiuShishuang
 * @Description:TODO
 * @Date: 22:40 2019/1/6
 */
public class Java9Test {

    // 注解声明,将来会移除
    @Deprecated(forRemoval = true)
    String foo;

    public static void main(String[] args) {
        // 钻石操作符 声明的是子类对象
        Set<String> set = new HashSet<>();
        Set<String> set2 = new HashSet<>() {
        };


        //  UnderScore(下划线)使用的限制
//        String _ = "hello";  //java8支持标识符独立使用
    }

    /**
     * Strings的新方法
     * strip() 可以去掉Unicode空格,例如中文空格
     * isBlank() 判断是否是空白字符串
     * lines()  按照行来分割字符串
     * repeat(count) 指定重复次数
     */
    @Test
    public void testStrings() {
        System.out.println(" ".isBlank());         //判断是否为空, 不需要StringUtils了
        System.out.println(" Foo Bar ".trim());    //去除首尾的空白字符(空格,tab,换行符)  法删除掉Unicode空白字符
        // Character.isWhitespace(c) 可以判断是否是可以移除的
        System.out.println(" Foo Bar ".strip());    // "Foo Bar"  去除首尾的空格
        System.out.println(" Foo Bar ".stripTrailing());    // " Foo Bar"
        System.out.println(" Foo Bar ".stripLeading());    // "Foo Bar "
        System.out.println("Java".repeat(3));    // "JavaJavaJava"
        System.out.println("A\nB\nC".lines().count());  // 3  自动换行操作
    }

    /**
     * 集合的改进
     * List.of
     * Map.of
     * Set.of
     * List.copyOf(List) 集合的拷贝
     * list.toArray() 集合转换为数组
     */
    @Test
    public void testCollections() {
        List<Integer> listForJava8 = Collections.unmodifiableList(Arrays.asList(1, 2)); //java8

        List<String> list = List.of("A", "B", "C");
        var copy = List.copyOf(list);
        System.out.println(list == copy);   // true

        var map = Map.of("A", 1, "B", 2);
        var set = Set.of("a", "b", "c");

        String[] newArray = list.toArray(String[]::new);
        //排序操作
        Optional.ofNullable(list).ifPresent(listValue -> {
            listValue.sort(Comparator.nullsLast(Comparator.naturalOrder()));
        });
    }

    /**
     * writeString 将字符串写入文件
     * readString  读取字符串
     *
     * @throws IOException
     */
    @Test
    public void testFiles() throws IOException {
        Files.writeString(Path.of("./", "tmp.txt"),
                "hello,World",
                StandardCharsets.UTF_8);

        String s = Files.readString(
                Paths.get("./tmp.txt"), // 路径
                StandardCharsets.UTF_8); // 编码
    }

    /**
     * try...catch的改进
     */
    @Test
    public void testTryCatch() {
        //java8
        try (InputStreamReader reader = new
                InputStreamReader(System.in)) {
        } catch (IOException e) {
            e.printStackTrace();
        }
        //java9
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream("com/winterbe/mine/java9.txt");
        InputStreamReader reader = new InputStreamReader(System.in);
        OutputStreamWriter writer = new OutputStreamWriter(System.out);
        try (inputStream; reader; writer) {
            //reader 是 final 的，不可再被赋值
            // reader = null;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 流的改进
     * takeWhile()
     * dropWhile
     * iterator(开始,结束条件,增长条件)
     * <p>
     * //Optional
     * Stream.ofNullable(null) 允许创建单元素Stream,并且元素为null
     * stream() 直接对流进行stream操作
     * orElseThrow()  抛出一个大的异常
     * or(() -> Optional.of(值)).get() 对Optional处理,允许先给出一个Optional值
     */
    @Test
    public void testStream() {
        List<Integer> listForStream =
                Arrays.asList(45, 43, 76, 87, 42, 77, 90, 73, 67, 88);
        listForStream.stream()
                .takeWhile(x -> x < 50)
                .dropWhile(x -> x > 50)
                .forEach(System.out::println);


        //原来的控制终止方式：
        Stream.iterate(1, i -> i + 1).limit(10)
                .forEach(System.out::println);
        // 现在的终止方式：
        Stream.iterate(1, i -> i < 100, i -> i + 1)
                .forEach(System.out::println);

        //Optional的改进
        //允许创建单元素Stream,并且元素为null ofNullable方法
        Stream<String> stringStream = Stream.of("AA", "BB", null);
        stringStream.count();  //3
        Stream<Object> stream1 = Stream.ofNullable(null);
        stream1.count();//0
        Stream<String> stream = Stream.ofNullable("hello world");
        stream.count();//1

        //Optional允许直接进行stream操作
        Optional.ofNullable(Arrays.asList("a", "b"))
                .stream()
                .flatMap(Collection::stream)
                .forEach(System.out::println);

        //允许直接抛出异常(不指定类型)
        Optional.ofNullable("hello")
                .orElseThrow();

        //允许为null时,给出Optional类型的默认值
        Optional.ofNullable("hello")
                .or(() -> Optional.of("world"))
                .get();


        System.out.println(Optional.of("foo").orElseThrow());   // foo
        System.out.println(Optional.ofNullable(null).or(() -> Optional.of("bar")).get());   // bar
        System.out.println(Optional.of("foo").stream().count());    // 1
    }

    /**
     * 原生的HttpClient
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Test
    public void testHttpClient() throws IOException, InterruptedException {
        //httpclient
        HttpClient client = HttpClient.newHttpClient();
        HttpClient.newBuilder()
                .connectTimeout(Duration.ofMillis(5000)) //连接超时时间
                .followRedirects(HttpClient.Redirect.NORMAL) //重定向
                .authenticator(new Authenticator() {  //密码校验
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("postman", "password".toCharArray());
                    }
                });
       /* HttpEntity multipartEntity = MultipartEntityBuilder.create()
                .addPart("file", new FileBody(file, ContentType.DEFAULT_BINARY))
                .setBoundary(multipartFormDataBoundary) //要设置，否则阻塞
                .build();*/


        //httpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://www.baidu.com")) //url地址
                .timeout(Duration.ofSeconds(30)) //本次连接超时时间
                .version(HttpClient.Version.HTTP_2) //指定Http请求版本
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofString("requestBodyJson"))
                .build();

        //BodyHandler
        HttpResponse.BodyHandler<String> stringBodyHandler = HttpResponse.BodyHandlers.ofString(Charset.defaultCharset());
        HttpResponse.BodyHandler<Path> pathBodyHandler = HttpResponse.BodyHandlers.ofFile(Paths.get("body.txt"));

        //发送同步请求
        HttpResponse<String> syncResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        //异步请求
        CompletableFuture<HttpResponse<String>> asynResponse = client.sendAsync(request, stringBodyHandler);
        asynResponse.thenApply(HttpResponse::body)
                .exceptionally(err -> {
                    err.printStackTrace();
                    return "fallback";
                })
                .thenAccept(System.out::print)
                .join();
    }

    @Test
    public void testWebSocket() throws InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        WebSocket webSocket = client.newWebSocketBuilder()
                .buildAsync(URI.create("ws://localhost:8080/echo"), new WebSocket.Listener() {
                    @Override
                    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
                        // request one more
                        webSocket.request(1);
                        // Print the message when it's available
                        return CompletableFuture.completedFuture(data)
                                .thenAccept(System.out::println);
                    }
                }).join();
        webSocket.sendText("hello ", false);
        webSocket.sendText("world ", true);
        TimeUnit.SECONDS.sleep(10);
        webSocket.sendClose(WebSocket.NORMAL_CLOSURE, "ok").join();
    }

    @Test
    public void testConcurrentRequests() {
        HttpClient client = HttpClient.newHttpClient();
        List<String> urls = List.of("http://www.baidu.com", "http://www.alibaba.com/", "http://www.tencent.com");
        List<HttpRequest> requests = urls.stream()
                .map(url -> HttpRequest.newBuilder(URI.create(url)))
                .map(reqBuilder -> reqBuilder.build())
                .collect(Collectors.toList());
        List<CompletableFuture<HttpResponse<String>>> futures = requests.stream()
                .map(request -> client.sendAsync(request, HttpResponse.BodyHandlers.ofString()))
                .collect(Collectors.toList());
        futures.stream()
                .forEach(e -> e.whenComplete((resp, err) -> {
                    if (err != null) {
                        err.printStackTrace();
                    } else {
                        System.out.println(resp.body());
                        System.out.println(resp.statusCode());
                    }
                }));
        CompletableFuture.allOf(futures
                .toArray(CompletableFuture<?>[]::new))
                .join();
    }


}
