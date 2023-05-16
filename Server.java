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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;


public final class Server {

    static int num = 0;

    public static void run() throws Exception {
        int port = 1234;
        String ip = "127.0.0.1";
        InetAddress inet = InetAddress.getByName(ip);
        //returns the IP Address of the given host name.
        System.out.println("Port: " + port);
        //Establish the listen socket:

        ServerSocket socket = new ServerSocket(port, 0, inet);
        System.out.println("Socket created");

        int addPort = 2000;

        Ports sp1 = new Ports(ip, 2001);
        Ports sp2 = new Ports(ip, 2002);
        Ports sp3 = new Ports(ip, 2003);
        Ports sp4 = new Ports(ip, 2004);

        sp1.start();
        sp2.start();
        sp3.start();
        sp4.start();
//
//        try {
//            sp1.join();
//            sp2.join();
//            sp3.join();
//            sp4.join();
//        } catch (InterruptedException e) {
//
//        }

        //process HTTP Service requests in an infinite loop:
        while (true) {
           addPort = (int)(Math.random()*(2005-2001+1)+2001);
//            if (addPort == 2004) {
//                addPort = 2000;
//            }
//            addPort = addPort + 1;

            //listen for a TCP Connection request message: 
            Socket connection = socket.accept();
            System.out.println("Connection accepted.");
            num++;
            System.out.println("Connection: " + num);

            //construct an object to process the HTTP request message:
            RoundRobin request = new RoundRobin(connection, addPort);

            //create a new thread to process the request:
            Thread thread = new Thread(request);

            //start the thread:
            thread.start();
            thread.sleep(100);
            System.out.println("Thread Started!");

        }

    }

}
