package com.hz.servlets;

import com.hz.DB.UserInfoDB;
import com.hz.app.Clients;
import com.hz.app.Server;
import com.hz.app.ServerListener;
import com.hz.classes.WorkWithDBInAnotherThreat;
import org.hibernate.Query;
import org.hibernate.Session;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.*;

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
    private Clients client;
    private String login;
    private String password;
    private WorkWithDBInAnotherThreat work;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        PrintWriter out = resp.getWriter();
        Enumeration flds = req.getParameterNames();
        String n = req.getParameter("name");
        int result = 0;
        for (int i=0; i<server.getUserlist().size(); i++)
        {
            if (server.getUserlist().get(i).userName == login)
            {
                result = i;
                break;
            }
        }
        Clients cl = server.getUserlist().get(result);
        cl.sendMessage("kick " + n);
        showResult(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        work = new WorkWithDBInAnotherThreat(req.getParameter("login"),
                req.getParameter("password"));
        Thread t = new Thread(work);
        t.start();
        server = new Server();
        server.init(8080);
        nClient("login 0");
        nClient("login 1");
        nClient("login 4");
        nClient("login 3");
        showResult(req, resp);
        login = req.getParameter("login");
        String password = req.getParameter("password");
        nClient(login);
    }

    private void showResult(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        PrintWriter pw = resp.getWriter();
        pw.println("<B>The selected login is:  ");
        pw.println(login);
        pw.println("<p><B>The selected password is:  ");
        pw.println(password);
        synchronized (server)
        {
            server.notify();
        }
        pw.println("<html> \n" +
                "     <head> \n" +
                "          <title> \n" +
                "               User kicking \n" +
                "          </title>\n" +
                "     </head> \n" +
                "     <body> ");
        if (server.getUserlist().size() == 0)
        {
            pw.println("<p><B> NoBody is online(");
        }
        for (int i=0; i<server.getUserlist().size(); i++)
        {
            try{
                pw.println("      <form name=\"Form1\"\n" +
                        "            method=\"GET\"\n" +
                        "            action=\"signin\">");
//                if (work.getState(server.getUserlist().get(i).userName))
//                {
                    pw.println("<P><div id=\"list5\">\n" +
                            "<li>"+ "<input type =\"text\"" + " name = \"name\"" + " value = \"" +server.getUserlist().get(i).userName +"\"\n" +
                            "<li>"+ server.getName() +"</li>\n" +
                            "<li>"+ server.getUserlist().get(i).userIp +"</li>\n" +
                            "<li>"+ server.getServerSocket().getInetAddress() + ":" +
                            server.getServerSocket().getLocalPort() +"</li>\n" +
                            "</li>" + "</div>");
                    pw.println("<input name=\"submit\" type=\"submit\" value=\"kick\"/>");
                    pw.println("</form>");
                //}
            }
            catch (NullPointerException e)
            {
                e.printStackTrace();
            }
        }
        pw.println("     </body> \n" + "</html>");
        pw.close();
    }

    private void nClient(String login) throws IOException
    {
        Clients client = new Clients(server);
        client.userName = login;
        client.userIp = "127.0.0.1" + login;
        server.addListener(this);
        server.addUser(client);
        String q = "UPDATE users.userinfo SET state='online' WHERE login= '" + client.userName +"'";
        WorkWithDBInAnotherThreat db = new WorkWithDBInAnotherThreat(null, null);
        db.SQLQ(q);
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

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }
}
