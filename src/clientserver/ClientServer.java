/*
 * Start bestand
 */
package clientserver;

import JSON.JSONArray;
import JSON.JSONObject;
import bittrex.GetBalance;
import global.GetIpAddress;
import global.Socket;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author michel_desktop
 */
public class ClientServer {
    
    //bittrex keys
    private static String bittrexApiKey = "885a6bef5a104692b26aed858b1e5475";
    private static String bittrexApiSecret = "c55cc5dc80244e48a569e2286fad7176";

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //maak klasse getBalance aan
        GetBalance getBalance = new GetBalance(bittrexApiKey, bittrexApiSecret);
        
        //maak timetask
        TimerTask task = new TimerTask() {

            @Override
            public void run() {
                try {
                    //reload alle methoden om de minut
                    getBalance.getBalance();
                    
                } catch (Exception ex) {
                    System.err.println(ex);
                }
            }
        };

        //great timer
        Timer timer = new Timer();

        //timer schema
        timer.schedule(task, new Date(), 60000);

        //update ip address bij de server
        //sendIp();

        /* 
        Socket socket;
        try {
            socket = new Socket();
            socket.test();
        } catch (IOException ex) {
        }
         */
    }

    /**
     * Deze methoden stuur het ip door naar de main server van het programma
     * zodat de client interface weet op welk intern ip de client server draait
     */
    private static void sendIp() {

        try {
            //deze code is voor het krijgen van het ip4 address
            GetIpAddress ipAddress = new GetIpAddress();
            JSONArray ip4Address = ipAddress.getIp4();

            System.out.println(ip4Address);

            //stuur het ip address door naar de server
            JSONObject sendIpObject = new JSONObject();
            sendIpObject.put("methoden", "getLocalIp");
            sendIpObject.put("auth", "false");
            sendIpObject.put("data", ip4Address);

            System.out.println(sendIpObject);

            try {
                Socket socket = new Socket();
                socket.sendData(sendIpObject);
            } catch (Exception e) {
                System.err.println(e);
            }

        } catch (Exception ex) {
            System.err.println("Er is een probleem om het ip address op te vragen.");
            System.err.println(ex);
        }
    }
}
