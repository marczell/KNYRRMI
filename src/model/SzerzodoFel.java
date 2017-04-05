/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author zsofi
 */

public class SzerzodoFel implements Serializable {

    
    private String szfid;
    
    private String szerzodofel;
    
    private String szekhelyVaros;
    
    private String szekhelyIranyitoszam;
    
    private String szekhelyKozterulet;
    
    private String szekhelyHazszam;
    
    private String telefonszam;
    
    private String faxszam;
    
    private String eMail;
    
    private String cegjegyzekszam;
    
    private String adoszam;
    
    private String kapcsolattartoNeve;
    
    private String kapcsolattartoTel;
   
    private String kapcsolattartoEmail;
    

    public SzerzodoFel() {
    }

    public SzerzodoFel(String szfid) {
        this.szfid = szfid;
    }

    public SzerzodoFel(String szfid, String szerzodofel, String szekhelyVaros, String szekhelyIranyitoszam, String szekhelyKozterulet, String szekhelyHazszam, String telefonszam, String faxszam, String eMail, String cegjegyzekszam, String adoszam, String kapcsolattartoNeve, String kapcsolattartoTel, String kapcsolattartoEmail) {
        this.szfid = szfid;
        this.szerzodofel = szerzodofel;
        this.szekhelyVaros = szekhelyVaros;
        this.szekhelyIranyitoszam = szekhelyIranyitoszam;
        this.szekhelyKozterulet = szekhelyKozterulet;
        this.szekhelyHazszam = szekhelyHazszam;
        this.telefonszam = telefonszam;
        this.faxszam = faxszam;
        this.eMail = eMail;
        this.cegjegyzekszam = cegjegyzekszam;
        this.adoszam = adoszam;
        this.kapcsolattartoNeve = kapcsolattartoNeve;
        this.kapcsolattartoTel = kapcsolattartoTel;
        this.kapcsolattartoEmail = kapcsolattartoEmail;
    }

    public String getSzfid() {
        return szfid;
    }

    public void setSzfid(String szfid) {
        this.szfid = szfid;
    }

    public String getSzerzodofel() {
        return szerzodofel;
    }

    public void setSzerzodofel(String szerzodofel) {
        this.szerzodofel = szerzodofel;
    }

    public String getSzekhelyVaros() {
        return szekhelyVaros;
    }

    public void setSzekhelyVaros(String szekhelyVaros) {
        this.szekhelyVaros = szekhelyVaros;
    }

    public String getSzekhelyIranyitoszam() {
        return szekhelyIranyitoszam;
    }

    public void setSzekhelyIranyitoszam(String szekhelyIranyitoszam) {
        this.szekhelyIranyitoszam = szekhelyIranyitoszam;
    }

    public String getSzekhelyKozterulet() {
        return szekhelyKozterulet;
    }

    public void setSzekhelyKozterulet(String szekhelyKozterulet) {
        this.szekhelyKozterulet = szekhelyKozterulet;
    }

    public String getSzekhelyHazszam() {
        return szekhelyHazszam;
    }

    public void setSzekhelyHazszam(String szekhelyHazszam) {
        this.szekhelyHazszam = szekhelyHazszam;
    }

    public String getTelefonszam() {
        return telefonszam;
    }

    public void setTelefonszam(String telefonszam) {
        this.telefonszam = telefonszam;
    }

    public String getFaxszam() {
        return faxszam;
    }

    public void setFaxszam(String faxszam) {
        this.faxszam = faxszam;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getCegjegyzekszam() {
        return cegjegyzekszam;
    }

    public void setCegjegyzekszam(String cegjegyzekszam) {
        this.cegjegyzekszam = cegjegyzekszam;
    }

    public String getAdoszam() {
        return adoszam;
    }

    public void setAdoszam(String adoszam) {
        this.adoszam = adoszam;
    }

    public String getKapcsolattartoNeve() {
        return kapcsolattartoNeve;
    }

    public void setKapcsolattartoNeve(String kapcsolattartoNeve) {
        this.kapcsolattartoNeve = kapcsolattartoNeve;
    }

    public String getKapcsolattartoTel() {
        return kapcsolattartoTel;
    }

    public void setKapcsolattartoTel(String kapcsolattartoTel) {
        this.kapcsolattartoTel = kapcsolattartoTel;
    }

    public String getKapcsolattartoEmail() {
        return kapcsolattartoEmail;
    }

    public void setKapcsolattartoEmail(String kapcsolattartoEmail) {
        this.kapcsolattartoEmail = kapcsolattartoEmail;
    }

   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (szfid != null ? szfid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SzerzodoFel)) {
            return false;
        }
        SzerzodoFel other = (SzerzodoFel) object;
        if ((this.szfid == null && other.szfid != null) || (this.szfid != null && !this.szfid.equals(other.szfid))) {
            return false;
        }
        return true;
    }

    

    
    
}
