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
    private String szekhelyVaro;
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

    public String getSzekhelyVaro() {
        return szekhelyVaro;
    }

    public void setSzekhelyVaro(String szekhelyVaro) {
        this.szekhelyVaro = szekhelyVaro;
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
