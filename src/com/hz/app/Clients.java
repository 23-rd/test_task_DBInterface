package com.hz.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: 23-rd
 * Date: 20.04.13
 * Time: 12:52
 * To change this template use File | Settings | File Templates.
 */
public class Clients extends Thread {

    Server chatServer = null;

    private Socket socket = null;
    public BufferedReader bufferedReader = null;
    public PrintStream printStream = null;
    public String userName = "";
    public String userIp = "";

    public  Clients (Server server) throws IOException
    {
        super("User Thread");
        this.chatServer = server;
    }

    public Clients(Server chatServer, Socket socket) throws IOException {
        super("User Thread");
        this.chatServer = chatServer;
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        printStream = new PrintStream(socket.getOutputStream(), true, "UTF-8");
        start();
    }

    @Override
    public void run() {
        try {
            userIp = socket.getInetAddress().getHostAddress();
            chatServer.onUserConnected(this);
            chatServer.userlist.add(this);
            chatServer.sendChatMessage(null, "Подключен пользователь: "+userName);
            while(true) {
                try {
                    String messageReceived = bufferedReader.readLine(); // блокируется пока не получит строки или null!
                    if(messageReceived==null) {
                        closeSocket();
                        break;
                    }
                    else if(!messageReceived.isEmpty()) {
// Нотификация: получено сообщение
                        chatServer.onMessageReceived(this, messageReceived);
// Отправляем всем сообщение
                        chatServer.sendChatMessage(this, messageReceived);
                    }
                } catch (Exception ex) {
                    closeSocket();
                    break;
                }
            }
        } catch(Exception ex) {}
    }

    // Удаляет пользователя из списка онлайн пользователей и освобождает ресурсы сокета
    private void closeSocket() {
        try {
// Нотификация: пользователь отключился
            chatServer.onUserDisconnected(this);
// Удаляем пользователя со списка онлайн
            chatServer.userlist.remove(this);
            bufferedReader.close();
            printStream.close();
            socket.close();
        } catch (Exception ex) {}
    }

}
