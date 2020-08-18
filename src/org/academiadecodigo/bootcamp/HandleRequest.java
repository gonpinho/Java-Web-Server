package org.academiadecodigo.bootcamp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class HandleRequest implements Runnable {

    Socket socket = null;
    private HttpResponse httpResponse;

    public HandleRequest(Socket socket) {
        this.socket = socket;
        httpResponse = new HttpResponse();
    }


    @Override
    public void run() {

        System.out.println("new thread.. new client");

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
}
