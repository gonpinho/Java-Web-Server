package org.academiadecodigo.bootcamp;

import java.io.*;
import java.net.Socket;

public class HttpResponse {

    private final String URL_ERROR_404 = "www/errors/404.html";
    private HttpHeaderGenerator httpHeader;
    //private ServerUsage usage; //OFF

    public HttpResponse() {
        this.httpHeader = new HttpHeaderGenerator();
        //this.usage = new ServerUsage();
    }

    public void getResponse(String request, String filePath, Socket socket) {

        DataInputStream in = null;
        DataOutputStream out = null;

        if (request.equals("GET")) {

            File file = new File(getFilePath(filePath));

            try {

                if (!file.exists()) {
                    file = new File(URL_ERROR_404);
                }

                //open outstream
                out = new DataOutputStream(socket.getOutputStream());

                //send httpheader
                out.write(httpHeader.getHttpHeader(file).getBytes());
                out.flush();

                //send file
                in = new DataInputStream(new FileInputStream(file));

                byte[] bytes = new byte[1024];
                int bytesRead;

                while ((bytesRead = in.read(bytes)) != -1) {
                    out.write(bytes, 0, bytesRead);
                }

                //monitorize usage
                //usage.addBytesSent(file.length() + httpHeader.getHttpHeader(file).getBytes().length);


            } catch (FileNotFoundException e) {
                System.err.println(e.getMessage());

            } catch (IOException e) {
                System.err.println(e.getMessage());

            } finally {

                try {
                    if (in != null) {
                        in.close();
                    }

                    if (out != null) {
                        out.close();
                    }

                    if (socket != null) {
                        //usage.saveValues();
                        socket.close();
                    }

                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }

            }
        }
    }

    private String getFilePath(String filePath) {
        String path = null;

        if (filePath.equals("/")){
            path = "www/index.html";
            return path;
        }

        if (filePath.startsWith("/")) {
            path = "www".concat(filePath);
        }

        return path;
    }

}
