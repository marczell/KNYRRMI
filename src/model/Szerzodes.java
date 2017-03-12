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
public class Szerzodes implements Serializable{
    
    private String sorszam;
    private String szerzodesNeve;
    private String szerzodoFel;
    private String kozbeszerzesFajtaja;
    private String szerzodesFajtaja;
    private String cpvKod;
    private String projekt;
    private String szerzodesKotesDatum;
    private String szerzodesTervezettLezarasa;
    private String szerzodesErteke;

    public Szerzodes(String sorszam, String szerzodesNeve, String szerzodoFel, String kozbeszerzesFajtaja, String szerzodesFajtaja, String cpvKod, String projekt, String szerzodesKotesDatum, String szerzodesTervezettLezarasa, String szerzodesErteke) {
        this.sorszam = sorszam;
        this.szerzodesNeve = szerzodesNeve;
        this.szerzodoFel = szerzodoFel;
        this.kozbeszerzesFajtaja = kozbeszerzesFajtaja;
        this.szerzodesFajtaja = szerzodesFajtaja;
        this.cpvKod = cpvKod;
        this.projekt = projekt;
        this.szerzodesKotesDatum = szerzodesKotesDatum;
        this.szerzodesTervezettLezarasa = szerzodesTervezettLezarasa;
        this.szerzodesErteke = szerzodesErteke;
    }

    public String getSorszam() {
        return sorszam;
    }

    public void setSorszam(String sorszam) {
        this.sorszam = sorszam;
    }

    public String getSzerzodesNeve() {
        return szerzodesNeve;
    }

    public void setSzerzodesNeve(String szerzodesNeve) {
        this.szerzodesNeve = szerzodesNeve;
    }

    public String getSzerzodoFel() {
        return szerzodoFel;
    }

    public void setSzerzodoFel(String szerzodoFel) {
        this.szerzodoFel = szerzodoFel;
    }

    public String getKozbeszerzesFajtaja() {
        return kozbeszerzesFajtaja;
    }

    public void setKozbeszerzesFajtaja(String kozbeszerzesFajtaja) {
        this.kozbeszerzesFajtaja = kozbeszerzesFajtaja;
    }

    public String getSzerzodesFajtaja() {
        return szerzodesFajtaja;
    }

    public void setSzerzodesFajtaja(String szerzodesFajtaja) {
        this.szerzodesFajtaja = szerzodesFajtaja;
    }

    public String getCpvKod() {
        return cpvKod;
    }

    public void setCpvKod(String cpvKod) {
        this.cpvKod = cpvKod;
    }

    public String getProjekt() {
        return projekt;
    }

    public void setProjekt(String projekt) {
        this.projekt = projekt;
    }

    public String getSzerzodesKotesDatum() {
        return szerzodesKotesDatum;
    }

    public void setSzerzodesKotesDatum(String szerzodesKotesDatum) {
        this.szerzodesKotesDatum = szerzodesKotesDatum;
    }

    public String getSzerzodesTervezettLezarasa() {
        return szerzodesTervezettLezarasa;
    }

    public void setSzerzodesTervezettLezarasa(String szerzodesTervezettLezarasa) {
        this.szerzodesTervezettLezarasa = szerzodesTervezettLezarasa;
    }

    public String getSzerzodesErteke() {
        return szerzodesErteke;
    }

    public void setSzerzodesErteke(String szerzodesErteke) {
        this.szerzodesErteke = szerzodesErteke;
    }
    
}
