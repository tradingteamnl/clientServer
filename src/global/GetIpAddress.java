/*
 * Vraag ip address op
 */
package global;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;

/**
 *
 * @author michel_desktop
 */
public class GetIpAddress {

    /**
     * Deze methoden probeerd een ip address te krijgen
     *
     * @return Ip4 adres
     * @throws Exception als er geen ip beschikbaar is
     */
    public String getIp4() throws Exception {

        //collect netwerk kaarten
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {

            //kijk of de kaart en0
            if ("en0".equals(netint.getDisplayName())) {

                //ip in array
                Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();

                //kijk op het een ip6 of ip4 is
                for (InetAddress inetAddress : Collections.list(inetAddresses)) {
                    System.out.printf("InetAddress: %s\n", inetAddress);

                    //zet inetaddress om naar een string
                    String ipRaw = "" + inetAddress;

                    //kijk hoe lang de string is zodat je weet of het ip4 of ip6 is
                    /*if(){
                    
                    } 
                    
                    
                    
                     */
                    
                    return ipRaw;
                }

                System.err.println("blabla");
            } else {
                throw new Exception("Geen juiste ip drivers beschikbaar");
            }
        }
        //als er geen for loop is
        throw new Exception("Geen juiste ip drivers beschikbaar");
    }

}
