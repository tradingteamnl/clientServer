/*
 * Start bestand
 */
package clientserver;

import JSON.JSONArray;
import JSON.JSONObject;
import bittrex.GetBalance;
import global.GetIpAddress;
import global.Socket;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author michel_desktop
 */
public class ClientServer {

    //variable die gevuld moeten worden met de data uit de properties file
    //bittrex keys
    /*private static String bittrexApiKey;
    private static String bittrexApiKeySecret;
    private static boolean loadBittrex;*/
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //variable
        String bittrexApiKey = null;
        String bittrexApiKeySecret = null;
        boolean loadBittrex = false;
        
        //public key
        String publicKey= null;
        
        //maak objct aan
        PropertiesGetKey propertiesGetKey;

        //probeer object propertiesGetKey
        try {
            //maak object aan
            propertiesGetKey = new PropertiesGetKey();
            
            //vul varirable
            loadBittrex = propertiesGetKey.isBittrexLoad();
            bittrexApiKey = propertiesGetKey.getBittrexApiKey();
            bittrexApiKeySecret = propertiesGetKey.getBittrexApiSecretKey();
            
            //laat algemene keys
            publicKey = propertiesGetKey.getPubicKey();
            
        } catch (FileNotFoundException ex) {
            
            //Error als het bestand niet is gevonden
            System.err.println("[ERROR] [CLIENTSERVER] Config.propersties kan niet worden gevonden!");
            System.err.println("[ERROR] [CLIENTSERVER] " + ex);
            System.exit(1);
        } catch (IOException ex) {
            
            //Error als niet alle data goed verwerkt word
            System.err.println("[ERROR] [CLIENTSERVER] Er is een probleem om alle propersties in te laden!");
            System.err.println("[ERROR] [CLIENTSERVER] " + ex);
            System.exit(1);
        }
        
        //laat alle variable
        //fillVariable();
        System.out.println("test" + bittrexApiKey);
        
        //maak klasse getBalance aan
        GetBalance getBalance = new GetBalance(bittrexApiKey, bittrexApiKeySecret, publicKey);

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

            //stuur het ip address door naar de server
            JSONObject sendIpObject = new JSONObject();
            sendIpObject.put("methoden", "getLocalIp");
            sendIpObject.put("auth", "false");
            sendIpObject.put("data", ip4Address);

            System.out.println(sendIpObject);

            try {
                Socket socket = new Socket();
                socket.sendData(sendIpObject);
            } catch (IOException e) {
                System.err.println(e);
            }

        } catch (Exception ex) {
            System.err.println("Er is een probleem om het ip address op te vragen.");
            System.err.println(ex);
        }
    }

}

//the spy who loved me