package com.winterbe.java11;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class HttpClientExamples {

    public static void main(String[] args) throws IOException, InterruptedException {
//        syncRequest();
//        asyncRequest();
//        postData();
        basicAuth();
    }

    private static void syncRequest() throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://winterbe.com"))
                .build();
        var client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }

    private static void asyncRequest() {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://winterbe.com"))
                .build();
        var client = HttpClient.newHttpClient();
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(System.out::println);
    }

    private static void postData() throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create("https://postman-echo.com/post"))
                .timeout(Duration.ofSeconds(30))
                .version(HttpClient.Version.HTTP_2)
                .header("Content-Type", "text/plain")
                .POST(HttpRequest.BodyPublishers.ofString("Hi there!"))
                .build();
        var client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());      // 200
    }

    private static void basicAuth() throws IOException, InterruptedException {
        var client = HttpClient.newHttpClient();

        var request1 = HttpRequest.newBuilder()
                .uri(URI.create("https://postman-echo.com/basic-auth"))
                .build();
        var response1 = client.send(request1, HttpResponse.BodyHandlers.ofString());
        System.out.println(response1.statusCode());      // 401

        var authClient = HttpClient
                .newBuilder()
                .authenticator(new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("postman", "password".toCharArray());
                    }
                })
                .build();
        var request2 = HttpRequest.newBuilder()
                .uri(URI.create("https://postman-echo.com/basic-auth"))
                .build();
        var response2 = authClient.send(request2, HttpResponse.BodyHandlers.ofString());
        System.out.println(response2.statusCode());      // 200
    }

}
