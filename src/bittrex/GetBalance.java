/*
 * Om de balance op bittrex op te vragen
 */
package bittrex;

//import
import JSON.JSONObject;
import global.Socket;
import java.io.IOException;


/**
 *
 * @author michel
 */
public class GetBalance {
    
    private String apiKey, secretKey;
    private final String PUBLIC_KEY = "BLABLABLA";

    //maak bittrexRequest classe
    BittrexRequest bittrexRequest;
    Socket socket = new Socket();
    
    /**
     * Constructor
     * @param apiKey public key
     * @param secretKey private key
     */
    public GetBalance(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
        
        bittrexRequest = new BittrexRequest(apiKey, secretKey);
    }
    
    /**
     * 
     * @throws java.lang.Exception
     */
    public void getBalance() throws Exception{
        
        //vraag de balance op van alle coins
        String balanceString = bittrexRequest.getBalances();
        System.out.println(balanceString);
        //maak er een JSONobject van
        JSONObject object = new JSONObject(balanceString);
        JSONObject sendObject = new JSONObject();
        
        //kijk of het gelukt is
        boolean succes = object.getBoolean("success");
        
        //als het niet goed is gelukt laat dan het programma stoppen door middel van een exceptions
        if(!succes){
            
            //Exception
            throw new Exception("Er is een probleem bij balance op te vragen bij bittrex."
                    +"\nEr van bittrex is "+object.getString("message"));
        }
        
        //maak JSONObject data
        JSONObject objectData = new JSONObject();
        objectData.put("bittrexBalance", object.getJSONArray("result"));
        
        //voer licentie toe
        sendObject.put("publicKey", PUBLIC_KEY);
        sendObject.put("methoden", "balanceUpdate");
        sendObject.put("data", objectData);
    
        //probeer data naar de server te verturen
        try {
            socket.sendData(sendObject);
        } catch (IOException ex) {
            System.err.println("[ERROR] [GETBALANCE] Kan de nieuwe balance niet naar de server versturen.");
        }
    
    
    }
}