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

    // Сокет пользователя
    private Socket socket = null;
    // Входной канал
    public BufferedReader bufferedReader = null;
    // Выходной канал
    public PrintStream printStream = null;
    // Имя пользователя
    public String userName = "";
    // IP пользователя
    public String userIp = "";


    // Конструктор
    Clients(Server chatServer, Socket socket) throws IOException {
        super("User Thread");
        this.chatServer = chatServer;
        this.socket = socket;
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        printStream = new PrintStream(socket.getOutputStream(), true, "UTF-8");
        start();
    }
    // Обработка потока
    @Override
    public void run() {
        try {
// Предлагаем пользователю ввести свое имя
            printStream.println("Enter your username: ");
// IP пользователя
            userIp = socket.getInetAddress().getHostAddress();
// Получаем логин пользователя
            userName = bufferedReader.readLine(); // блокируется пока не получит строки!
// Нотификация о подключении нового пользователя
            chatServer.onUserConnected(this);
// Помещаем пользователя в список пользователей
            chatServer.userlist.add(this);
// Отправляем всем сообщение
            chatServer.sendChatMessage(null, "Подключен пользователь: "+userName);

// основной цикл обработки
            while(true) {
                try {
// Читаем новое сообщение от пользователя
                    String messageReceived = bufferedReader.readLine(); // блокируется пока не получит строки или null!
                    if(messageReceived==null) {
// Невозможно прочитать данные, пользователь отключился от сервера
                        closeSocket();
// Останавливаем бесконечный цикл
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
// Отправляем всем сообщение
            chatServer.sendChatMessage(null, "Отключен пользователь: "+userName);
// Удаляем пользователя со списка онлайн
            chatServer.userlist.remove(this);
// Закрываем потоки
            bufferedReader.close();
            printStream.close();
            socket.close();
        } catch (Exception ex) {}
    }

}
