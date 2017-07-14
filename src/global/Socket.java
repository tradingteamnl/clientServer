/*
 * Socket
 */
package global;

import JSON.JSONObject;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 *
 * @author michel
 */
public class Socket {

    public Socket() throws IOException {
        /*int port = 9099;
        String serverName = "127.0.0.1";

        System.out.println("Connecting to " + serverName + " on port " + port);
        java.net.Socket client = new java.net.Socket(serverName, port);

        System.out.println("Just connected to " + client.getRemoteSocketAddress());
        OutputStream outToServer = client.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);

        out.writeUTF("Client send " + client.getLocalSocketAddress());
        InputStream inFromServer = client.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);

        System.out.println("Server says " + in.readUTF());
        client.close();*/
    }

    public void setUpConnenction(JSONObject dataToSend) throws IOException {
        int port = 9099;
        String serverName = "127.0.0.1";

        System.out.println("Connecting to " + serverName + " on port " + port);
        java.net.Socket client = new java.net.Socket(serverName, port);

        System.out.println("Just connected to " + client.getRemoteSocketAddress());
        OutputStream outToServer = client.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);

        out.writeUTF(""+dataToSend);
        InputStream inFromServer = client.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);

        System.out.println("Server says " + in.readUTF());
        client.close();
    }
}
