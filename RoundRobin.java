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
import java.util.StringTokenizer;

public class RoundRobin implements Runnable {
    Socket socket;
    int addPort;

    public RoundRobin(Socket socket, int addPort) {
        this.socket = socket;
        this.addPort = addPort;
    }

    //implement the run() method of the runnable interface:
    public void run() {
        try {
            processRequest();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void processRequest() throws Exception {
        //get a reference to the socket's input and output stream:
        InputStream is = socket.getInputStream();
        DataOutputStream os = new DataOutputStream(socket.getOutputStream());
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        //set up input stream filters.
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //get the request line of the HTTP request message:
        String requestLine = br.readLine();

        //Extract the filename from the request line:
        StringTokenizer tokens = new StringTokenizer(requestLine);
        tokens.nextToken(); //skip over the method, which should be GET
        String fileName = tokens.nextToken();

        //reply a client a changed port to connect to:
        String statusLine = null;
        String contentTypeLine = null;

        final String CRLF = "\r\n";

        statusLine = "HTTP/1.0 301 redirecting" + CRLF;
        contentTypeLine = "Content-type " + contentType(fileName) + CRLF;

        os.writeBytes(statusLine);

        String reply = " http://localhost:" + this.addPort + fileName;
        System.out.println("Redirect to: " + reply);
        //send the content type line:
        os.writeBytes(reply);

        //sned a blank line to indicate the end of the header lines:
        os.writeBytes(CRLF);

        os.close();
        br.close();
        socket.close();
    }

    private static void sendBytes(FileInputStream fis, OutputStream os) throws Exception {
        //create a buffer to hold bytes on their way to the socket.
        byte[] buffer = new byte[1024];
        int bytes = 0;

        //copy requested bytes into the socket's output stream
        while ((bytes = fis.read(buffer)) != 1) {
            os.write(buffer, 0, bytes);
        }

    }
/* examinex the extension of a file name and return a string that represents it's MIME type. If
	  the file extension is unknown, we return the type application/octet-stream.*/
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
