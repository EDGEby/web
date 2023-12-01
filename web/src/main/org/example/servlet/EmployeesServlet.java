package main.org.example.servlet;

import com.mysql.cj.exceptions.CJConnectionFeatureNotAvailableException;
import main.org.example.jdbs.dao.abs.OfficeDAO;
import main.org.example.jdbs.dao.impl.EmployeeDAOImpl;
import main.org.example.jdbs.dao.impl.OfficeDAOImpl;
import main.org.example.jdbs.dao.impl.PassportDAOImpl;
import main.org.example.model.Employee;
import main.org.example.model.Passport;
import main.org.example.util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

@WebServlet("/employees")
public class EmployeesServlet extends HttpServlet {
    private EmployeeDAOImpl dao = new EmployeeDAOImpl();
    private OfficeDAOImpl officeDAO = new OfficeDAOImpl();
    private PassportDAOImpl passportDAO = new PassportDAOImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String action = req.getParameter("action");
        if (action!=null){
            switch (action){
                case "D":
                    dao.deleteById(Integer.valueOf(req.getParameter("id")));
                    break;
                case"U":
                    //TODO
                    break;
                case "C":
                    req.setAttribute("offices", officeDAO.all());
                    ServletUtils.openJSP(req,resp,"create-empl");
                    return;
            }
        }
        req.setAttribute("empls", dao.all());
        ServletUtils.openJSP(req,resp,"employees");

    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Employee();
        employee.setName(req.getParameter("name"));
        employee.setLastName(req.getParameter("last_name"));
        employee.setAge(Math.abs(Integer.valueOf(req.getParameter("age"))));
        int officeId = Integer.valueOf(req.getParameter("office_id"));
        employee.setOffice( officeDAO.findById(officeId));

        Passport passport = new Passport();
        passport.setPersonalID(req.getParameter("personal_id"));
        passport.setIndID(req.getParameter("ind_id").trim());


        String expDateStr = req.getParameter("exp_date");
        SimpleDateFormat format = new SimpleDateFormat("yyyy.mm.dd");
        try {
            java.util.Date parsed = format.parse(expDateStr);
            Date sql = new Date(parsed.getTime());
            passport.setExpTS( sql);
        } catch (ParseException e){
            e.printStackTrace();
        }

        int passId = passportDAO.createPassport2(passport);
        passport.setId(passId);
        employee.setPassport(passport);

        if (dao.createEmployee(employee)) {
            req.setAttribute("empls", dao.all());
            ServletUtils.openJSP(req, resp, "employees");
        }else  {
            //TODO Show generic error
            ServletUtils.shoError(req,resp,"Error Employee creation");
        }

    }
}
