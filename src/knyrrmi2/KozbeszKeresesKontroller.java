/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knyrrmi2;

import java.math.BigDecimal;
import java.net.URL;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ErtekLista;
import model.Szerzodes;

/**
 *
 * @author zsofi
 */
public class KozbeszKeresesKontroller implements Initializable{

    @FXML
    private Label uzenet;
    @FXML
    private Button CtrlKozbeszKereses;
    @FXML
    private Button CtrlKozbeszKeresesVissza;
    @FXML
    private TextField SzerzNevKozbeszKereses;
    @FXML
    private TextField BeszSorszamKozbeszKereses;
    @FXML
    private TextField KozbeszAzonKozbeszKereses;
    @FXML
    private ComboBox<?> KozbeszFajtKozbeszKereses;
    @FXML
    private ComboBox<?> SzerzFajtKozbeszKereses;
    @FXML
    private ComboBox<?> CPVKozbeszKereses;
    @FXML
    private ComboBox<?> ProjektKozbeszKereses;
    @FXML
    private DatePicker SzerzKotTolKozbeszKereses;
    @FXML
    private DatePicker SzerzKotIgKozbeszKereses;
    @FXML
    private DatePicker SzerzLezarTolKizbeszKereses;
    @FXML
    private DatePicker SzerzLezarIgKozbeszKereses;
    @FXML
    private TextField BecsultErtekMinKozbeszKereses;
    @FXML
    private TextField BecsultErtekMaxKozbeszKereses;
    @FXML
    private TableView<?> KozbeszerzesekTable;
    @FXML
    private TableColumn<?, ?> tblBeszSorszamKozbeszKereses;
    @FXML
    private TableColumn<?, ?> tblKozbeszAzonKozbeszKeres;
    @FXML
    private TableColumn<?, ?> tblBeszNevKozbeszKeres;
    @FXML
    private TableColumn<?, ?> tblKozbeszFajtKozbeszKeres;
    @FXML
    private TableColumn<?, ?> tblSzerzFajtKozbeszKeres;
    @FXML
    private TableColumn<?, ?> tblCPVKozbeszKeres;
    @FXML
    private TableColumn<?, ?> tblProjektKozbeszKeres;
    @FXML
    private TableColumn<?, ?> tblKozbeszKezdetKozbeszKereses;
    @FXML
    private TableColumn<?, ?> tblKozbeszVegeKozbeszKereses;
    @FXML
    private TableColumn<?, ?> tblBecsultErtKozbeszKereses;

    KnyrInterface serverImpl;
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            serverImpl = (KnyrInterface) myRegistry.lookup("knyr");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tblBeszSorszamKozbeszKereses.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("sorszam"));
        tblKozbeszAzonKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodesNeve"));
        tblBeszNevKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodoFel"));
        tblKozbeszFajtKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodesKotesDatum"));
        tblSzerzFajtKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodesFajtaja"));
        tblCPVKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("cpvKod"));
        tblProjektKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("projekt"));
        tblKozbeszKezdetKozbeszKereses.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodesErteke"));
        tblKozbeszVegeKozbeszKereses.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodesTervezettLezarasa"));
        tblBecsultErtKozbeszKereses.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("kozbeszerzesFajtaja"));
        
        String sql1 = "SELECT KOZBESZERZESIELJARASFAJTAI, KEJID FROM KOZBESZERZESIELJARASFAJTAI WHERE LATHATO=TRUE";//meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        try {
            ResultSet rs = kapcsolat.adatbazisReport(sql1);
            ObservableList obListKej = FXCollections.observableArrayList();
            while (rs.next()) {
                listKej.add(new ErtekLista(new BigDecimal(rs.getString("KEJID")),
                        rs.getString("KOZBESZERZESIELJARASFAJTAI")));
                obListKej.add(rs.getString("KOZBESZERZESIELJARASFAJTAI"));
            }
            KozbeszFajtKereses.getItems().clear();
            KozbeszFajtKereses.setItems(FXCollections.observableList(obListKej));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");
        } finally {
            kapcsolat.closeConnection();
        }
        String sql2 = "SELECT SZERZODESFAJTAID, SZERZODESFAJTA FROM SZERZODESFAJTAI WHERE LATHATO=TRUE";//meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        try {
            ResultSet rs = kapcsolat.adatbazisReport(sql2);
            ObservableList obListSzerzF = FXCollections.observableArrayList();
            while (rs.next()) {
                listSzerzF.add(new ErtekLista(new BigDecimal(rs.getString("SZERZODESFAJTAID")),
                        rs.getString("SZERZODESFAJTA")));
                obListSzerzF.add(rs.getString("SZERZODESFAJTA"));
            }
            SzerzFajtKereses.getItems().clear();
            SzerzFajtKereses.setItems(FXCollections.observableList(obListSzerzF));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } finally {
            kapcsolat.closeConnection();
        }
        //meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        String sql3 = "SELECT CPVKOD, CPVID FROM CPVKODOK WHERE LATHATO=TRUE";
        try {
            ResultSet rs = kapcsolat.adatbazisReport(sql3);
            ObservableList obListCpv = FXCollections.observableArrayList();
            while (rs.next()) {
                listCpv.add(new ErtekLista(new BigDecimal(rs.getString("CPVID")),
                        rs.getString("CPVKOD")));
                obListCpv.add(rs.getString("CPVKOD"));
            }
            CPVKereses.getItems().clear();
            CPVKereses.setItems(FXCollections.observableList(obListCpv));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } finally {
            kapcsolat.closeConnection();
        }
        String sql4 = "SELECT PROJEKT, PROJEKTID FROM PROJEKTEK WHERE LATHATO=TRUE";//meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        try {
            ResultSet rs = kapcsolat.adatbazisReport(sql4);
            ObservableList obListProjekt = FXCollections.observableArrayList();
            while (rs.next()) {
                listProjekt.add(new ErtekLista(new BigDecimal(rs.getString("PROJEKTID")),
                        rs.getString("PROJEKT")));
                obListProjekt.add(rs.getString("PROJEKT"));
            }
            ProjektKereses.getItems().clear();
            ProjektKereses.setItems(FXCollections.observableList(obListProjekt));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } finally {
            kapcsolat.closeConnection();
        }
        String sql5 = "SELECT SZFID, SZERZODOFEL FROM SZERZODO_FEL";
        try {
            ResultSet rs = kapcsolat.adatbazisReport(sql5);
            ObservableList obListSzerzFel = FXCollections.observableArrayList();
            while (rs.next()) {
                listSzerzFel.add(new ErtekLista(new BigDecimal(rs.getString("SZFID")),
                        rs.getString("SZERZODOFEL")));
                obListSzerzFel.add(rs.getString("SZERZODOFEL"));
            }
            SzerzFelKereses.getItems().clear();
            SzerzFelKereses.setItems(FXCollections.observableList(obListSzerzFel));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } finally {
            kapcsolat.closeConnection();
        }
    }
    
    
    
    @FXML
    private void kereses(ActionEvent event) {
    }

    @FXML
    private void keresesVissza(ActionEvent event) {
    }
    
}
