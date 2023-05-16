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
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.StringTokenizer;

public class ClientThread implements Runnable{

    private final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/38.0.2125.104 Safari/537.36";

    @Override
    public void run() {
        String url1 = "http://localhost:1234/webpages/webpage1.html";
        String url2 = "http://localhost:1234/webpages/webpage2.html";
        String url3 = "http://localhost:1234/webpages/images/malaysiamap.png";

        String url = "";

        Random rand = new Random ();

        int randomNum = rand.nextInt((3-1) +1) +1;
        if(randomNum==1){
            url = url1;
        } else if (randomNum==2){
            url = url2;
        } else if (randomNum==3){
            url = url3;
        }

        try {
            URL obj = new URL (url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");

            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " + url);
            System.out.println("Response code: " + responseCode);
 

            String redirectReplyUrl = con.getResponseMessage();
            System.out.println("Received redirect url: " + redirectReplyUrl);

            StringTokenizer tokens = new StringTokenizer(redirectReplyUrl);
            tokens.nextToken();
            
            redirectReplyUrl = tokens.nextToken();
            System.out.println("After token: " + redirectReplyUrl);

            URL obj2 = new URL (redirectReplyUrl);
            con = (HttpURLConnection) obj2.openConnection();

            con.setRequestMethod("GET");

            con.setRequestProperty("User-Agent", USER_AGENT);

            responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to URL : " +redirectReplyUrl);
            System.out.println("Response code: " + responseCode);


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
