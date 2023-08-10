package hello.servlet.basic;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);
        //request = org.apache.catalina.connector.RequestFacade@434f6f88
        //response = org.apache.catalina.connector.ResponseFacade@14b5a56e

        String username = request.getParameter("username");
        System.out.println("username = " + username); // username = kim

        // content header
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");

        // content body
        response.getWriter().write("hello"+username); // 화면에 hello kim 출력됨

    }
}
