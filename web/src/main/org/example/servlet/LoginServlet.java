package main.org.example.servlet;


import main.org.example.jdbs.dao.impl.UserDAOImpl;
import main.org.example.model.User;
import main.org.example.util.EncryptDecryptUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static main.org.example.util.ServletUtils.forward;
import static main.org.example.util.ServletUtils.include;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UserDAOImpl dao = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
//        RequestDispatcher rd = req.getRequestDispatcher("/log.html");
//        rd.forward(req, resp); //move forward with the same params and so on

        forward(req, resp, "/log.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");

        //PrintWriter pw = resp.getWriter();
        //pw.println("<h1>Login servlet</h1><p><h2>Name: " + req.getParameter("password") + "</h2></p><p><h2>Email: " + req.getParameter("email") + "</h2></p>");

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserDAOImpl userDAO = new UserDAOImpl();
        User user = userDAO.findByEmail(email.trim());

        if (user != null){
            if (user.getPwd().equals(EncryptDecryptUtils.encrypt(password))){
                if (user.getIsActive()){
                    resp.getWriter().println("Login successfully");
                    req.setAttribute("name", user.getName());
                    forward(req, resp, "/home?name=+" + user.getName());
                }
                else {
                    include(req, resp, "/log.html", "Sorry, user is not activated!");
                }
            }else {
                include(req, resp, "/log.html", "Sorry, Password incorrect!");
            }
        }else {
            include(req, resp, "/log.html", "Sorry, Email not exists! Please <a href='registration'> registry</a>");
        }

        // --- homework ---

        /*try{
            User user = userDAO.findByEmail(email.trim());
            if (user.getPwd().equals(EncryptDecryptUtils.encrypt(password))){
                if (user.getIsActive()){
                    resp.getWriter().println("Login successfully");
                }
                else {
                    resp.getWriter().println("<h1>User is not activated.</h1><p><h2>Check your email.</h2><p>");
                }
            }else {
                resp.getWriter().println("Password incorrect");
            }
        }catch (Exception e){
            resp.getWriter().println("Email incorrect");
        }*/



    }
}
