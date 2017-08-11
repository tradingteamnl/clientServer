/*
 * Om de balance op bittrex op te vragen
 */
package bittrex;

//import
import JSON.JSONArray;
import JSON.JSONObject;
import global.Socket;
import java.io.IOException;


/**
 *
 * @author michel
 */
public class GetBalance {
    
    private final String API_KEY, SECRET_KEY;
    private final String PUBLIC_KEY;

    //maak bittrexRequest classe
    BittrexRequest bittrexRequest;
    Socket socket = new Socket();
    
    /**
     * Constructor
     * @param apiKey public key
     * @param secretKey private key
     * @param publicKey public key
     */
    public GetBalance(String apiKey, String secretKey, String publicKey) {
        this.API_KEY = apiKey;
        this.SECRET_KEY = secretKey;
        this.PUBLIC_KEY = publicKey;
        
        //laat de constructor van bittrexRequest
        bittrexRequest = new BittrexRequest(API_KEY, SECRET_KEY);
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
        
        //maak een exchange array aan
        JSONArray array = new JSONArray();
        
        //kijk of het gelukt is
        boolean succes = object.getBoolean("success");
        
        //als het niet goed is gelukt laat dan het programma stoppen door middel van een exceptions
        //als het werkt word bittrex in de lisjt toegevoegd van exchange. Zodat de server van welke exchange er allemaal data binnen komt
        if(!succes){
            
            //Exception
            throw new Exception("Er is een probleem bij balance op te vragen bij bittrex."
                    +"\nEr van bittrex is "+object.getString("message"));
        } else {
            array.put("bittrex");
        }
        
        
        //maak JSONObject data
        JSONObject objectData = new JSONObject();
        objectData.put("poloniexBalance", "test");
        objectData.put("bittrexBalance", object.getJSONArray("result"));
        objectData.put("exchangeArray", array);
        
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