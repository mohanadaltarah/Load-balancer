/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yasminshehu
 */
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;


public class Ports extends Thread{
    ServerSocket socket;
    InetAddress host;


    public Ports(String ip, int port) throws IOException {
        host = InetAddress.getByName(ip);
        this.socket = new ServerSocket(port,0,host);

        System.out.println("Socket created: " + port);

    }

    public void run(){
        while(true){
            //listen for a TCP connection request
            System.out.println("Socket " + socket.getLocalPort() + " Listening!!!!!!!!");
            Socket connection;
            try{
                connection = this.socket.accept();
                System.out.println("Socket accepted on " + connection.getLocalPort() + "%%%%%%%%%");

                //construct an object to process the HTTP Request message

                HTTPRequest request = new HTTPRequest(connection);

                Calendar c2 = Calendar.getInstance();

                System.out.println("Start time: " + c2.getTime()+ ":" + c2.getTimeInMillis());
                System.out.println("Started a new port thread.");

                request.start();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }



}

