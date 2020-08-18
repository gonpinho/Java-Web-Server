package org.academiadecodigo.bootcamp;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WebServer {

    private final int PORT_NUMBER = 9000;
    private ServerSocket serverSocket = null;
    private Socket socket = null;


    private WebServer() {

        try {
            serverSocket = new ServerSocket(PORT_NUMBER);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        WebServer webServer = new WebServer();
        ExecutorService executorService = Executors.newCachedThreadPool();

        while(true) {
                webServer.acceptConnection();
                executorService.submit(new HandleRequest(webServer.socket));
                //Thread handleClient = new Thread(new HandleRequest(webServer.socket));
                //handleClient.start();
        }

    }

    private void acceptConnection() {
        try {

            socket = serverSocket.accept();
            System.out.println("New connection from: " + socket);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

/*
    //webServer.run();

    private void run() {

        BufferedReader bReader = null;

            try {
                bReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                //read request
                String str = bReader.readLine();
                System.out.println(str);

                if (str != null) {
                    String[] request;
                    request = str.split(" ");
                    httpResponse.getResponse(request[0], request[1], socket);
                }

            } catch (IOException e) {
                System.err.println(e.getMessage());

            } finally {

                try {

                    if (bReader != null) {
                        bReader.close();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
    }
    */


