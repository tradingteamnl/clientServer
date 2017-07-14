/*
 * Start bestand
 */
package clientserver;

import JSON.JSONArray;
import global.FileSystem;
import global.GetIpAddress;
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
            //System.out.println(ip4Address);
            //kijk of het ip klopt met het ip address wat is opgeslagen in het systeem 
            
            
            //stuur het ip address door naar de hoogd server
            
            
        } catch (Exception ex) {
            System.err.println("Er is een probleem om het ip address op te vragen.");
            System.err.println(ex);
        }
    }
}
