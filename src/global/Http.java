package global;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * Http request
package global;

/**
 *
 * @author michel_desktop
 */
public class Http {
    
    //url
    private final String URL = "http://localhost:8080";
    
    /**
     * 
     * @param send data die naar de server gezonden moet worden
     * @throws IOException als er een error is 
     */
    public void httpPut(String send) throws IOException {
        URL url = new URL(URL+"/api/put/clientServerIpSave");
        HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
        httpCon.setDoOutput(true);
        httpCon.setRequestMethod("PUT");
        OutputStreamWriter out = new OutputStreamWriter(
                httpCon.getOutputStream());
        out.write(send);
        out.close();
        httpCon.getInputStream();
    }
}
