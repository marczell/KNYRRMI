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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Kozbeszerzes;
import model.SerializableResultSet;

/**
 * FXML Controller class
 *
 * @author zsofi
 */
public class SzerzrogzitesController implements Initializable {

    @FXML
    private Label uzenet;
    @FXML
    private Label txtBeszSorszam;
    @FXML
    private TextField SzerzNevSzerz;
    @FXML
    private TextField KozbeszAzon;
    @FXML
    private TextField BecsErtek;
    @FXML
    private TextField KozbeszfajtSzerz;
    @FXML
    private TextField SzerzFajtSzerz;
    @FXML
    private TextField CpvSzerz;
    @FXML
    private TextField ProjektSzerz;
    @FXML
    private DatePicker KozbeszKezd;
    @FXML
    private DatePicker KozbeszVege;
    @FXML
    private ChoiceBox<?> SzerzfelSzerz;
    @FXML
    private TextField SzerzTargya;
    @FXML
    private TextField SzerzertekSzerz;
    @FXML
    private DatePicker SzerzkotSzerz;
    @FXML
    private DatePicker SzerzlezarSzerz;
    @FXML
    private Button CtrlSzerzVissza;
    @FXML
    private Label SzerzAzonSzerz;
    
    KnyrInterface serverImpl;
    Registry myRegistry;
    
    List<String> listSzerzFel = new ArrayList<>();
    List<String> listSzerzFelId = new ArrayList<>();
    @FXML
    private MenuItem menuBezaras;
    @FXML
    private Button CtrlSzerzMentes;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            serverImpl = (KnyrInterface) myRegistry.lookup("knyr");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql = "SELECT MAX(`szerzodes`.`szerzazon`) as szerzazon FROM `adattar`.`szerzodes`";
        try {
            SerializableResultSet rs = (SerializableResultSet) serverImpl.adatbazisReport(sql);
            while (rs.next()) {
                int s = Integer.parseInt(rs.getObject(1).toString());
                int x = s + 1;
                String sorszam = Integer.toString(x);
                SzerzAzonSzerz.setText(sorszam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba a sorszámlekérés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql1 = "SELECT szfid, szerzodofel FROM `adattar`.`szerzodo_fel`";
        try {
            ResultSet rs = (ResultSet) serverImpl.adatbazisReport(sql1);
            while (rs.next()) {
                String s = rs.getObject(1).toString();
                String t = rs.getString(2);
                listSzerzFelId.add(s);
                listSzerzFel.add(t);
            }
            ObservableList obListKej = FXCollections.observableList(listSzerzFel);
            SzerzfelSzerz.getItems().clear();
            SzerzfelSzerz.setItems(obListKej);
        } catch (SQLException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }    
public void initData(Kozbeszerzes kozbesz) {
    txtBeszSorszam.setText(kozbesz.getSorszam());
    SzerzNevSzerz.setText(kozbesz.getBesznev());
    KozbeszAzon.setText(kozbesz.getKeljarasazon());
    BecsErtek.setText(kozbesz.getBertek());
    KozbeszfajtSzerz.setText(kozbesz.getKozbeszerzesieljarasfajta());
    SzerzFajtSzerz.setText(kozbesz.getSzerzodesfajtaja());
    CpvSzerz.setText(kozbesz.getCpvkod());
    ProjektSzerz.setText(kozbesz.getProjekt());
    KozbeszKezd.setValue(LocalDate.parse(kozbesz.getKozbeszkezdete()));
    KozbeszVege.setValue(LocalDate.parse(kozbesz.getKozbeszvege()));
  }
    @FXML
    private void mentesAction(ActionEvent event) {
        if (SzerzfelSzerz.getSelectionModel().getSelectedItem() != null
                && SzerzertekSzerz.getText().matches("[0-9]{1,11}")
                && SzerzTargya.getText().length() <= 250
                && SzerzkotSzerz.getValue() != null
                && SzerzlezarSzerz.getValue() != null
                && SzerzkotSzerz.getValue().isBefore(SzerzlezarSzerz.getValue())) {
            
            String szerzfel = (String) SzerzfelSzerz.getSelectionModel().getSelectedItem();

            String sql = "INSERT INTO `adattar`.`szerzodes` (`sorszam`,\n" +
            "`szerzodofel`,\n" +
            "`szerzodeserteke`,\n" +
            "`szerztargy`,\n" +
            "`szerzodeskotesdatuma`,\n" +
            "`szerzodestervezettlezarasa`)\n"
            + " VALUES ('"+ txtBeszSorszam.getText() +"','"+ listSzerzFelId.get(listSzerzFel.indexOf(szerzfel)) + "', '"
            + SzerzertekSzerz.getText() + "', '" + SzerzTargya.getText() 
            + "','" + SzerzkotSzerz.getValue() + "','" + SzerzlezarSzerz.getValue() + "')";
            System.out.println(sql);
            try {
                serverImpl.adatbazisbaInsertalas(sql);
              
                uzenet.setText("Sikeres mentése a " + SzerzAzonSzerz.getText() + " azonosítójú szerződésnek!");
               
            } catch (SQLException ex) {
                Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
                uzenet.setText("Hiba a mentés során!");
            } catch (RemoteException ex) {
                Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                
                String sql_uj = "SELECT MAX(`szerzodes`.`szerzazon`) as szerzazon FROM `adattar`.`szerzodes`";
        try {
            SerializableResultSet rs = (SerializableResultSet) serverImpl.adatbazisReport(sql_uj);
            while (rs.next()) {
                int s = Integer.parseInt(rs.getObject(1).toString());
                int x = s + 1;
                String sorszam = Integer.toString(x);
                SzerzAzonSzerz.setText(sorszam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba a sorszámlekérés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            }
                SzerzertekSzerz.clear();
                SzerzTargya.clear();
                SzerzfelSzerz.getItems().clear();
                SzerzkotSzerz.getEditor().clear();
                SzerzlezarSzerz.getEditor().clear();
        }
                
            }
        } else {
            uzenet.setText("Ellenőrize a mezők kitöltöttségét!");
        }
    }

    @FXML
    private void visszaAction(ActionEvent event) throws IOException {
         Stage stage = (Stage) CtrlSzerzVissza.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("kozbeszkereses.fxml"));
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
              Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (NotBoundException ex) {
              Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
      }
    });

}
}
