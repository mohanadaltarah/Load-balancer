/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yasminshehu
 */
public class Client {
    public static void main(String[] args){
        ClientThread http = new ClientThread();
           //creating multiple client threads.
        System.out.println("Sending Http Request...");
             
        for(int i=0; i!=5; i++){
            Thread t = new Thread(http);
            t.start();
        }
    }
}