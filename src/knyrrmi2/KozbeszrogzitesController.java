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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DataEgybentartas;
import model.ProjektEgybentartas;
import model.SerializableResultSet;

/**
 * FXML Controller class
 *
 * @author Marcell
 */
public class KozbeszrogzitesController implements Initializable {

   
   
    @FXML
    private Label uzenet;

   
    List<String> listKej = new ArrayList<>();
    List<String> listKejId = new ArrayList<>();
    List<String> listSzerzF = new ArrayList<>();
    List<String> listSzerzFId = new ArrayList<>();
    List<String> listCpv = new ArrayList<>();
    List<String> listCpvId = new ArrayList<>();
    List<String> listProjekt = new ArrayList<>();
    List<String> listProjektId = new ArrayList<>();
    

    /**
     * Initializes the controller class.
     */
    KnyrInterface serverImpl;
    @FXML
    private TextField SzerzNevKozbesz;
    @FXML
    private Label txtBeszSorszamKozbesz;
    @FXML
    private TextField KozbeszAzonKozbesz;
    @FXML
    private TextField BecsErtekKozbesz;
    @FXML
    private ChoiceBox<?> KozbeszfajtKozbesz;
    @FXML
    private ChoiceBox<?> SzerzFajtKozbesz;
    @FXML
    private ChoiceBox<?> CpvKozbesz;
    @FXML
    private ChoiceBox<?> ProjektKozbesz;
    @FXML
    private DatePicker KozbeszKezdKozbesz;
    @FXML
    private DatePicker KozbeszVegeKozbesz;
    @FXML
    private Button CtrlEgybeszamitas;
    @FXML
    private TableView<ProjektEgybentartas> ProjektTable;
    @FXML
    private TableColumn<ProjektEgybentartas, String> Projekt;
    @FXML
    private TableColumn<ProjektEgybentartas, String> ProjektOsszeg;
    @FXML
    private TableView<DataEgybentartas> CpvTable;
    @FXML
    private TableColumn<DataEgybentartas, String> Cpv;
    @FXML
    private TableColumn<DataEgybentartas, String> CpvOsszeg;
    @FXML
    private Button CtrlSzerzMentes;
    @FXML
    private Button CtrlSzerzVissza;
    @FXML
    private MenuItem menuKiljelentkezes;
    @FXML
    private MenuItem menuBezaras;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            serverImpl = (KnyrInterface) myRegistry.lookup("knyr");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        String sql = "SELECT MAX(`kozbeszerzes`.`sorszam`) as SORSZAM FROM `adattar`.`kozbeszerzes`";
        try {
            SerializableResultSet rs = (SerializableResultSet) serverImpl.adatbazisReport(sql);
            while (rs.next()) {
                int s = Integer.parseInt(rs.getObject(1).toString());
                int x = s + 1;
                String sorszam = Integer.toString(x);
                txtBeszSorszamKozbesz.setText(sorszam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba a sorszámlekérés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String sql1 = "SELECT KOZBESZERZESIELJARASFAJTAI, KEJID FROM KOZBESZERZESIELJARASFAJTAI WHERE LATHATO=TRUE";
        try {
            ResultSet rs = (ResultSet) serverImpl.adatbazisReport(sql1);
            while (rs.next()) {
                String s = rs.getObject(2).toString();
                String t = rs.getString(1);
                listKejId.add(s);
                listKej.add(t);
            }
            ObservableList obListKej = FXCollections.observableList(listKej);
            KozbeszfajtKozbesz.getItems().clear();
            KozbeszfajtKozbesz.setItems(obListKej);
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String sql2 = "SELECT SZERZODESFAJTA, SZERZODESFAJTAID FROM SZERZODESFAJTAI WHERE LATHATO=TRUE";
        try {
            ResultSet rs = (ResultSet) serverImpl.adatbazisReport(sql2);
            while (rs.next()) {
                String s = ((Integer)rs.getInt(2)).toString();
                String t = rs.getString(1);
                listSzerzFId.add(s);
                listSzerzF.add(t);
            }
            ObservableList obListSzerzF = FXCollections.observableList(listSzerzF);
            SzerzFajtKozbesz.getItems().clear();
            SzerzFajtKozbesz.setItems(obListSzerzF);
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String sql3 = "SELECT CPVKOD, CPVID FROM CPVKODOK WHERE LATHATO=TRUE";
        try {
            ResultSet rs = (ResultSet) serverImpl.adatbazisReport(sql3);
            while (rs.next()) {
                String s = ((Integer)rs.getInt(2)).toString();
                String t = ((Integer)rs.getInt(1)).toString();
                listCpvId.add(s);
                listCpv.add(t);
            }
            ObservableList obListSzerzF = FXCollections.observableList(listCpv);
            CpvKozbesz.getItems().clear();
            CpvKozbesz.setItems(obListSzerzF);
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String sql4 = "SELECT PROJEKT, PROJEKTID FROM PROJEKTEK WHERE LATHATO=TRUE";
        try {
            ResultSet rs = (ResultSet) serverImpl.adatbazisReport(sql4);
            while (rs.next()) {
                String s = ((Integer)rs.getInt(2)).toString();
                String t = rs.getString(1);
                listProjektId.add(s);
                listProjekt.add(t);
            }
            ObservableList obListProjekt = FXCollections.observableList(listProjekt);
            ProjektKozbesz.getItems().clear();
            ProjektKozbesz.setItems(obListProjekt);
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Projekt.setCellValueFactory(new PropertyValueFactory<ProjektEgybentartas, String>("projektNev"));
        ProjektOsszeg.setCellValueFactory(new PropertyValueFactory<ProjektEgybentartas, String>("projektErtek"));
        Cpv.setCellValueFactory(new PropertyValueFactory<DataEgybentartas, String>("nev"));
        CpvOsszeg.setCellValueFactory(new PropertyValueFactory<DataEgybentartas, String>("ertek"));
    }
 @FXML
    private void mentesAction(ActionEvent event) {
        //a közbeszerzés adatainak rögzítése
        String kozbeszf = (String) KozbeszfajtKozbesz.getSelectionModel().getSelectedItem();
        String szerzf = (String) SzerzFajtKozbesz.getSelectionModel().getSelectedItem();
        String cpv = (String) CpvKozbesz.getSelectionModel().getSelectedItem();
        String projekt = (String) ProjektKozbesz.getSelectionModel().getSelectedItem();

        if (SzerzNevKozbesz.getText().length() <= 100
                && KozbeszAzonKozbesz.getText()!= null
                && BecsErtekKozbesz.getText().matches("[0-9]{1,11}")
                && KozbeszfajtKozbesz.getSelectionModel().getSelectedItem() != null
                && SzerzFajtKozbesz.getSelectionModel().getSelectedItem() != null
                && CpvKozbesz.getSelectionModel().getSelectedItem() != null
                && ProjektKozbesz.getSelectionModel().getSelectedItem() != null
                && KozbeszKezdKozbesz.getValue() != null
                && KozbeszVegeKozbesz.getValue() != null
                && KozbeszKezdKozbesz.getValue().isBefore(KozbeszVegeKozbesz.getValue())) {

            String sql = "INSERT INTO `adattar`.`kozbeszerzes` (`besznev`,\n" +
            "`keljarasazon`,\n" +
            "`bertek`,\n" +
            "`kozbeszerzesieljarasfajta`,\n" +
            "`szerzodesfajtaja`,\n" +
            "`cpvkod`,\n" +
            "`projekt`,\n" +
            "`kozbeszkezdete`,\n" +
            "`kozbeszvege`)\n"
                    + " VALUES ('" + SzerzNevKozbesz.getText() + "', '" + KozbeszAzonKozbesz.getText() + "', '" + BecsErtekKozbesz.getText() + "', '"
                    + listKejId.get(listKej.indexOf(kozbeszf))+ "', '" +listSzerzFId.get(listSzerzF.indexOf(szerzf))  + "','" + listCpvId.get(listCpv.indexOf(cpv)) + "','" + listProjektId.get(listProjekt.indexOf(projekt)) + "','"
                    + KozbeszKezdKozbesz.getValue() + "','" + KozbeszVegeKozbesz.getValue() + "')";
            System.out.println(sql);
            try {
                serverImpl.adatbazisbaInsertalas(sql);
              
                uzenet.setText("A" + SzerzNevKozbesz.getText()+"beszerzés mentése sikeresen megtörtént!");
                SzerzNevKozbesz.clear();
                KozbeszAzonKozbesz.clear();
                BecsErtekKozbesz.clear();
                KozbeszfajtKozbesz.getItems().clear();
                SzerzFajtKozbesz.getItems().clear();
                CpvKozbesz.getItems().clear();
                ProjektKozbesz.getItems().clear();
                KozbeszKezdKozbesz.getEditor().clear();
                KozbeszVegeKozbesz.getEditor().clear();
            } catch (SQLException ex) {
                Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
                uzenet.setText("Hiba a mentés során!");
            } catch (RemoteException ex) {
                Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                 String sqluj = "SELECT MAX(`kozbeszerzes`.`sorszam`) as SORSZAM FROM `adattar`.`kozbeszerzes`";
        try {
            SerializableResultSet rs = (SerializableResultSet) serverImpl.adatbazisReport(sqluj);
            while (rs.next()) {
                int s = Integer.parseInt(rs.getObject(1).toString());
                int x = s + 1;
                String sorszam = Integer.toString(x);
                txtBeszSorszamKozbesz.setText(sorszam);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba a sorszámlekérés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                
            }
        } else {
            uzenet.setText("Ellenőrize a mezők kitöltöttségét!");
        }
    }
    @FXML
    private void egybeszamitasAction(ActionEvent event) {
        //egybeszámítási vizsgálat
        
        String cpv = (String) CpvKozbesz.getSelectionModel().getSelectedItem();
        String projekt = (String) ProjektKozbesz.getSelectionModel().getSelectedItem();
        String sqlprojekt = null;
        String sqlcpv = null;
        LocalDate datum = KozbeszKezdKozbesz.getValue();
        int ev = datum.getYear();
        
        if (BecsErtekKozbesz.getText().matches("[0-9]{1,11}")
                && CpvKozbesz.getSelectionModel().getSelectedItem() != null
                && ProjektKozbesz.getSelectionModel().getSelectedItem() != null
                && KozbeszKezdKozbesz.getValue() != null
                ) {

         //Projekt egybeszámítás elvégzése   
        sqlprojekt = "select p.projekt, sum(sz.bertek) as osszeg \n" +
        "from projektek p, kozbeszerzes sz \n" +
        "where sz.projekt=p.projektid and sz.projekt=  '"+ listProjektId.get(listProjekt.indexOf(projekt)) +
        "' and year(sz.kozbeszkezdete) = " + ev;
        
        sqlprojekt += " group by sz.projekt";
        System.out.println(sqlprojekt);
        ArrayList<ProjektEgybentartas> projektEgybentartasLista = null;
        try {
           
            projektEgybentartasLista=serverImpl.projektEgybOsszes(sqlprojekt);
          if (projektEgybentartasLista.isEmpty()){
              
              ProjektEgybentartas uj = new ProjektEgybentartas(projekt,BecsErtekKozbesz.getText());
              projektEgybentartasLista.add(uj);
          } else{
              ProjektEgybentartas uj = projektEgybentartasLista.get(0);
              System.out.println(projektEgybentartasLista.get(0).getProjektErtek());
              int eredeti = Integer.parseInt(projektEgybentartasLista.get(0).getProjektErtek());
              Integer teljes = eredeti + Integer.parseInt(BecsErtekKozbesz.getText());
              uj.setProjektErtek(teljes.toString());
              projektEgybentartasLista.set(0,uj);
          }
            ProjektTable.setItems(FXCollections.observableArrayList(projektEgybentartasLista));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        projektEgybentartasLista.add(new ProjektEgybentartas("projekt neve", "15"));
        ProjektTable.setItems(FXCollections.observableArrayList(projektEgybentartasLista));
        //cpv egybeszámítás elvégzése
        sqlcpv = "select c.cpvkod, sum(sz.bertek) as osszeg \n"
                + "from cpvkodok c, kozbeszerzes sz \n"
                + "where sz.cpvkod=c.cpvid and c.cpvid='"
        + listCpvId.get(listCpv.indexOf(cpv))+ "' and year(sz.kozbeszkezdete) = " + ev;
        sqlcpv += " group by c.cpvkod";
        
        System.out.println(sqlcpv);
        
        ArrayList<DataEgybentartas> dataEgybentartasLista=null;
        try {
            
            dataEgybentartasLista=serverImpl.cpvEgybOsszes(sqlcpv);
           
             if (dataEgybentartasLista.isEmpty()){
              
              DataEgybentartas uj = new DataEgybentartas(cpv,BecsErtekKozbesz.getText());
              dataEgybentartasLista.add(uj);
          } else{
              DataEgybentartas uj = dataEgybentartasLista.get(0);
              System.out.println(dataEgybentartasLista.get(0).getErtek());
              int eredeti = Integer.parseInt(dataEgybentartasLista.get(0).getErtek());
              Integer teljes = eredeti + Integer.parseInt(BecsErtekKozbesz.getText());
              uj.setErtek(teljes.toString());
              dataEgybentartasLista.set(0,uj);
          }
            CpvTable.setItems(FXCollections.observableArrayList(dataEgybentartasLista));
            //KnyrInterface knyrInterface = (KnyrInterface) Naming.lookup("almafa");
        } catch (Exception e) {
            e.printStackTrace();
        }
    
        } else {
            uzenet.setText("Ellenőrize a mezők kitöltöttségét!");
        }
        
    }

    @FXML
    private void visszaAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlSzerzVissza.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("fomenu.fxml"));
        Scene scene = new Scene(root);
        File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }

}
