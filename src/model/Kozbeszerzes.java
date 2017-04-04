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

   
    private Integer sorszam;
   
    private String besznev;
    
    private String keljarasazon;
    
    private int bertek;
    
    private int kozbeszerzesieljarasfajta;
   
    private int szerzodesfajtaja;
    
    private int cpvkod;
    
    private int projekt;    
   
    private Date kozbeszkezdete;
   
    private Date kozbeszvege;
   
    private List<Szerzodes> szerzodesList;

    public Kozbeszerzes() {
    }

    public Kozbeszerzes(Integer sorszam) {
        this.sorszam = sorszam;
    }

    public Kozbeszerzes(Integer sorszam, String besznev, String keljarasazon, int bertek, int kozbeszerzesieljarasfajta, int szerzodesfajtaja, int cpvkod, int projekt, Date kozbeszkezdete, Date kozbeszvege) {
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

    public Integer getSorszam() {
        return sorszam;
    }

    public void setSorszam(Integer sorszam) {
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

    public int getBertek() {
        return bertek;
    }

    public void setBertek(int bertek) {
        this.bertek = bertek;
    }

    public Date getKozbeszkezdete() {
        return kozbeszkezdete;
    }

    public void setKozbeszkezdete(Date kozbeszkezdete) {
        this.kozbeszkezdete = kozbeszkezdete;
    }

    public Date getKozbeszvege() {
        return kozbeszvege;
    }

    public void setKozbeszvege(Date kozbeszvege) {
        this.kozbeszvege = kozbeszvege;
    }

    public List<Szerzodes> getSzerzodesList() {
        return szerzodesList;
    }

    public void setSzerzodesList(List<Szerzodes> szerzodesList) {
        this.szerzodesList = szerzodesList;
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
