package main.org.example.servlet;

import main.org.example.jdbs.dao.abs.AbstractDAO;
import main.org.example.jdbs.dao.impl.UserDAOImpl;
import main.org.example.model.User;
import main.org.example.util.EncryptDecryptUtils;
import main.org.example.util.UserUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


@WebServlet("/user")
public class UserServlet extends HttpServlet {
    private UserDAOImpl dao = new UserDAOImpl();


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Set<User> users = dao.all();
        resp.setContentType("text/html");
        if (users.size() > 0){
            if (req.getParameter("id") != null && req.getParameter("action") == null){
                UserUtils.userTableResponse(req, resp, users);
            }else if (req.getParameter("id") != null && req.getParameter("action") != null){
                if (req.getParameter("action").equals("D")){
                    System.out.println(AbstractDAO.deleteById(Integer.parseInt(req.getParameter("id"))));
                    users = AbstractDAO.all();
                    UserUtils.allUsersTableResponse(resp, users);
                    return;
                }
                if (req.getParameter("action").equals("U")){
                    for (User user : users){
                        if (user.getId().equals(Integer.parseInt(req.getParameter("id")))){
                            UserUtils.showUserToUpdate(resp, user);
                            return;
                        }
                    }
                }

            }else{
              UserUtils.allUsersTableResponse(resp, users);
            }
        }else {
            UserUtils.showInfo(resp, "Database is empty");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userFromDB = (User) AbstractDAO.findById(Integer.parseInt(req.getParameter("id")));
        User userToUpdate = new User();
        if (req.getParameter("pwdOld").equals(EncryptDecryptUtils.decrypt(userFromDB.getPwd()))) {
        }
        else {
            doGet(req, resp);
        }
    }
}
