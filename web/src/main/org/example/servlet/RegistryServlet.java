package main.org.example.servlet;

import main.org.example.jdbs.dao.impl.UserDAOImpl;
import main.org.example.model.User;
import main.org.example.util.EmailUtils;
import main.org.example.util.EncryptDecryptUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/registry")
public class RegistryServlet extends HttpServlet {

    private UserDAOImpl dao = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher rd = req.getRequestDispatcher("/reg.html");
        rd.forward(req, resp); //move forward with the same params and so on
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");


        //getting parameter from HTML Registry form
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String psw1 = req.getParameter("psw1");
        String psw2 = req.getParameter("psw2");


        //EmailSender.send(email, "Application registry", "Login: " + name + " Password1: " + password1 + " Password2: " + password2);
        resp.getWriter().println("Hi. Message send to your email: " + email);

        // 1 Validation

        User userFromGUI = new User();
        userFromGUI.setName(name.trim());
        userFromGUI.setEmail(email.trim().toLowerCase());
        userFromGUI.setPwd(EncryptDecryptUtils.encrypt(psw1));
        // if its valid create user in memory(BD)

        try {
            boolean created = dao.create(userFromGUI);
            //2 send message with activate instructions
            if (created) {
                String token = EncryptDecryptUtils.encrypt(email);
                String url = "http://localhost:8080/web_app_war/activate?token=" + token;
                EmailUtils.send(email, "APP REG", "<h1> Click <a href='" + url + "'> here</a> to activate </h1>  ");

            }else {
                PrintWriter pw = resp.getWriter();
                pw.println("Error");
                System.out.println("Error");
            }
            //3 show same info in response

        }catch (Exception e){
            PrintWriter pw = resp.getWriter();
            pw.println("<details><summary>Tech Error</summary>" +
                    "  <p>"+e.getMessage() +"</p></details>");
            e.printStackTrace();
        }
    }
}
