/*
 * Vraag ip address op
 */
package global;

import java.net.InetAddress;

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
    
        //krijg het ip adres
        String ip = InetAddress.getLocalHost().getHostAddress();
        System.out.println("Current IP address : " + ip);

        //kijk of er een internet verbinding is
        if(!"127.0.0.1".equals(ip)){
            return ip;
        } else {
            //geen internet verbinding
            throw new Exception("Er is geen internet verbinding beschikbaar");
        }
        
        
        
        //return ip.getHostAddress();
        /*
            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            System.out.print("Current MAC address : ");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            System.out.println(sb.toString());
         */
    }

}
