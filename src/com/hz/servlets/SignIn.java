package com.hz.servlets;

import com.hz.DB.UserInfoDB;
import com.hz.classes.User;
import com.hz.classes.WorkWithDBInAnotherThreat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created with IntelliJ IDEA.
 * com.hz.classes.User: 23-rd
 * Date: 19.04.13
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
public class SignIn extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        PrintWriter pw = resp.getWriter();
        pw.println("<B>The selected login is:  ");
        pw.println(login);
        pw.println("<B>The selected password is:  ");
        pw.println(password);
        pw.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        WorkWithDBInAnotherThreat threat = new WorkWithDBInAnotherThreat(req.getParameter("login"),
                req.getParameter("password"));
        Thread t = new Thread(threat);
        t.start();
        doGet(req, resp);
    }
}
