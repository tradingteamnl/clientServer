/*
 * Start bestand
 */
package clientserver;

import JSON.JSONArray;
import JSON.JSONObject;
import global.FileSystem;
import global.GetIpAddress;
import global.Socket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.Formatter;

/**
 *
 * @author michel_desktop
 */
public class Starter {
    
    //maak objecten
    FileSystem fileSystem = new FileSystem();
    
    /**
     * Start het programma
     */
    public Starter(){
        sendIp();
    }
    
    /**
     * Deze methoden stuur het ip door naar de main server van het programma
     * zodat de client interface weet op welk intern ip de client server draait
     */
    private void sendIp() {
        
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
