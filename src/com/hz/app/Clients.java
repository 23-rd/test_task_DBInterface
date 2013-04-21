package com.hz.app;

import java.io.*;
import java.net.Socket;

/**
 * Created with IntelliJ IDEA.
 * User: 23-rd
 * Date: 20.04.13
 * Time: 12:52
 * To change this template use File | Settings | File Templates.
 */
public class Clients extends Thread {

    Server server = null;

    private Socket socket = null;
    public BufferedReader bufferedReader = null;
    public PrintStream printStream = null;
    public String userName = "";
    public String userIp = "";
    private String message;

/*
    public Clients (Server server) throws IOException
    {
        super("User Thread");
        this.server = server;
    }
*/

    public Clients(Server server) throws IOException {
        super("User Thread");
        this.server = server;
        this.socket = new Socket("127.0.0.1", 8080);
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        printStream = new PrintStream(socket.getOutputStream(), true, "UTF-8");
        start();
    }

    public Clients(Server server, Socket socket) throws IOException {
        super("User Thread");
        this.server = server;
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        printStream = new PrintStream(socket.getOutputStream(), true, "UTF-8");
        start();
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void sendMessage(String message) throws IOException {
        this.message = message;
        //String m = new String(message.getBytes("UTF-8"),"windows-1251");
        StringReader sr = new StringReader(message);
        BufferedReader in  = new BufferedReader(sr);
        PrintWriter    out = new PrintWriter(getSocket().getOutputStream(),true);

        out.println(in);
        server.sendMessage(this, message);
    }

    @Override
    public void run() {
        try {
            userIp = socket.getInetAddress().getHostAddress();
            server.onUserConnected(this);
            server.userlist.add(this);
            server.sendMessage(null, "Connectet user : "+userName);
            while(true) {
                try {
                    String messageReceived = message; // блокируется пока не получит строки или null!
                    if(messageReceived==null) {
                        //closeSocket();
                        break;
                    }
                    else if(!messageReceived.isEmpty()) {
// Нотификация: получено сообщение
                        server.onMessageReceived(this, messageReceived);
// Отправляем всем сообщение
                        server.sendMessage(this, messageReceived);
                    }
                } catch (Exception ex) {
                    closeSocket();
                    break;
                }
            }
        } catch(Exception ex) {}
    }

    // Удаляет пользователя из списка онлайн пользователей и освобождает ресурсы сокета
    public void closeSocket() {
        try {
// Нотификация: пользователь отключился
            server.onUserDisconnected(this);
// Удаляем пользователя со списка онлайн
            //server.userlist.remove(this);
            bufferedReader.close();
            printStream.close();
            socket.close();
        } catch (Exception ex) {}
    }

}
