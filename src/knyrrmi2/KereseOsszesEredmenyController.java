/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knyrrmi2;

import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ErtekLista;
import model.Szerzodes;

/**
 *
 * @author zsofi
 */
public class KereseOsszesEredmenyController {

    @FXML
    private Label uzenet;
    @FXML
    private Button CtrlKozbeszKeresesVissza;
    @FXML
    private TableView<?> KozbeszerzesekTable;
    @FXML
    private TableColumn<?, ?> KözbeszSorszamKozbeszKereses;
    @FXML
    private TableColumn<?, ?> KozbeszAzonKozbeszKeres;
    @FXML
    private TableColumn<?, ?> BeszNevKozbeszKeres;
    @FXML
    private TableColumn<?, ?> KozbeszFajtKozbeszKeres;
    @FXML
    private TableColumn<?, ?> SzerzFajtKozbeszKeres;
    @FXML
    private TableColumn<?, ?> CPVKozbeszKeres;
    @FXML
    private TableColumn<?, ?> ProjektKozbeszKeres;
    @FXML
    private TableColumn<?, ?> KozbeszKezdetKozbeszKereses;
    @FXML
    private TableColumn<?, ?> KozbeszVegeKozbeszKereses;
    @FXML
    private TableColumn<?, ?> BecsultErtKozbeszKereses;
    @FXML
    private MenuItem menuKijelentkezes;
    @FXML
    private MenuItem menuBezaras;
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            serverImpl = (KnyrInterface) myRegistry.lookup("knyr");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(KeresesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
        SzerzNevKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodesNeve"));
        SzerzFelKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodoFel"));
        SzerzKotKereses.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodesKotesDatum"));
        SzerzFajtKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodesFajtaja"));
        CPVKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("cpvKod"));
        ProjektKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("projekt"));
        SzerzertekKereses.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodesErteke"));
        SzerzLezarKereses.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodesTervezettLezarasa"));
        KozbeszFajtKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("kozbeszerzesFajtaja"));
        
        String sql1 = "SELECT KOZBESZERZESIELJARASFAJTAI, KEJID FROM KOZBESZERZESIELJARASFAJTAI WHERE LATHATO=TRUE";//meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        try {
            ResultSet rs = null;
            try {
                rs = serverImpl.adatbazisReport(sql1);
            } catch (RemoteException ex) {
                Logger.getLogger(KeresesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObservableList obListKej = FXCollections.observableArrayList();
            while (rs.next()) {
                listKej.add(new ErtekLista(rs.getString("KEJID"),
                        rs.getString("KOZBESZERZESIELJARASFAJTAI")));
                obListKej.add(rs.getString("KOZBESZERZESIELJARASFAJTAI"));
            }
            KozbeszFajtKereses.getItems().clear();
            KozbeszFajtKereses.setItems(FXCollections.observableList(obListKej));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");
        } finally {
           
        }
        String sql2 = "SELECT SZERZODESFAJTAID, SZERZODESFAJTA FROM SZERZODESFAJTAI WHERE LATHATO=TRUE";//meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql2);
            ObservableList obListSzerzF = FXCollections.observableArrayList();
            while (rs.next()) {
                listSzerzF.add(new ErtekLista(rs.getString("SZERZODESFAJTAID"),
                        rs.getString("SZERZODESFAJTA")));
                obListSzerzF.add(rs.getString("SZERZODESFAJTA"));
            }
            SzerzFajtKereses.getItems().clear();
            SzerzFajtKereses.setItems(FXCollections.observableList(obListSzerzF));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } catch (RemoteException ex) {
            Logger.getLogger(KeresesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
        //meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        String sql3 = "SELECT CPVKOD, CPVID FROM CPVKODOK WHERE LATHATO=TRUE";
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql3);
            ObservableList obListCpv = FXCollections.observableArrayList();
            while (rs.next()) {
                listCpv.add(new ErtekLista(rs.getString("CPVID"),
                        rs.getString("CPVKOD")));
                obListCpv.add(rs.getString("CPVKOD"));
            }
            CPVKereses.getItems().clear();
            CPVKereses.setItems(FXCollections.observableList(obListCpv));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } catch (RemoteException ex) {
            Logger.getLogger(KeresesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
        String sql4 = "SELECT PROJEKT, PROJEKTID FROM PROJEKTEK WHERE LATHATO=TRUE";//meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql4);
            ObservableList obListProjekt = FXCollections.observableArrayList();
            while (rs.next()) {
                listProjekt.add(new ErtekLista(rs.getString("PROJEKTID"),
                        rs.getString("PROJEKT")));
                obListProjekt.add(rs.getString("PROJEKT"));
            }
            ProjektKereses.getItems().clear();
            ProjektKereses.setItems(FXCollections.observableList(obListProjekt));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } catch (RemoteException ex) {
            Logger.getLogger(KeresesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           
        }
        String sql5 = "SELECT SZFID, SZERZODOFEL FROM SZERZODO_FEL";
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql5);
            ObservableList obListSzerzFel = FXCollections.observableArrayList();
            while (rs.next()) {
                listSzerzFel.add(new ErtekLista(rs.getString("SZFID"),
                        rs.getString("SZERZODOFEL")));
                obListSzerzFel.add(rs.getString("SZERZODOFEL"));
            }
            SzerzFelKereses.getItems().clear();
            SzerzFelKereses.setItems(FXCollections.observableList(obListSzerzFel));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } catch (RemoteException ex) {
            Logger.getLogger(KeresesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
    }
    
    private String idKereso(ArrayList<ErtekLista> ertekLista){
        for (ErtekLista kozFajt : ertekLista) {
            if (kozFajt.getNev().equals(KozbeszFajtKereses.getValue())) {
                return kozFajt.getId();
            }
        }
        //elvileg ilyen nem lehet
//        uzenet.setText("A "+listaNev+" már nem szerepel a listában!");
        return null;
    }
    

    @FXML
    private void keresesVissza(ActionEvent event) {
    }
    
}
