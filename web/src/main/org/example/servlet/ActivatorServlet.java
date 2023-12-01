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

@WebServlet("/activate")
public class ActivatorServlet extends HttpServlet {

    private UserDAOImpl dao = new UserDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        // 1 Get Token Value
        String token = req.getParameter("token");
        // cIvQn67NEe8h1wpAGaH7RQO0pTR%20/DDuzkXYYJK7GNqs=
        String email = EncryptDecryptUtils.decrypt(token);

        // 2. Check if it's valid Email at all
        User userFromDB = dao.findByEmail(email);
        if(userFromDB != null){
            if(userFromDB.getIsActive()){
                resp.getWriter().println("Already activated!");
            } else {
                // 3. Activate for new email
                dao.activate(userFromDB);
                resp.getWriter().println("Activated! |You can login now! Yahoo! ");
                resp.getWriter().println("<a href='login.html'> login </a> "); // should be Servlet
            }
        } else {
            resp.getWriter().println("Incorrect user token");
        }

    }
}
