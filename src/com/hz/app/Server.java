package com.hz.app;

import com.hz.classes.WorkWithDBInAnotherThreat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;


/**
 * Created with IntelliJ IDEA.
 * User: 23-rd
 * Date: 20.04.13
 * Time: 12:50
 * To change this template use File | Settings | File Templates.
 */
public class Server extends Thread implements ServerListener {

    public LinkedList<Clients> userlist;
    private LinkedList<ServerListener> listenerList;
    private ServerSocket serverSocket;

    private Clients clients;

    public Server() {
        super("Server");
        userlist = new LinkedList<Clients>();
        listenerList = new LinkedList<ServerListener>();
    }

    // Запускает сервер на определенном порту
    public void init(int port) {
        try {
            serverSocket = new ServerSocket(port);
            start();
            String ip = "127.0.0.1";
            serverStarted(ip, port);
        } catch(Exception ex) {
            serverStopped();
        }
    }

    /********************** Обработка потока сервера ************************/

    @Override
    public void run() {
        while(true) {
            try
            {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");
                Clients clients1 = new Clients(this, socket);
                BufferedReader in  = new BufferedReader(new
                        InputStreamReader(socket.getInputStream()));
                PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                String input;
                while ((input = in.readLine()) != null) {
                    if (input.equalsIgnoreCase("exit")) break;
                    String inp =new String(input.getBytes("UTF-8"),"windows-1251");
                    out.println("S ::: "+input);
                    System.out.println(input);
                }
            }
            catch (Exception ex) {
            }
        }
    }

    /***************** отправка сообщения пользователeм ****************/

    public void sendMessage(Clients sender, String message) {
            for(Clients user : userlist) {
                try {
                    user.printStream.println("<"+(sender!=null ? sender.userName : "server")+"> "+message);
                } catch (Exception ex) {}
                if (message.contains("kick"))
                {
                    String[] temp = message.split("kick ");
                    String kickclient = temp[temp.length-1];
                    int t = 0;
                    for (Clients u : userlist)
                    {
                        if (u.userName == kickclient)
                        {
                            t = userlist.indexOf(u);
                            u.closeSocket();
                            //userlist.remove(t);
                            //removeUser(userlist.get(t));
                        }

                    }


                }
            }

    }

    /******************** добавление/удаление слушателей ********************/

    public void addListener(ServerListener listener) {
            listenerList.add(listener);
    }

    public void removeListener(ServerListener listener) {
            listenerList.remove(listener);
    }

    /******************** добавление/удаление онлайн пользователей ********************/

    public void addUser(Clients client) {
        userlist.add(client);
    }

    public void removeUser(Clients client) {
        String q = "UPDATE users.userinfo SET state='offline', DATE = '"+
                new Date()+"' WHERE login= \"" + client.userName +"\"";
        WorkWithDBInAnotherThreat db = new WorkWithDBInAnotherThreat(null, null);
        db.SQLQ(q);
    }

    /******************** методы интерфейса ServerListener, getters *******************/

    public void serverStarted(String ip, int port) {
            for(ServerListener listener : listenerList) {
                listener.serverStarted(ip, port);
            }
    }

    public void serverStopped() {
            for(ServerListener listener : listenerList) {
                listener.serverStopped();
            }
    }

    @Override
    public void onUserConnected(Clients user){
        for(ServerListener listener : listenerList) {
            listener.onUserConnected(user);
        }
    }

    public void onUserDisconnected(Clients user) {
            for(ServerListener listener : listenerList) {
                listener.onUserDisconnected(user);
            }
    }

    public void onMessageReceived(Clients user, String message) {
            for(ServerListener listener : listenerList) {
                listener.onMessageReceived(user, message);
            }
    }

    public LinkedList<ServerListener> getListenerList() {
        return listenerList;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    public Clients getClients() {
        return clients;
    }

    public LinkedList<Clients> getUserlist() {

        return userlist;
    }

}
