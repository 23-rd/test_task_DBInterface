package com.hz.servlets;

import com.hz.DB.UserInfoDB;
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
import java.net.Socket;
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

    private Server server;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        server = new Server();
        server.init(8080);
        nClient("login0");
        nClient("login1");
        nClient("login4");
        nClient("login3");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        nClient(login);
        PrintWriter pw = resp.getWriter();
        pw.println("<B>The selected login is:  ");
        pw.println(login);
        pw.println("<p><B>The selected password is:  ");
        pw.println(password);
        synchronized (server)
        {
            server.notify();
        }
        if (server.getUserlist().size() == 0)
        {
            pw.println("<p><B> NoBody is online(");
        }
        for (int i=0; i<server.getUserlist().size(); i++)
        {
            try{
                pw.println("<P><div id=\"list5\">\n" +
                        "<li>"+ server.getUserlist().get(i).userName +"\n" +
                        "<li>"+ server.getName() +"</li>\n" +
                        "<li>"+ server.getUserlist().get(i).userIp +"</li>\n" +
                        "<li>"+ server.getServerSocket().getInetAddress() + ":" +
                        server.getServerSocket().getLocalPort() +"</li>\n" +
                        "</li>" + "</div>");
            }
            catch (ArrayIndexOutOfBoundsException e)
            {
                e.printStackTrace();
            }
        }
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

    private void nClient(String login) throws IOException
    {
        Clients client = new Clients(server);
        client.userName = login;
        client.userIp = "localhost://" + login;
        server.addListener(this);
        server.addUser(client);
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
