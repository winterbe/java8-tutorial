//package com.winterbe.java9.subscribe
//import java.io.IOException;
//import java.util.concurrent.CompletableFuture;
//import java.util.concurrent.TimeUnit;
//
//import javax.servlet.AsyncContext;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * Servlet implementation class AsyncServlet
// */
//@WebServlet(asyncSupported = true, urlPatterns = { "/AsyncServlet" })
//public class AsyncServlet extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * @see HttpServlet#HttpServlet()
//     */
//    public AsyncServlet() {
//        super();
//    }
//
//    /**
//     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
//     *      response)
//     */
//    protected void doGet(HttpServletRequest request,
//                         HttpServletResponse response) throws ServletException, IOException {
//        long t1 = System.currentTimeMillis();
//
//        // 开启异步
//        AsyncContext asyncContext = request.startAsync();
//
//        // 执行业务代码
//        CompletableFuture.runAsync(() -> doSomeThing(asyncContext,
//                asyncContext.getRequest(), asyncContext.getResponse()));
//
//        System.out.println("async use:" + (System.currentTimeMillis() - t1));
//    }
//
//    private void doSomeThing(AsyncContext asyncContext,
//                             ServletRequest servletRequest, ServletResponse servletResponse) {
//
//        // 模拟耗时操作
//        try {
//            TimeUnit.SECONDS.sleep(5);
//        } catch (InterruptedException e) {
//        }
//
//        //
//        try {
//            servletResponse.getWriter().append("done");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 业务代码处理完毕, 通知结束
//        asyncContext.complete();
//    }
//
//    /**
//     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
//     *      response)
//     */
//    protected void doPost(HttpServletRequest request,
//                          HttpServletResponse response) throws ServletException, IOException {
//        doGet(request, response);
//    }
//}
