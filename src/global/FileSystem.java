/*
 * Bestand systeem
 */
package global;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author michel
 */
/**
 *
 * @author michel_desktop
 */
public class FileSystem {
    //maakt objecten aan

    JFileChooser fr = new JFileChooser();
    GetOsPlatform getosplatform = new GetOsPlatform();

    /**
     * Constructor
     */
    public FileSystem() {
        //kijk of de folder bestaat
        if (!Files.exists(Paths.get(fileLocation()))) {
            System.out.println("No Folder");

            //folder word aangemaakt.
            File file = new File(fileLocation());
            file.mkdir();
            System.out.println("Folder created");
        } else {
            System.out.println("Folder exists");
        }
    }

    /**
     * Deze methoden stuur de locatie terug waar het moet worden opgeslagen
     *
     * @return save locatie
     */
    private String fileLocation() {
        FileSystemView fw = fr.getFileSystemView();

        if ("windows".equals(getosplatform.getOS())) {
            return Paths.get("C:\\trading").toString() + "\\";
        }

        if ("mac".equals(getosplatform.getOS())) {
            return Paths.get(fw.getDefaultDirectory() + "//Documents//trading").toString() + "//";
        }

        return Paths.get(fw.getDefaultDirectory() + "//Documents//trading").toString() + "//";
    }

    /**
     * Deze methoden slaat het bestand op
     *
     * @param fileName bestands naam
     * @param FileData data die opgeslagen moet worden
     * @throws java.io.FileNotFoundException als er iets mist gaat
     * @throws java.io.UnsupportedEncodingException als er iets mist gaat
     */
    public void saveFile(String fileName, String FileData) throws FileNotFoundException, UnsupportedEncodingException {

        //hier word het bestand omgeslagen
        PrintWriter writer;
        writer = new PrintWriter(fileLocation() + fileName, "UTF-8");
        writer.println(FileData);
        writer.close();
    }

    /**
     * Leest bestanden in
     *
     * @param file Bestands naam
     * @return data van het bestand
     * @throws java.io.IOException als er iets fout gaat
     */
    public String readFile(String file) throws IOException {
        FileSystemView fw = fr.getFileSystemView();
        return new String(Files.readAllBytes(Paths.get(fileLocation() + file)));
    }
}

