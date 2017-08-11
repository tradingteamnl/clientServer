/*
 * Methoden die alle properties file beschikbaar geeft
 */
package clientserver;

import global.FileSystem;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author michel
 */
public class PropertiesGetKey {

    //Maak object fileSystem
    FileSystem fileSystem = new FileSystem();

    //algemene keys
    private String publicKey;

    //bittrex key en of bittrex geladen moet worden
    private String bittrexApiKey, bittrexApiSecretKey;
    private boolean bittrexLoad;

    /**
     * Constructor
     *
     * @throws FileNotFoundException als bestand niet gevonden is
     * @throws IOException als er een error is
     */
    public PropertiesGetKey() throws FileNotFoundException, IOException {

        Properties prop;
        InputStream input = null;

        //prop flie
        prop = new Properties();

        //bestand locatie
        input = new FileInputStream(fileSystem.inputStreamFileLocation("config.properties"));

        // load a properties file
        prop.load(input);

        //Roep alle methoden op die de private variable vullen
        bittrexProperties(prop);
        algemeneKeys(prop);
    }

    /* ============= GETTERS ============= */
    /**
     * bittrexLoad
     *
     * @return bittrexLoad
     */
    public boolean isBittrexLoad() {
        return bittrexLoad;
    }

    /**
     * Getter bittrexApiKey
     *
     * @return bittrexApiKey
     */
    public String getBittrexApiKey() {
        return bittrexApiKey;
    }

    /**
     * bittrexApiSecretKey
     *
     * @return bittrexApiSecretKey
     */
    public String getBittrexApiSecretKey() {
        return bittrexApiSecretKey;
    }

    /**
     * get voor publicKey
     *
     * @return reponse publickey
     */
    public String getPubicKey() {
        return publicKey;
    }

    /* ============= SETTERS ============= */
    /**
     * Zorg voor dat alles van bittrex geladen word
     *
     * @param prop properties file
     */
    private void bittrexProperties(Properties prop) {

        //kijk of het true of false is
        String bittrexBooleanCheck = prop.getProperty("bittrexLoad");

        //als het niet true of false is geef in de console aan het geen geldig input is
        if ("true".equals(bittrexBooleanCheck) || "false".equals(bittrexBooleanCheck)) {

            System.err.println(bittrexBooleanCheck);
            System.err.println(Boolean.getBoolean(bittrexBooleanCheck));

            //string naar boolean
            boolean bittrexLaatInput;

            //geef bittrexLaatInput juiste variable mee
            if ("true".equals(bittrexBooleanCheck)) {
                bittrexLaatInput = true;
            } else {
                bittrexLaatInput = false;
            }

            //if stament of bittrex geladen moet worden
            if (bittrexLaatInput) {
                System.out.println(prop.getProperty("bittrexApiKey"));
                //vul de variable
                this.bittrexApiKey = prop.getProperty("bittrexApiKey");
                this.bittrexApiSecretKey = prop.getProperty("bittrexApiSecret");

                System.out.println("[INFO] [CLIENTSERVER] Bittrex keys worden geladen.");
            } else {
                System.out.println("[INFO] [CLIENTSERVER] Bittrex keys worden niet geladen.");
            }

        } else {
            //Print error uit
            System.err.println("[ERROR] [CLIENTSERVER] Bij bittrexBooleanCheck is het niet true of false waardoor het geen "
                    + "geldige input is. Bittrex word hierdoor niet opgestart!");

            //zet de bittrex variable op false omdat er een error is
            this.bittrexLoad = false;
        }
    }

    /**
     * Methoden om algemene keys te laden
     *
     * @param prop properties bestand
     */
    private void algemeneKeys(Properties prop) {

        //vul de keys
        this.publicKey = prop.getProperty("publicKey");
    }
}
