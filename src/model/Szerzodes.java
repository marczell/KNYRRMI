/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author zsofi
 */

public class Szerzodes implements Serializable {

    
    private String sorszam;
   
    private String szerzazon;
    
    private String szerzodeserteke;
    
    private String szerztargy;
   
    private String szerzodeskotesdatuma;
    
    private String szerzodestervezettlezarasa;
    
    private String szerzodoFel;
   

    public Szerzodes() {
    }

    public Szerzodes(String sorszam, String szerzazon, String szerzodoFel, String szerzodeserteke, String szerztargy, String szerzodeskotesdatuma, String szerzodestervezettlezarasa  ) {
        this.sorszam = sorszam;
        this.szerzazon = szerzazon;
        this.szerzodoFel = szerzodoFel;
        this.szerzodeserteke = szerzodeserteke;
        this.szerztargy = szerztargy;
        this.szerzodeskotesdatuma = szerzodeskotesdatuma;
        this.szerzodestervezettlezarasa = szerzodestervezettlezarasa;
        
        
    }

    public String getSzerzazon() {
        return szerzazon;
    }

    public void setSzerzazon(String szerzazon) {
        this.szerzazon = szerzazon;
    }

    public String getSzerzodeserteke() {
        return szerzodeserteke;
    }

    public void setSzerzodeserteke(String szerzodeserteke) {
        this.szerzodeserteke = szerzodeserteke;
    }

    public String getSzerztargy() {
        return szerztargy;
    }

    public void setSzerztargy(String szerztargy) {
        this.szerztargy = szerztargy;
    }

    public String getSzerzodeskotesdatuma() {
        return szerzodeskotesdatuma;
    }

    public void setSzerzodeskotesdatuma(String szerzodeskotesdatuma) {
        this.szerzodeskotesdatuma = szerzodeskotesdatuma;
    }

    public String getSzerzodestervezettlezarasa() {
        return szerzodestervezettlezarasa;
    }

    public void setSzerzodestervezettlezarasa(String szerzodestervezettlezarasa) {
        this.szerzodestervezettlezarasa = szerzodestervezettlezarasa;
    }

   

   

    public String getSzerzodoFel() {
        return szerzodoFel;
    }

    public void setSzerzodoFel(String szerzodoFel) {
        this.szerzodoFel = szerzodoFel;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (szerzazon != null ? szerzazon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Szerzodes)) {
            return false;
        }
        Szerzodes other = (Szerzodes) object;
        if ((this.szerzazon == null && other.szerzazon != null) || (this.szerzazon != null && !this.szerzazon.equals(other.szerzazon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Szerzodes[ szerzazon=" + szerzazon + " ]";
    }
    
}
