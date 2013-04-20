package com.hz.app;

import java.net.ServerSocket;
import java.net.Socket;
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
        super("Chat server");
        userlist = new LinkedList<Clients>();
        listenerList = new LinkedList<ServerListener>();
    }

    // Запускает сервер на определенном порту
    public void init(int port) {
        try {
            serverSocket = new ServerSocket(port);
            start();
            String ip = "localhost://";
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
                Clients user = new Clients(this, socket);
            }
            catch (Exception ex) {
            }
        }
    }
    /***************** отправка сообщения пользователeм ****************/

    public void sendChatMessage(Clients sender, String message) {
            for(Clients user : userlist) {
                try {
                    user.printStream.println("<"+(sender!=null ? sender.userName : "server")+"> "+message);
                } catch (Exception ex) {}
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
        userlist.remove(client);
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
