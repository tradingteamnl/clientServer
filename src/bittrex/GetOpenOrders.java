/*
 * Run al open bittrex orders
 */
package bittrex;

import JSON.JSONObject;
import global.Socket;

/**
 *
 * @author michel
 */
public class GetOpenOrders {

    private String apiKey, secretKey;
    private final String PUBLIC_KEY = "BLABLABLA";

    //maak bittrexRequest classe
    BittrexRequest bittrexRequest;
    Socket socket = new Socket();

    /**
     * Constructor
     *
     * @param apiKey public key
     * @param secretKey private key
     */
    public GetOpenOrders(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;

        bittrexRequest = new BittrexRequest(apiKey, secretKey);
    }
    
    public void updateOrders(){
        
        //vraag alle open orders op
        String openOrders = bittrexRequest.getOpenOrders();
        
        //maak er een object van
        JSONObject openOrdersObject = new JSONObject(openOrders);
        
        
        
        
        
        
    }
}
