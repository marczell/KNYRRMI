/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knyrrmi2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marcell
 */

public class Erteklista_modController implements Initializable {
    @FXML
    private Button CtrlErtekTorles;
    @FXML
    private TextField UjErtek;
    @FXML
    private Button CtrlErteklistaVissza;
    @FXML
    private Button CtrlUjErtekMentes;
    @FXML
    private ChoiceBox<?> ErtekLista1;
    @FXML
    private ChoiceBox<String> ErtekLista2;
    @FXML
    private ChoiceBox<?> OsszesErtek;
    @FXML
    private Label uzenet;
    
    KnyrInterface serverImpl;

    Registry myRegistry;
    
    String tabla = new String();
    String oszlop = new String();
    @FXML
    private MenuItem menuBezaras;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            serverImpl = (KnyrInterface) myRegistry.lookup("knyr");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
        }
               
        List<String> list = new ArrayList<>();// ezt a feltöltési módszert ComboBoxhoz ajánlották, de hátha itt is működik
        list.add("CPV kód");
        list.add("Közbeszerzési eljárás fajta");
        list.add("Projekt");
        list.add("Szerződés fajta");
        ObservableList obList = FXCollections.observableList(list);
        ErtekLista1.getItems().clear();
        ErtekLista1.setItems(obList);
        ErtekLista2.getItems().clear();
        ErtekLista2.setItems(obList);
      
        ErtekLista2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
      @Override
      public void changed(ObservableValue<? extends String> selected, String regiErtek, String ujErtek) {
          String sql;
          List<String> list2 = new ArrayList<>();
        if (ujErtek != null) {
          switch(ujErtek) {
            case "CPV kód":  sql = "SELECT CPVKOD FROM CPVKODOK WHERE LATHATO=TRUE";
                try {
                    tabla = "CPVKODOK";
                    oszlop = "CPVKOD";
                    ResultSet rs = serverImpl.adatbazisReport(sql); 
                    while (rs.next()) {
                        String s = rs.getObject(1).toString();
                        list2.add(s);
                    }
                } catch (SQLException ex) {
                Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
                    uzenet.setText("Hiba az értékkeresés során!");
                } catch (RemoteException ex) {
              Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
          } finally {
              try {
                  serverImpl.closeConnection();
              } catch (RemoteException ex) {
                  Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
              }
                }
break;
            case "Közbeszerzési eljárás fajta": sql = "SELECT KOZBESZERZESIELJARASFAJTAI FROM KOZBESZERZESIELJARASFAJTAI WHERE LATHATO=TRUE";
                try { 
                    tabla = "KOZBESZERZESIELJARASFAJTAI";
                    oszlop = "KOZBESZERZESIELJARASFAJTAI";
                    ResultSet rs = serverImpl.adatbazisReport(sql); 
                    while (rs.next()) {
                        String s = rs.getObject(1).toString();
                        list2.add(s);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
                    uzenet.setText("Hiba az értékkeresés során!");
                } catch (RemoteException ex) {
              Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
          } finally {
                    try {
                  serverImpl.closeConnection();
              } catch (RemoteException ex) {
                  Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
              }
                }
break;
            case "Projekt":   sql = "SELECT PROJEKT FROM PROJEKTEK WHERE LATHATO=TRUE";
                try {
                    tabla = "PROJEKTEK";
                     oszlop = "PROJEKT";
                    ResultSet rs = serverImpl.adatbazisReport(sql); 
                    while (rs.next()) {
                        String s = rs.getObject(1).toString();
                        list2.add(s);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
                    uzenet.setText("Hiba az értékkeresés során!");
                } catch (RemoteException ex) {
              Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
          } finally {
                    try {
                  serverImpl.closeConnection();
              } catch (RemoteException ex) {
                  Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
              }
                }
break;
            case "Szerződés fajta":   sql = "SELECT SZERZODESFAJTA FROM SZERZODESFAJTAI WHERE LATHATO=TRUE";
                try {
                    tabla = "SZERZODESFAJTAI";
                    oszlop = "SZERZODESFAJTA";
                    ResultSet rs = serverImpl.adatbazisReport(sql); 
                    while (rs.next()) {
                        String s = rs.getObject(1).toString();
                        list2.add(s);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
                    uzenet.setText("Hiba az értékkeresés során!");
                } catch (RemoteException ex) {
              Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
          } finally {
                  try {
                  serverImpl.closeConnection();
              } catch (RemoteException ex) {
                  Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
              } 
                } 
                break;
          }
        }
        ObservableList obList2 = FXCollections.observableList(list2);
        OsszesErtek.getItems().clear();
        OsszesErtek.setItems(obList2);
      }    
      });
    }
    
    @FXML
     private void ertekTorlesAction(ActionEvent event) {
        //a érték lathato paraméterének false-ra állítása
        Object kivalasztottErtek = OsszesErtek.getSelectionModel().getSelectedItem();
        String sql = "UPDATE "+tabla+" SET LATHATO=FALSE WHERE "+oszlop+" = '"+kivalasztottErtek.toString()+"'";
        try {
                serverImpl.adatbazisbaInsertalas(sql);
                uzenet.setText("Sikeres mentése a " + kivalasztottErtek.toString());
            } catch (SQLException ex) {
                Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
                uzenet.setText("Hiba a mentés során!");
            } catch (RemoteException ex) {
            Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
                
            }
    }
    @FXML
     private void ujErtekMentes(ActionEvent event) {
        //új érték mentése
        Object kivalasztott = ErtekLista1.getSelectionModel().getSelectedItem();
        
        if ("CPV kód".equals(kivalasztott.toString()) && UjErtek.getText().length() <= 15){
            tabla="CPVKODOK";
            oszlop = "CPVKOD";
        } else if ("Közbeszerzési eljárás fajta".equals(kivalasztott.toString())&& UjErtek.getText().length() <= 150){
            tabla = "KOZBESZERZESIELJARASFAJTAI";
            oszlop = "KOZBESZERZESIELJARASFAJTAI";
        } else if ("Projekt".equals(kivalasztott.toString())&& UjErtek.getText().length() <= 150){
            tabla = "PROJEKTEK";
            oszlop = "PROJEKT";
        } else if ("Szerződés fajta".equals(kivalasztott.toString())&& UjErtek.getText().length() <= 150){
            tabla = "SZERZODESFAJTAI";
            oszlop = "SZERZODESFAJTA";   
        } else{
            uzenet.setText("Hibás érték!");
        }
        String sql = "INSERT INTO "+tabla+" ("+oszlop+",LATHATO) VALUES ('"+UjErtek.getText()+"', TRUE)";
    
        try {
                serverImpl.adatbazisbaInsertalas(sql);
                uzenet.setText("Sikeres mentése a " + UjErtek.getText());
                UjErtek.clear();
            } catch (SQLException ex) {
                Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
                uzenet.setText("Hiba a mentés során!");
                 UjErtek.clear();
            } catch (RemoteException ex) {
            Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
               
            }
     }
    
     @FXML
    private void visszaAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlErteklistaVissza.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("fomenu.fxml"));
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }   

        @FXML
    public void bezarasAction(ActionEvent event) throws IOException{
        menuBezaras.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        System.exit(0);
          try {
              myRegistry.unbind("knyr");
          } catch (RemoteException ex) {
              Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (NotBoundException ex) {
              Logger.getLogger(Erteklista_modController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
      }
    });

}
     }
