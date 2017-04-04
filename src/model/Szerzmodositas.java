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

    
    private Integer szerzmodazon;
    
    private int szerzmodertek;
    
    private String szerzmodtargy;
    
    private Date szerzmoddatum;
    
    private Date szerzmodvege;
    
    private Integer szerzazon;

    public Szerzmodositas() {
    }

    public Szerzmodositas(Integer szerzmodazon) {
        this.szerzmodazon = szerzmodazon;
    }

    public Szerzmodositas(Integer szerzmodazon, Integer szerzazon, int szerzmodertek, String szerzmodtargy, Date szerzmoddatum, Date szerzmodvege) {
        this.szerzmodazon = szerzmodazon;
        this.szerzazon = szerzazon;
        this.szerzmodertek = szerzmodertek;
        this.szerzmodtargy = szerzmodtargy;
        this.szerzmoddatum = szerzmoddatum;
        this.szerzmodvege = szerzmodvege;
    }

    public Integer getSzerzmodazon() {
        return szerzmodazon;
    }

    public void setSzerzmodazon(Integer szerzmodazon) {
        this.szerzmodazon = szerzmodazon;
    }

    public int getSzerzmodertek() {
        return szerzmodertek;
    }

    public void setSzerzmodertek(int szerzmodertek) {
        this.szerzmodertek = szerzmodertek;
    }

    public String getSzerzmodtargy() {
        return szerzmodtargy;
    }

    public void setSzerzmodtargy(String szerzmodtargy) {
        this.szerzmodtargy = szerzmodtargy;
    }

    public Date getSzerzmoddatum() {
        return szerzmoddatum;
    }

    public void setSzerzmoddatum(Date szerzmoddatum) {
        this.szerzmoddatum = szerzmoddatum;
    }

    public Date getSzerzmodvege() {
        return szerzmodvege;
    }

    public void setSzerzmodvege(Date szerzmodvege) {
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
