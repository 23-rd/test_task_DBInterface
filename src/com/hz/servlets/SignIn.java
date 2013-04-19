package com.hz.servlets;

import com.hz.DB.UserInfoDB;
import com.hz.classes.User;
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
    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession hsession =  req.getSession();
        String login = (String) hsession.getAttribute("login");
        String password = (String) hsession.getAttribute("password");
        PrintWriter pw = resp.getWriter();
        pw.println("<B>The selected login is:  ");
        pw.println(login);
        pw.println("<B>The selected password is:  ");
        pw.println(password);
        pw.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        HttpSession hsession =  req.getSession();

        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration
                .getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        UserInfoDB[] users = new UserInfoDB[5];
        for (int i=0; i<users.length; i++)
        {
            users[i].setLogin("login " + i);
            users[i].setPassword("password" + i);
            users[i].setState(false);
            session.beginTransaction();
            session.save(users[i]);
            session.getTransaction().commit();
        }

        String login = (String) hsession.getAttribute("login");
        String password = (String) hsession.getAttribute("password");
        User user = new User(login, password);

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }
}
