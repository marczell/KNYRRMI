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
public class SzerzodoFel implements Serializable{
    
    private int szfid;
    private String szerzodoFel;
    private String szekhelyVaros;
    private int szekhelyIranyitoszam;
    private String szekhelyKozterulet;
    private int szekhelyHazszam;
    private String telefonszam;
    private String faxszam;
    private String email;
    private String cegjegyzekszam;
    private String adoszam;
    private String kapcsolattartoNeve;
    private String kapcsolattartoTel;
    private String kapcsolattartoEmail;
    
    public SzerzodoFel (int szfid, String szerzodoFel, String szekhelyVaros, int szekhelyIranyitoszam, String szekhelyKozterulet, int szekhelyHazszam, String telefonszam, String faxszam, String email, String cegjegyzekszam, String adoszam, String kapcsolattartoNeve, String kapcsolattartoTel, String kapcsolattartoEmail){

    this.szfid = szfid;
    this.szerzodoFel = szerzodoFel;
    this.szekhelyVaros = szekhelyVaros;
    this.szekhelyIranyitoszam = szekhelyIranyitoszam;
    this.szekhelyKozterulet = szekhelyKozterulet;
    this.szekhelyHazszam = szekhelyHazszam;
    this.telefonszam = telefonszam;
    this.faxszam = faxszam;
    this.email = email;
    this.cegjegyzekszam = cegjegyzekszam;
    this.adoszam = adoszam;
    this.kapcsolattartoNeve = kapcsolattartoNeve;
    this.kapcsolattartoTel = kapcsolattartoTel;
    this.kapcsolattartoEmail = kapcsolattartoEmail;

}

    public int getSzfid() {
        return szfid;
    }

    public void setSzfid(int szfid) {
        this.szfid = szfid;
    }

    public String getSzerzodoFel() {
        return szerzodoFel;
    }

    public void setSzerzodoFel(String szerzodoFel) {
        this.szerzodoFel = szerzodoFel;
    }

    public String getSzekhelyVaros() {
        return szekhelyVaros;
    }

    public void setSzekhelyVaros(String szekhelyVaro) {
        this.szekhelyVaros = szekhelyVaro;
    }

    public int getSzekhelyIranyitoszam() {
        return szekhelyIranyitoszam;
    }

    public void setSzekhelyIranyitoszam(int szekhelyIranyitoszam) {
        this.szekhelyIranyitoszam = szekhelyIranyitoszam;
    }

    public String getSzekhelyKozterulet() {
        return szekhelyKozterulet;
    }

    public void setSzekhelyKozterulet(String szekhelyKozterulet) {
        this.szekhelyKozterulet = szekhelyKozterulet;
    }

    public int getSzekhelyHazszam() {
        return szekhelyHazszam;
    }

    public void setSzekhelyHazszam(int szekhelyHazszam) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    
    
}
