/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yasminshehu
 */
import java.io.*;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.StringTokenizer;

public class HTTPRequest extends Thread {
    final static String CRLF = "\r\n";
    Socket socket;

    public HTTPRequest(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        InputStream is;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

        try {
            Calendar c = Calendar.getInstance();
            String startDate = sdf.format(c.getTime());
            long startTime = System.nanoTime();
            is = socket.getInputStream();

            DataOutputStream os = new DataOutputStream(socket.getOutputStream());

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            String requestLine = br.readLine();

            StringTokenizer tokens = new StringTokenizer(requestLine);
            tokens.nextToken();
            String fileName = tokens.nextToken();

            fileName = '.' + fileName;
            FileInputStream fis = null;
            boolean fileExist = true;
            try {
                fis = new FileInputStream(fileName);
            } catch (FileNotFoundException e) {
                fileExist = false;
            }

            System.out.println("Incoming!");
            System.out.println(requestLine);
            String headerLine = null;
            while ((headerLine = br.readLine()).length() != 0) {
                System.out.println(headerLine);
            }

            String statusLine = null;
            String contentTypeLine = null;
            String entityBody = null;
            if (fileExist) {
                statusLine = "HTTP/1.0 200 OK" + CRLF;
                contentTypeLine = "Content-Type:" + contentType(fileName);
            } else {
                statusLine = "HTTP/1.0 404 Not Found" + CRLF;
                contentTypeLine = "Content-Type: text/html" + CRLF;
                entityBody = "<HTML>" + "<HEAD><TITLE>Not Found</TITLE></HEAD>" + "<BODY>Not Found</BODY></HTML>";
            }
            os.writeBytes(statusLine);

            os.writeBytes(contentTypeLine + CRLF);
            System.out.println(contentTypeLine);

            os.writeBytes(CRLF);

            if (fileExist) {
                try {
                    sendBytes(fis, os);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                fis.close();
            } else {
                os.writeBytes(entityBody);
            }
            Calendar c2 = Calendar.getInstance();
            String endDate = sdf.format(c2.getTime());
            long endTime = System.nanoTime();

            System.out.println("End time:" + c2.getTime() + ":" + c2.getTimeInMillis());
            int duration = (int) (c2.getTimeInMillis() - c.getTimeInMillis());
            System.out.println("It took  " + duration + " millisecond");

            os.close();
            br.close();
            socket.close();
            System.out.println("---Thread Done---");

           


        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

    private static void sendBytes(FileInputStream fis, OutputStream os) throws Exception {
        byte[] buffer = new byte[1024];
        int bytes = 0;

        while ((bytes = fis.read(buffer)) != -1) {
            os.write(buffer, 0, bytes);
        }

    }

    private static String contentType(String fileName) {
        if (fileName.endsWith(".htm") || fileName.endsWith(".html")) {
            return "text.html";
        }
        if (fileName.endsWith(".ram") || fileName.endsWith(".ra")) {
            return "audio/x-pn-realaudio";
        }
        if (fileName.endsWith(".png") || fileName.endsWith(".jpeg") || fileName.endsWith(".jng") || fileName.endsWith(".gif")) {
            return "image";

        }
        return "application/octet-stream";

    }
}

