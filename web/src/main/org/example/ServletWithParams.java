package main.org.example;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/params")
public class ServletWithParams extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String aParam = req.getParameter("a");
        String bParam = req.getParameter("b");
        double a = Double.valueOf(aParam);
        double b = Double.valueOf(bParam);
        double s = a + b;

        PrintWriter pw = resp.getWriter();
        pw.println("Result:");
        pw.println(s);
    }



}