package main.org.example.util;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class ServletUtils {

    public static void info(HttpServletRequest request, String msg) {
        request.getMethod();
        request.getContextPath();
        request.getPathInfo();
        request.getServletPath();
        request.getRequestURI();
        request.getRequestURL();
        System.out.println(String.format("%s INFO [%s] Method:%s | Path: %s | %s ",
                new Date().toString(), Thread.currentThread().getName(), request.getMethod(), request.getServletPath(), msg));
    }

    public static void forward(HttpServletRequest request, HttpServletResponse response, String path) {
        info(request, "FORWARD to -> " + path);
        try {
            request.getRequestDispatcher(path).forward(request, response);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void include(HttpServletRequest request, HttpServletResponse response, String path, String message){
        info(request, "INCLUDE to -> " + path);
        try {
            response.getWriter().print("<h1>" + message + "</h1>");
            RequestDispatcher rd = request.getRequestDispatcher(path);
            rd.include(request, response);
        } catch (IOException | ServletException e) {
            throw new RuntimeException(e);
        }
    }
}