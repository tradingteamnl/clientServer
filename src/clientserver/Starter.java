/*
 * Start bestand
 */
package clientserver;

import global.GetIpAddress;

/**
 *
 * @author michel_desktop
 */
public class Starter {
    
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
            String ip4Address = ipAddress.getIp4();
            
            //stuur het ip address door naar de hoogd server
            
            
        } catch (Exception ex) {
            System.err.println("Er is een probleem om het ip address op te vragen.");
            System.err.println(ex);
        }
    }
}
