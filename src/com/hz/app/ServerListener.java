package com.hz.app;

import org.apache.catalina.User;

/**
 * Created with IntelliJ IDEA.
 * User: 23-rd
 * Date: 20.04.13
 * Time: 12:49
 * To change this template use File | Settings | File Templates.
 */
public interface ServerListener {

    /****** События *******/

    // Сервер запустился
    public void serverStarted(String ip, int port);

    // Сервер прекратил работу
    public void serverStopped();

    // Подключился новый пользователь
    public void onUserConnected(Clients user);

    // Пользователь отключился
    public void onUserDisconnected(Clients user);

    // Получено сообщение от пользователя
    public void onMessageReceived(Clients user, String message);
}
