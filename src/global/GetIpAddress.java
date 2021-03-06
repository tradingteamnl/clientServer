/*
 * Vraag ip address op
 */
package global;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import JSON.JSONArray;
import java.net.SocketException;
import static java.lang.System.out;

/**
 *
 * @author michel_desktop
 */
public class GetIpAddress {

    //private JSONArray
    private JSONArray ipArray;

    /**
     * Deze methoden probeerd een ip address te krijgen
     *
     * @return Ip4 adres
     * @throws Exception als er geen ip beschikbaar is
     */
    public JSONArray getIp4() throws Exception {

        //JSONArray
        ipArray = new JSONArray();

        //alle ip adres te krijgen
        Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
        for (NetworkInterface netint : Collections.list(nets)) {
            displayInterfaceInformation(netint);
        }

        //return de array
        return ipArray;

    }

    /**
     * Alle ip informatie
     *
     * @param netint ip
     * @throws SocketException Bij een error
     */
    private void displayInterfaceInformation(NetworkInterface netint) throws SocketException {
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {

            //ipRaw
            String ipRaw = "" + inetAddress;

            //Ip
            String ip = ipRaw.replace("/", "");

            //vul de ip adres in de array
            ipArray.put(ip);
        }
        out.printf("\n");
    }

}
