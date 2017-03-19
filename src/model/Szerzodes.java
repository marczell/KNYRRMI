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
    private String beszerzesNeve;
    private String keljarasAzon;
    private int becsultErtek;
    private String kozbeszerzesFajtaja;
    private String szerzodesFajtaja;
    private String cpvKod;
    private String projekt;
    private String kozbeszKezdete;
    private String kozbeszVege;
    private String szerzAzon;    
    private String szerzodoFel;
    private int szerzodesErteke;
    private String szerzTargy;
    private String szerzodesKotesDatum;
    private String szerzodesTervezettLezarasa;
    private String szerzmodAzon;
    private String szerzmodErtek;
    private String szerzmodTargy;
    private String szerzmodDatum;
    private String szerzmodVege;

    public Szerzodes(String sorszam, String beszerzesNeve, String keljarasAzon, int becsultErtek, String kozbeszerzesFajtaja, String szerzodesFajtaja, String cpvKod, String projekt, String kozbeszKezdete, String kozbeszVege, String szerzAzon, String szerzodoFel, int szerzodesErteke, String szerzTargy, String szerzodesKotesDatum, String szerzodesTervezettLezarasa, String szerzmodAzon, String szerzmodErtek, String szerzmodTargy, String szerzmodDatum, String szerzmodVege) {
        this.sorszam = sorszam;
        this.beszerzesNeve = beszerzesNeve;
        this.keljarasAzon = keljarasAzon;
        this.becsultErtek = becsultErtek;
        this.kozbeszerzesFajtaja = kozbeszerzesFajtaja;
        this.szerzodesFajtaja = szerzodesFajtaja;
        this.cpvKod = cpvKod;
        this.projekt = projekt;
        this.kozbeszKezdete = kozbeszKezdete;
        this.kozbeszVege = kozbeszVege;
        this.szerzAzon = szerzAzon;    
        this.szerzodoFel = szerzodoFel;
        this.szerzodesErteke = szerzodesErteke;
        this.szerzTargy = szerzTargy;
        this.szerzodesKotesDatum = szerzodesKotesDatum;
        this.szerzodesTervezettLezarasa = szerzodesTervezettLezarasa;
        this.szerzmodAzon = szerzmodAzon;
        this.szerzmodErtek = szerzmodErtek;
        this.szerzmodTargy = szerzmodTargy;
        this.szerzmodDatum = szerzmodDatum;
        this.szerzmodVege = szerzmodVege;
    }

    public String getSorszam() {
        return sorszam;
    }

    public String getKeljarasAzon() {
        return keljarasAzon;
    }

    public String getKozbeszerzesFajtaja() {
        return kozbeszerzesFajtaja;
    }

    public String getSzerzodesFajtaja() {
        return szerzodesFajtaja;
    }

    public String getCpvKod() {
        return cpvKod;
    }

    public String getProjekt() {
        return projekt;
    }

    public String getKozbeszKezdete() {
        return kozbeszKezdete;
    }

    public String getKozbeszVege() {
        return kozbeszVege;
    }

    public String getSzerzAzon() {
        return szerzAzon;
    }

    public String getSzerzodoFel() {
        return szerzodoFel;
    }

    public int getSzerzodesErteke() {
        return szerzodesErteke;
    }

    public String getSzerzTargy() {
        return szerzTargy;
    }

    public String getSzerzodesKotesDatum() {
        return szerzodesKotesDatum;
    }

    public String getSzerzodesTervezettLezarasa() {
        return szerzodesTervezettLezarasa;
    }

    public String getSzerzmodAzon() {
        return szerzmodAzon;
    }

    public String getSzerzmodErtek() {
        return szerzmodErtek;
    }

    public String getSzerzmodTargy() {
        return szerzmodTargy;
    }

    public String getSzerzmodDatum() {
        return szerzmodDatum;
    }

    public String getSzerzmodVege() {
        return szerzmodVege;
    }

    public String getBeszerzesNeve() {
        return beszerzesNeve;
    }

    public int getBecsultErtek() {
        return becsultErtek;
    }

    
}
