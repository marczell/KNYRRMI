/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author zsofi
 */

public class Kozbeszerzes implements Serializable {

   
    private String sorszam;
   
    private String besznev;
    
    private String keljarasazon;
    
    private String bertek;
    
    private String kozbeszerzesieljarasfajta;
   
    private String szerzodesfajtaja;
    
    private String cpvkod;
    
    private String projekt;    
   
    private String kozbeszkezdete;
   
    private String kozbeszvege;
   
  

    public Kozbeszerzes() {
    }

    public Kozbeszerzes(String sorszam) {
        this.sorszam = sorszam;
    }

    public Kozbeszerzes(String sorszam, String besznev, String keljarasazon, String bertek, String kozbeszerzesieljarasfajta, String szerzodesfajtaja, String cpvkod, String projekt, String kozbeszkezdete, String kozbeszvege) {
        this.sorszam = sorszam;
        this.besznev = besznev;
        this.keljarasazon = keljarasazon;
        this.bertek = bertek;
        this.kozbeszerzesieljarasfajta = kozbeszerzesieljarasfajta;
        this.szerzodesfajtaja = szerzodesfajtaja;
        this.cpvkod = cpvkod;
        this.projekt = projekt;
        this.kozbeszkezdete = kozbeszkezdete;
        this.kozbeszvege = kozbeszvege;
    }

    public String getSorszam() {
        return sorszam;
    }

    public void setSorszam(String sorszam) {
        this.sorszam = sorszam;
    }

    public String getBesznev() {
        return besznev;
    }

    public void setBesznev(String besznev) {
        this.besznev = besznev;
    }

    public String getKeljarasazon() {
        return keljarasazon;
    }

    public void setKeljarasazon(String keljarasazon) {
        this.keljarasazon = keljarasazon;
    }

    public String getBertek() {
        return bertek;
    }

    public void setBertek(String bertek) {
        this.bertek = bertek;
    }

    public String getKozbeszkezdete() {
        return kozbeszkezdete;
    }

    public void setKozbeszkezdete(String kozbeszkezdete) {
        this.kozbeszkezdete = kozbeszkezdete;
    }

    public String getKozbeszvege() {
        return kozbeszvege;
    }

    public void setKozbeszvege(String kozbeszvege) {
        this.kozbeszvege = kozbeszvege;
    }

  

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sorszam != null ? sorszam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Kozbeszerzes)) {
            return false;
        }
        Kozbeszerzes other = (Kozbeszerzes) object;
        if ((this.sorszam == null && other.sorszam != null) || (this.sorszam != null && !this.sorszam.equals(other.sorszam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Kozbeszerzes[ sorszam=" + sorszam + " ]";
    }
    
}
