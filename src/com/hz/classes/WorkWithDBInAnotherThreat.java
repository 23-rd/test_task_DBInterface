package com.hz.classes;

import com.hz.DB.UserInfoDB;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.GenericJDBCException;
import org.hibernate.internal.SQLQueryImpl;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.transform.Transformers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: 23-rd
 * Date: 20.04.13
 * Time: 10:49
 * To change this template use File | Settings | File Templates.
 */
public class WorkWithDBInAnotherThreat implements Runnable
{
    private String login;
    private String password;
    private static ServiceRegistry serviceRegistry;
    private static SessionFactory sessionFactory;
    private static Session session;

    static
    {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration
                .getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        session = sessionFactory.openSession();
    }

    public WorkWithDBInAnotherThreat(String login, String password)
    {
        this.login =login;
        this.password = password;
    }
    @Override
    public void run() {
        UserInfoDB user = new UserInfoDB();
        user.setLogin(login);
        user.setPassword(password);
        user.setState("online");
        user.setDate(new Date());
        user.setPhoto("I2M2Y1ZDg.jpg");
        try
        {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
        catch (RuntimeException re)
        {
            //session.getTransaction().rollback();
        }

        //session.close();
    }

    public void SQLQ (String q)
    {
        try
        {
            //session.beginTransaction();
            Query qq =  session.createSQLQuery(q);
            qq.executeUpdate();
            session.getTransaction().commit();
        }
        catch (RuntimeException re)
        {
            //session.getTransaction().rollback();
        }
        //session.close();
    }

    public boolean getState(String login)
    {
        List l = null;
        try
        {
            //session.beginTransaction();
            Query qq = session.createQuery("SELECT * FROM users.userinfo WHERE login = '" + login + "'" );
            l = qq.list();
            qq.executeUpdate();
            //session.getTransaction().commit();
            //session.close();
        }
        catch (RuntimeException re)
        {
            //session.getTransaction().rollback();
        }
        if (l!=null)
        {
            if (l.get(0) == "online")
            {
                return true;
            }
        }


            return false;

    }

    public void closeSession()
    {
        session.close();
    }
}
