/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package global;

/**
 *
 * @author michel
 */
public class GetOsPlatform {
    
    //os
    private String OS = System.getProperty("os.name").toLowerCase();

    /**
     * Public route om te kijken welk os het is
     *
     * @return os type
     */
    public String getOS() {

        if (isWindows()) {
            return "windows";
        } else if (isMac()) {
            return "mac";
        } else {
            return "false";
        }
    }

    /**
     *
     * @return of het een win pc is
     */
    private boolean isWindows() {
        return (OS.contains("win"));
    }

    /**
     *
     * @return of het een mac is
     */
    private boolean isMac() {
        return (OS.contains("mac"));
    }
}
