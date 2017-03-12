/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author Marcell
 */
public class ErtekLista implements Serializable{
    
    private BigDecimal id;
    private String nev;

    public ErtekLista(BigDecimal id, String nev) {
        this.id = id;
        this.nev = nev;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }
    
    
    
}
