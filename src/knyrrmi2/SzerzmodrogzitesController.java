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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.SerializableResultSet;
import model.Szerzodes;

/**
 * FXML Controller class
 *
 * @author zsofi
 */
public class SzerzmodrogzitesController implements Initializable {

    @FXML
    private MenuItem menuKijelentkezes;
    @FXML
    private Label uzenet;
    
    @FXML
    private TextField sorszamMod;
    @FXML
    private TextField SzerzAzonszerz;
    @FXML
    private TextField SzerzfelSzerz;
    @FXML
    private TextField SzerzTargya;
    @FXML
    private TextField SzerzertekSzerz;
    @FXML
    private DatePicker SzerzkotSzerz;
    @FXML
    private DatePicker SzerzlezarSzerz;
    @FXML
    private Label SzerzModAzonMod;
    @FXML
    private TextField SzerzertekMod;
    @FXML
    private TextField SzerzTargyaMod;
    @FXML
    private DatePicker SzerzkotSzerzMod;
    @FXML
    private DatePicker SzerzlezarSzerzMod;
    @FXML
    private Button CtrlSzerzModMentes;
    @FXML
    private Button CtrlSzerzModVissza;

     KnyrInterface serverImpl;
    @FXML
    private MenuItem menuBezaras;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            serverImpl = (KnyrInterface) myRegistry.lookup("knyr");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(SzerzmodrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql = "SELECT MAX(`szerzmodositas`.`szerzmodazon`) as szerzmodazon FROM `adattar`.`szerzmodositas`";
        try {
            SerializableResultSet rs = (SerializableResultSet) serverImpl.adatbazisReport(sql);
            while (rs.next()) {
                int s = Integer.parseInt(rs.getObject(1).toString());
                int x = s + 1;
                String sorszam = Integer.toString(x);
                SzerzModAzonMod.setText(sorszam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SzerzmodrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba a sorszámlekérés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(SzerzmodrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(SzerzmodrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }  
    
    public void initData(Szerzodes szerzodes) {
    sorszamMod.setText(szerzodes.getSorszam());
    SzerzAzonszerz.setText(szerzodes.getSzerzazon());
    SzerzfelSzerz.setText(szerzodes.getSzerzodoFel());
    SzerzTargya.setText(szerzodes.getSzerztargy());
    SzerzertekSzerz.setText(szerzodes.getSzerzodeserteke());
    SzerzkotSzerz.setValue(LocalDate.parse(szerzodes.getSzerzodeskotesdatuma()));
    SzerzlezarSzerz.setValue(LocalDate.parse(szerzodes.getSzerzodestervezettlezarasa()));
  }

    @FXML
    private void mentesAction(ActionEvent event) {
                if ( SzerzkotSzerzMod.getValue()!= null && SzerzertekMod.getText() != null
                || SzerzTargyaMod.getText() != null
                || SzerzlezarSzerzMod.getValue()!= null) {
            
          
            String sql = "INSERT INTO `adattar`.`szerzmodositas` (`szerzazon`,\n" +
                "`szerzmodertek`,\n" +
                "`szerzmodtargy`,\n" +
                "`szerzmoddatum`,\n" +
                "`szerzmodvege`)\n"
            + " VALUES ('"+ SzerzAzonszerz.getText()+"'";
            if (SzerzertekMod.getText() != null) {
            sql += ", '" + SzerzertekMod.getText() + "'";
            } else {
            sql += ", 'null'";
             }
             if (SzerzTargyaMod.getText() != null) {
            sql += ", '" + SzerzTargyaMod.getText() + "'";
            } else {
            sql += ", 'null',";
             }
            sql += ", '"+ SzerzkotSzerzMod.getValue() +"'";
             if (SzerzlezarSzerzMod.getValue()!= null) {
            sql += " '" + SzerzlezarSzerzMod.getValue() + "'";
            } else {
            sql += ", NULL";
             }
            sql+= ")";
            System.out.println(sql);
            try {
                serverImpl.adatbazisbaInsertalas(sql);
              
                uzenet.setText("Sikeres mentése a " + SzerzModAzonMod.getText() + " azonosítójú szerződésmódosításnak!");
                
                   
                String sql_uj = "SELECT MAX(`szerzodes`.`szerzazon`) as szerzazon FROM `adattar`.`szerzmodositas`";
        try {
            SerializableResultSet rs = (SerializableResultSet) serverImpl.adatbazisReport(sql_uj);
            while (rs.next()) {
                int s = Integer.parseInt(rs.getObject(1).toString());
                int x = s + 1;
                String sorszam = Integer.toString(x);
                SzerzModAzonMod.setText(sorszam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SzerzmodrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba a sorszámlekérés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(SzerzmodrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(SzerzmodrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            }
                SzerzertekMod.clear();
                SzerzTargyaMod.clear();
                SzerzkotSzerzMod.getEditor().clear();
                SzerzlezarSzerzMod.getEditor().clear();
        }                
            } catch (SQLException ex) {
                Logger.getLogger(SzerzmodrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
                uzenet.setText("Hiba a mentés során!");
            } catch (RemoteException ex) {
                Logger.getLogger(SzerzmodrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                 
            }
        } else {
            uzenet.setText("Ellenőrize a mezők kitöltöttségét!");
        }
    }

    @FXML
    private void visszaAction(ActionEvent event) throws IOException {
         Stage stage = (Stage) CtrlSzerzModVissza.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("szerzkereses.fxml"));
        Scene scene = new Scene(root);
        File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }
    
}
