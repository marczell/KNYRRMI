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

public class Szerzmodositas implements Serializable {

    
    private String szerzmodazon;
    
    private String szerzmodertek;
    
    private String szerzmodtargy;
    
    private String szerzmoddatum;
    
    private String szerzmodvege;
    
    private String szerzazon;

    public Szerzmodositas() {
    }

    public Szerzmodositas(String szerzmodazon) {
        this.szerzmodazon = szerzmodazon;
    }

    public Szerzmodositas(String szerzmodazon, String szerzazon, String szerzmodertek, String szerzmodtargy, String szerzmoddatum, String szerzmodvege) {
        this.szerzmodazon = szerzmodazon;
        this.szerzazon = szerzazon;
        this.szerzmodertek = szerzmodertek;
        this.szerzmodtargy = szerzmodtargy;
        this.szerzmoddatum = szerzmoddatum;
        this.szerzmodvege = szerzmodvege;
    }

    public String getSzerzmodazon() {
        return szerzmodazon;
    }

    public void setSzerzmodazon(String szerzmodazon) {
        this.szerzmodazon = szerzmodazon;
    }

    public String getSzerzmodertek() {
        return szerzmodertek;
    }

    public void setSzerzmodertek(String szerzmodertek) {
        this.szerzmodertek = szerzmodertek;
    }

    public String getSzerzmodtargy() {
        return szerzmodtargy;
    }

    public void setSzerzmodtargy(String szerzmodtargy) {
        this.szerzmodtargy = szerzmodtargy;
    }

    public String getSzerzmoddatum() {
        return szerzmoddatum;
    }

    public void setSzerzmoddatum(String szerzmoddatum) {
        this.szerzmoddatum = szerzmoddatum;
    }

    public String getSzerzmodvege() {
        return szerzmodvege;
    }

    public void setSzerzmodvege(String szerzmodvege) {
        this.szerzmodvege = szerzmodvege;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (szerzmodazon != null ? szerzmodazon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Szerzmodositas)) {
            return false;
        }
        Szerzmodositas other = (Szerzmodositas) object;
        if ((this.szerzmodazon == null && other.szerzmodazon != null) || (this.szerzmodazon != null && !this.szerzmodazon.equals(other.szerzmodazon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Szerzmodositas[ szerzmodazon=" + szerzmodazon + " ]";
    }
    
}
