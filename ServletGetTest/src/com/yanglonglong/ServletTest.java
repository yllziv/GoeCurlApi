package com.yanglonglong;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ServletTest extends javax.servlet.http.HttpServlet {
    //��ʼ��
    public void init() throws ServletException {
        System.out.println("����init()�������������г�ʼ������");
    }
    //����GET����
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("����doGet()��������������GET����");
        System.out.println(request.getAttributeNames());
        response.setContentType("text/html;charset=GB2312");
        PrintWriter out = response.getWriter(); // ��������out
        out.println("<HTML>"); // ����ҳ�����
        out.println("<BODY>");
        out.println("This is servlet demo");
        out.println("</BODY>");
        out.println("</HTML>");
        out.close(); // �ر������
    }
    //����POST����
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
    //����ʵ��
    public void destroy() {
        super.destroy();
        System.out.println("����destroy()������������������ʵ���Ĺ���");
    }
}
