/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Marcell
 */
public class ProjektEgybentartas implements Serializable{
    
    private String projektNev;
    private String projektErtek;

    public ProjektEgybentartas(String projektNev, String projektErtek) {
        this.projektNev = projektNev;
        this.projektErtek = projektErtek;
    }

    public String getProjektNev() {
        return projektNev;
    }

    public void setProjektNev(String projektNev) {
        this.projektNev = projektNev;
    }

    public String getProjektErtek() {
        return projektErtek;
    }

    public void setProjektErtek(String projektErtek) {
        this.projektErtek = projektErtek;
    }
    
}
