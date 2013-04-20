package com.hz.servlets;

import com.hz.app.Clients;
import com.hz.app.Server;
import com.hz.app.ServerListener;
import com.hz.classes.WorkWithDBInAnotherThreat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * com.hz.classes.User: 23-rd
 * Date: 19.04.13
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
public class SignIn extends HttpServlet implements ServerListener
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
        nClient();
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

    private void nClient()
    {
        Random r = new Random();
        int ran = r.nextInt(8000) + 2000 ;
        Server server = new Server();
        server.addListener(this);
        server.init(ran);
    }

    @Override
    public void serverStarted(String ip, int port) {
        System.out.println("\nСервер запущен по адресу: "+ip+" порт: "+port);
    }

    @Override
    public void serverStopped() {
        System.out.println("\nНевозможно запустить сервер ");
    }

    @Override
    public void onUserConnected(Clients user) {
        System.out.println("\nПодключен новый пользователь: "+user.userName);
    }

    @Override
    public void onUserDisconnected(Clients user) {
        System.out.println("\nПользователь отключился: "+user.userName);
    }

    @Override
    public void onMessageReceived(Clients user, String message) {
        System.out.println("\n<"+user.userName+"> "+message);
    }
}
