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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.SerializableResultSet;

/**
 * FXML Controller class
 *
 * @author Marcell
 */
public class SzerzrogzitesController implements Initializable {

    @FXML
    private TextField SzerzNevSzerz;
    @FXML
    private Button CtrlSzerzMentes;
    @FXML
    private TextField SzerzertekSzerz;
    @FXML
    private ChoiceBox<?> KozbeszfajtSzerz;
    @FXML
    private ChoiceBox<?> SzerzFajtSzerz;
    @FXML
    private ChoiceBox<?> CpvSzerz;
    @FXML
    private ChoiceBox<?> ProjektSzerz;
    @FXML
    private DatePicker SzerzkotSzerz;
    @FXML
    private DatePicker SzerzlezarSzerz;
    @FXML
    private ChoiceBox<?> SzerzfelSzerz;
    @FXML
    private Label Sorszam;
    @FXML
    private Button CtrlSzerzVissza;
    @FXML
    private Label uzenet;

    private Kapcsolat kapcsolat = new Kapcsolat();
    List<String> listKej = new ArrayList<>();
    List<String> listKejId = new ArrayList<>();
    List<String> listSzerzF = new ArrayList<>();
    List<String> listSzerzFId = new ArrayList<>();
    List<String> listCpv = new ArrayList<>();
    List<String> listCpvId = new ArrayList<>();
    List<String> listProjekt = new ArrayList<>();
    List<String> listProjektId = new ArrayList<>();
    List<String> listSzerzFel = new ArrayList<>();
    List<String> listSzerzFelId = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    KnyrInterface serverImpl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            serverImpl = (KnyrInterface) myRegistry.lookup("knyr");
        } catch (RemoteException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NotBoundException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT MAX(SORSZAM) as SORSZAM FROM SZERZODES";
        try {
            SerializableResultSet rs = (SerializableResultSet) serverImpl.adatbazisReport(sql);
            while (rs.next()) {
                int s = rs.getInt("SORSZAM");
                int x = s + 1;
                String sorszam = Integer.toString(x);
                Sorszam.setText(sorszam);
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
        String sql1 = "SELECT KOZBESZERZESIELJARASFAJTAI, KEJID FROM KOZBESZERZESIELJARASFAJTAI WHERE LATHATO=TRUE";
        try {
            ResultSet rs = (ResultSet) serverImpl.adatbazisReport(sql1);
            while (rs.next()) {
                String s = ((Integer)rs.getInt(2)).toString();
                String t = rs.getString(1);
                listKejId.add(s);
                listKej.add(t);
            }
            ObservableList obListKej = FXCollections.observableList(listKej);
            KozbeszfajtSzerz.getItems().clear();
            KozbeszfajtSzerz.setItems(obListKej);
        } catch (SQLException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            kapcsolat.closeConnection();
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
            SzerzFajtSzerz.getItems().clear();
            SzerzFajtSzerz.setItems(obListSzerzF);
        } catch (SQLException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            kapcsolat.closeConnection();
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
            CpvSzerz.getItems().clear();
            CpvSzerz.setItems(obListSzerzF);
        } catch (SQLException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            kapcsolat.closeConnection();
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
            ProjektSzerz.getItems().clear();
            ProjektSzerz.setItems(obListProjekt);
        } catch (SQLException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            kapcsolat.closeConnection();
        }
        String sql5 = "SELECT SZERZODOFEL, SZFID FROM SZERZODO_FEL";
        try {
            ResultSet rs = (ResultSet) serverImpl.adatbazisReport(sql5);
            while (rs.next()) {
                String s = ((Integer)rs.getInt(2)).toString();
                String t = rs.getString(1);
                listSzerzFelId.add(s);
                listSzerzFel.add(t);
            }
            ObservableList obListSzerzFel = FXCollections.observableList(listSzerzFel);
            SzerzfelSzerz.getItems().clear();
            SzerzfelSzerz.setItems(obListSzerzFel);
        } catch (SQLException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            kapcsolat.closeConnection();
        }
    }

    @FXML
    private void mentesAction(ActionEvent event) {
        //a szerződés adatainak rögzítése
        String kozbeszf = (String) KozbeszfajtSzerz.getSelectionModel().getSelectedItem();
        String szerzf = (String) SzerzFajtSzerz.getSelectionModel().getSelectedItem();
        String cpv = (String) CpvSzerz.getSelectionModel().getSelectedItem();
        String projekt = (String) ProjektSzerz.getSelectionModel().getSelectedItem();
        String szerzfel = (String) SzerzfelSzerz.getSelectionModel().getSelectedItem();

        if (SzerzNevSzerz.getText().length() <= 100
                && SzerzertekSzerz.getText().matches("[0-9]{1,11}") // itt nem jó az ellenőrzés. Marcell nézd meg, hogy mi a hiba 11 int lehet az érték
                && KozbeszfajtSzerz.getSelectionModel().getSelectedItem() != null
                && SzerzFajtSzerz.getSelectionModel().getSelectedItem() != null
                && CpvSzerz.getSelectionModel().getSelectedItem() != null
                && ProjektSzerz.getSelectionModel().getSelectedItem() != null
                && SzerzfelSzerz.getSelectionModel().getSelectedItem() != null
                && SzerzkotSzerz.getValue() != null
                && SzerzlezarSzerz.getValue() != null
                && SzerzkotSzerz.getValue().isBefore(SzerzlezarSzerz.getValue())) {

            String sql = "INSERT INTO SZERZODES(`szerzodesneve`, `szerzodofel`, `szerzodeskotesdatuma`, `szerzodesfajtaja`, `kozbeszerzesieljarasfajta`, `cpvkod`, `projekt`, `szerzodeserteke`, `szerzodestervezettlezarasa`, `megjegyzes`)\n"
                    + " VALUES ('" + SzerzNevSzerz.getText() + "', '" + listSzerzFelId.get(listSzerzFel.indexOf(szerzfel)) + "', '" + SzerzkotSzerz.getValue() + "','"
                    + listSzerzFId.get(listSzerzF.indexOf(szerzf)) + "','" + listKejId.get(listKej.indexOf(kozbeszf)) + "','" + listCpvId.get(listCpv.indexOf(cpv)) + "','" + listProjektId.get(listProjekt.indexOf(projekt)) + "','"
                    + SzerzertekSzerz.getText() + "','" + SzerzlezarSzerz.getValue() + "', 'ok')";
            System.out.println(sql);
            try {
                serverImpl.adatbazisbaInsertalas(sql);
                //kapcsolat.adatbazisbaInsertalas(sql);
                uzenet.setText("Sikeres mentése a " + SzerzNevSzerz.getText());
                SzerzNevSzerz.clear();
                SzerzertekSzerz.clear();
                KozbeszfajtSzerz.getItems().clear();
                SzerzFajtSzerz.getItems().clear();
                CpvSzerz.getItems().clear();
                ProjektSzerz.getItems().clear();
                SzerzfelSzerz.getItems().clear();
                SzerzkotSzerz.getEditor().clear();
                SzerzlezarSzerz.getEditor().clear();
            } catch (SQLException ex) {
                Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
                uzenet.setText("Hiba a mentés során!");
            } catch (RemoteException ex) {
                Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    serverImpl.closeConnection();
                } catch (RemoteException ex) {
                    Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
                }
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
