package org.academiadecodigo.bootcamp;

import java.io.*;

public class ServerUsage {

    private long bytesSent;
    private int answeredRequests;

    public ServerUsage() {
        loadLastValues();
    }

    public void loadLastValues() {
        File file = new File("resources/data.txt");
        BufferedReader in = null;
        try {
           in = new BufferedReader(new FileReader(file));
           bytesSent = Integer.parseInt(in.readLine());
           answeredRequests = Integer.parseInt(in.readLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void saveValues() {
        File file = new File ("resources/data.txt");
        PrintWriter out = null;
        try {
            out = new PrintWriter(file);
            out.println(bytesSent);
            out.println(answeredRequests);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }

        }
    }

    public void addBytesSent(long bytes) {
        bytesSent+=bytes;
        answeredRequests++;
    }

    public void printValues() {
        System.out.println("Bytes sent: " + bytesSent);
        System.out.println("Answered requests " + answeredRequests);
    }


}
