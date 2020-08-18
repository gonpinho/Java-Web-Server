package org.academiadecodigo.bootcamp;

import java.io.File;

public class HttpHeaderGenerator {

    public String getHttpHeader(File file) {

        String header;
        String mime;

        //compose header
        if (file.getPath().contains("404")){

            header = "HTTP/1.0 404 Not Found";
            mime = "Content-Type: text/html; charset=UTF-8";

        } else {

            header = "HTTP/1.1 200 OK";
            mime = (file.getPath().endsWith(".jpg")) ? "Content-Type: image/jpeg"
                    : (file.getPath().endsWith(".ico")) ? "Content-Type: image-xicon"
                    : (file.getPath().endsWith(".png")) ? "Content-Type: image/png"
                    : (file.getPath().endsWith(".gif")) ? "Content-Type: image/gif"
                    : (file.getPath().endsWith(".mp3")) ? "Content-Type: audio/mpeg"
                    : (file.getPath().endsWith(".js")) ? "Content-Type: text/javascript"
                    : (file.getPath().endsWith(".css")) ? "Content-Type: text/css; charset=UTF-8"
                    : "Content-Type: text/html; charset=UTF-8";

        }

        return header + "\r\n" + mime + "\r\nContent-Length: " + file.length() + "\r\n\n";

    }

}
