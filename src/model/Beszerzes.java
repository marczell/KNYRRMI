/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author zsofi
 */

public class Beszerzes implements Serializable {

    
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

    private String szerzazon;
    
    private String szerzodeserteke;
    
    private String szerztargy;
   
    private String szerzodeskotesdatuma;
    
    private String szerzodestervezettlezarasa;
    
    private String szerzodoFel;
    
    private String szerzmodazon;
    
    private String szerzmodertek;
    
    private String szerzmodtargy;
    
    private String szerzmoddatum;
    
    private String szerzmodvege;

    public Beszerzes(String sorszam, String besznev, String keljarasazon, String bertek, String kozbeszerzesieljarasfajta, String szerzodesfajtaja, String cpvkod, String projekt, String kozbeszkezdete, String kozbeszvege, String szerzazon, String szerzodeserteke, String szerztargy, String szerzodeskotesdatuma, String szerzodestervezettlezarasa, String szerzodoFel, String szerzmodazon, String szerzmodertek, String szerzmodtargy, String szerzmoddatum, String szerzmodvege) {
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
        this.szerzazon = szerzazon;
        this.szerzodeserteke = szerzodeserteke;
        this.szerztargy = szerztargy;
        this.szerzodeskotesdatuma = szerzodeskotesdatuma;
        this.szerzodestervezettlezarasa = szerzodestervezettlezarasa;
        this.szerzodoFel = szerzodoFel;
        this.szerzmodazon = szerzmodazon;
        this.szerzmodertek = szerzmodertek;
        this.szerzmodtargy = szerzmodtargy;
        this.szerzmoddatum = szerzmoddatum;
        this.szerzmodvege = szerzmodvege;
    }

    public String getBesznev() {
        return besznev;
    }

    public String getKeljarasazon() {
        return keljarasazon;
    }

    public String getBertek() {
        return bertek;
    }

    public String getKozbeszerzesieljarasfajta() {
        return kozbeszerzesieljarasfajta;
    }

    public String getSzerzodesfajtaja() {
        return szerzodesfajtaja;
    }

    public String getCpvkod() {
        return cpvkod;
    }

    public String getProjekt() {
        return projekt;
    }

    public String getKozbeszkezdete() {
        return kozbeszkezdete;
    }

    public String getKozbeszvege() {
        return kozbeszvege;
    }

    public String getSzerzazon() {
        return szerzazon;
    }

    public String getSzerzodeserteke() {
        return szerzodeserteke;
    }

    public String getSzerztargy() {
        return szerztargy;
    }

    public String getSzerzodeskotesdatuma() {
        return szerzodeskotesdatuma;
    }

    public String getSzerzodestervezettlezarasa() {
        return szerzodestervezettlezarasa;
    }

    public String getSzerzodoFel() {
        return szerzodoFel;
    }

    public String getSzerzmodazon() {
        return szerzmodazon;
    }

    public String getSzerzmodertek() {
        return szerzmodertek;
    }

    public String getSzerzmodtargy() {
        return szerzmodtargy;
    }

    public String getSzerzmoddatum() {
        return szerzmoddatum;
    }

    public String getSzerzmodvege() {
        return szerzmodvege;
    }

    
    
    public String getSorszam() {
        return sorszam;
    }

    public void setSorszam(String sorszam) {
        this.sorszam = sorszam;
    }
   
}
