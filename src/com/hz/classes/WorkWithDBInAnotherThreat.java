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

    /******Открытие сессии для работы с БД********/
    static
    {
        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration
                .getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        session = sessionFactory.openSession();
    }

    /********Наш единственный конструктор************/
    public WorkWithDBInAnotherThreat(String login, String password)
    {
        this.login =login;
        this.password = password;
    }

    /*****Поток записывающих польз-ля который ввел логин и пароль*****/
    @Override
    public void run() {
        UserInfoDB user = new UserInfoDB();
        user.setLogin(login);
        user.setPassword(password);
        user.setState("online");
        user.setDate(new Date());
        user.setPhoto("I2M2Y1ZDg.jpg");
        try{
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
        closeSession();
        }catch (RuntimeException re) {}
    }

    /******Выполняет запросы полученные из других классов*****/
    public void changeState (String login, String state)
    {
        try{
        session = sessionFactory.openSession();
        session.beginTransaction();
        Query qq =  session.createQuery("UPDATE UserInfoDB SET state = '" + state +"' WHERE login='" + login + "'");
        int eu = qq.executeUpdate();
        session.getTransaction().commit();
        closeSession();
        }catch (RuntimeException re) {}
    }

    /****Нахождение в БД пользователя и определение онлайн ли он******/
    public boolean getState(String login)
    {
        List l = null;
        try{
        session = sessionFactory.openSession();
        Query qq = session.createQuery("select state FROM UserInfoDB WHERE login='" + login + "'");
        l = qq.list();
        closeSession();
        }catch (RuntimeException re) {}
        if (l!=null&&l.size() != 0)
        {
            if (l.get(0).equals("online"))
            {
                return true;
            }
        }
    return false;
    }

    /*******Закрытие сессии***********/
    public void closeSession()
    {
        session.close();
    }
}
