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

    // Хранит онлайн пользователей чата
    public LinkedList<Clients> userlist;
    // Хранит слушателей сервера
    private LinkedList<ServerListener> listenerList;
    // СерверСокет
    private ServerSocket serverSocket;

    private Clients clients;

    // Конструктор
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
            String ip = serverSocket.getInetAddress().getLocalHost().getHostAddress();
// Нотификация события: сервер запущен
            serverStarted(ip, port);
        } catch(Exception ex) {
// Нотификация события: cервер прекратил работу
            serverStopped();
        }
    }

    /********************** Обработка потока сервера ************************/

    @Override
    public void run() {
        while(true) {
            try {
// Метод accept() блочит данный поток пока не подключиться новый пользователь
                Socket socket = serverSocket.accept();
// Создание нового потока-обработчика для подключенного пользователя
// он выполняется паралельно данному потоку, и ниче не блочит.
// Сервер переходит к готовности принять новое соединение
                Clients user = new Clients(this, socket);
            } catch (Exception ex) {
            }
        }
    }
    /***************** отправка сообщения всем пользователям ****************/
// sender - отправитель

    public void sendChatMessage(Clients sender, String message) {
            for(Clients user : userlist) {
                try {
                    user.printStream.println("<"+(sender!=null ? sender.userName : "server")+"> "+message);
                } catch (Exception ex) {}
            }

    }

    /******************** добавление/удаление слушателей ********************/

// Добавляет слушателя событий сервера
    public void addListener(ServerListener listener) {
            listenerList.add(listener);
    }

    // Удаляет слушателя
    public void removeListener(ServerListener listener) {
            listenerList.remove(listener);
    }

    /******************** методы интерфейса ServerListener *******************/

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
    public void onUserConnected(Clients user)
    {
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
}
