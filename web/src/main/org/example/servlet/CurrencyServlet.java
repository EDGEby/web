package main.org.example.servlet;

import main.org.example.util.EmailUtils;
import main.org.example.util.HTMLTableBuilder;
import main.org.example.util.XMLCurrencyParser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import static main.org.example.util.ServletUtils.*;

@WebServlet("/currency")
public class CurrencyServlet extends HttpServlet {
    @Override
    protected  void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        info(req,"currency -> doGet()");
        PrintWriter pw = resp.getWriter();
        try{
            String mail = req.getParameter("email");
            String usdCurrency =  XMLCurrencyParser.getCurrency("840");
            EmailUtils.send(mail,"Latest USD Currency", "<b>"+usdCurrency+"</b>");
            info(req,"currency -> send");
            pw.println("check your Email");
        }catch (Exception e){
            e.printStackTrace();
            pw.println("some error on server");
            info(req,"currency -> ERROR:" + e.getMessage());
        }
    }

    @Override
    protected  void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        info(req, "currency -> doPost()");
        PrintWriter pw = resp.getWriter();
        try {
            String mail = req.getParameter("email");
            String usdCurrency = XMLCurrencyParser.getCurrency("840");
            String euroCurrency = XMLCurrencyParser.getCurrency("978");
            String rubCurrency = XMLCurrencyParser.getCurrency("643");
            String cnyCurrency = XMLCurrencyParser.getCurrency("156");
            Date date = new Date();
            HTMLTableBuilder builder = new HTMLTableBuilder("Currencies", true, 4, 4, 15, 10, 10);

            builder.addTableHeader("NAME", "CODE", "VALUE", "DATE");
            builder.addRowValues("USD", "840", usdCurrency, date.toString());
            builder.addRowValues("EURO", "978", euroCurrency, date.toString());
            builder.addRowValues("RUB", "643", rubCurrency, date.toString());
            builder.addRowValues("CNY", "156", cnyCurrency, date.toString());
            String table = builder.build();

            EmailUtils.send(mail, "Latest Currencies", table);
            info(req, "currency -> send");
            resp.setContentType("text/html");
            pw.println(table);
            pw.println("<h1>Check your email as well</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("some error on server");
            info(req, "currency -> ERROR:" + e.getMessage());
        }
    }
}
