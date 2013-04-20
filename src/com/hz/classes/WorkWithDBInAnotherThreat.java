package com.hz.classes;

import com.hz.DB.UserInfoDB;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.util.Date;

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

    public WorkWithDBInAnotherThreat(String login, String password)
    {
        this.login =login;
        this.password = password;
    }
    @Override
    public void run() {

        Configuration configuration = new Configuration();
        configuration.configure();
        serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration
                .getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        Session session = sessionFactory.openSession();

        UserInfoDB user = new UserInfoDB();
        user.setLogin(login);
        user.setPassword(password);
        user.setState("online");
        user.setDate(new Date());
        user.setPhoto("I2M2Y1ZDg.jpg");

        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();

        session.close();
    }
}
