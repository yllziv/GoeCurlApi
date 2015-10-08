package com.yanglonglong;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletTest extends javax.servlet.http.HttpServlet {
    //初始化
    public void init() throws ServletException {
        System.out.println("我是init()方法！用来进行初始化工作");
    }
    //处理GET请求
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("GB2312");
        response.setContentType("text/plain;charset=GB2312");
        int a = new Integer(request.getParameter("a"));
        int b = new Integer(request.getParameter("b"));
        PrintWriter out = response.getWriter();// 获得输出流out
        out.println(a + b);
        out.flush(); // 冲刷缓冲区
        out.close(); // 关闭输出
    }
    //处理POST请求
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    //销毁实例
    public void destroy() {
        super.destroy();
        System.out.println("我是destroy()方法！用来进行销毁实例的工作");
    }
}
