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

    
    private Integer sorszam;
   
    private Integer szerzazon;
    
    private int szerzodeserteke;
    
    private String szerztargy;
   
    private Date szerzodeskotesdatuma;
    
    private Date szerzodestervezettlezarasa;
    
    private Integer szerzodoFel;
   

    public Szerzodes() {
    }

    public Szerzodes(Integer sorszam, Integer szerzazon, Integer szerzodoFel, int szerzodeserteke, String szerztargy, Date szerzodeskotesdatuma, Date szerzodestervezettlezarasa  ) {
        this.sorszam = sorszam;
        this.szerzazon = szerzazon;
        this.szerzodoFel = szerzodoFel;
        this.szerzodeserteke = szerzodeserteke;
        this.szerztargy = szerztargy;
        this.szerzodeskotesdatuma = szerzodeskotesdatuma;
        this.szerzodestervezettlezarasa = szerzodestervezettlezarasa;
        
        
    }

    public Integer getSzerzazon() {
        return szerzazon;
    }

    public void setSzerzazon(Integer szerzazon) {
        this.szerzazon = szerzazon;
    }

    public int getSzerzodeserteke() {
        return szerzodeserteke;
    }

    public void setSzerzodeserteke(int szerzodeserteke) {
        this.szerzodeserteke = szerzodeserteke;
    }

    public String getSzerztargy() {
        return szerztargy;
    }

    public void setSzerztargy(String szerztargy) {
        this.szerztargy = szerztargy;
    }

    public Date getSzerzodeskotesdatuma() {
        return szerzodeskotesdatuma;
    }

    public void setSzerzodeskotesdatuma(Date szerzodeskotesdatuma) {
        this.szerzodeskotesdatuma = szerzodeskotesdatuma;
    }

    public Date getSzerzodestervezettlezarasa() {
        return szerzodestervezettlezarasa;
    }

    public void setSzerzodestervezettlezarasa(Date szerzodestervezettlezarasa) {
        this.szerzodestervezettlezarasa = szerzodestervezettlezarasa;
    }

   

   

    public int getSzerzodoFel() {
        return szerzodoFel;
    }

    public void setSzerzodoFel(int szerzodoFel) {
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
