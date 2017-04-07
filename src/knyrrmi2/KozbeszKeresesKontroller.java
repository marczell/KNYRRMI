/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knyrrmi2;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import model.ErtekLista;
import model.Kozbeszerzes;
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
    private DatePicker KozbeszTolKozbeszKereses;
    @FXML
    private DatePicker KozbeszIgKozbeszKereses;
    @FXML
    private DatePicker KozbeszLezarTolKozbeszKereses;
    @FXML
    private DatePicker KozbeszLezarIgKozbeszKereses;
    @FXML
    private TextField BecsultErtekMinKozbeszKereses;
    @FXML
    private TextField BecsultErtekMaxKozbeszKereses;
    @FXML
    private TableView<Kozbeszerzes> KozbeszerzesekTable;
    @FXML
    private TableColumn<Kozbeszerzes, String> tblBeszSorszamKozbeszKereses;
    @FXML
    private TableColumn<Kozbeszerzes, String> tblKozbeszAzonKozbeszKeres;
    @FXML
    private TableColumn<Kozbeszerzes, String> tblBeszNevKozbeszKeres;
    @FXML
    private TableColumn<Kozbeszerzes, String> tblKozbeszFajtKozbeszKeres;
    @FXML
    private TableColumn<Kozbeszerzes, String> tblSzerzFajtKozbeszKeres;
    @FXML
    private TableColumn<Kozbeszerzes, String> tblCPVKozbeszKeres;
    @FXML
    private TableColumn<Kozbeszerzes, String> tblProjektKozbeszKeres;
    @FXML
    private TableColumn<Kozbeszerzes, String> tblKozbeszKezdetKozbeszKereses;
    @FXML
    private TableColumn<Kozbeszerzes, String> tblKozbeszVegeKozbeszKereses;
    @FXML
    private TableColumn<Kozbeszerzes, String> tblBecsultErtKozbeszKereses;

    KnyrInterface serverImpl;
    
    ArrayList<ErtekLista> listKej = new ArrayList<>();
    ArrayList<ErtekLista> listSzerzF = new ArrayList<>();
    ArrayList<ErtekLista> listCpv = new ArrayList<>();
    ArrayList<ErtekLista> listProjekt = new ArrayList<>();
    ArrayList<ErtekLista> listSzerzFel = new ArrayList<>();
    
    
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            serverImpl = (KnyrInterface) myRegistry.lookup("knyr");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        BecsultErtekMaxKozbeszKereses.clear();
        BecsultErtekMinKozbeszKereses.clear();
        
        tblBeszSorszamKozbeszKereses.setCellValueFactory(new PropertyValueFactory<Kozbeszerzes, String>("sorszam"));
        tblKozbeszAzonKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Kozbeszerzes, String>("keljarasazon"));
        tblBeszNevKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Kozbeszerzes, String>("besznev"));
        tblKozbeszFajtKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Kozbeszerzes, String>("kozbeszerzesieljarasfajta"));
        tblSzerzFajtKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Kozbeszerzes, String>("szerzodesfajtaja"));
        tblCPVKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Kozbeszerzes, String>("cpvkod"));
        tblProjektKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Kozbeszerzes, String>("projekt"));
        tblKozbeszKezdetKozbeszKereses.setCellValueFactory(new PropertyValueFactory<Kozbeszerzes, String>("kozbeszkezdete"));
        tblKozbeszVegeKozbeszKereses.setCellValueFactory(new PropertyValueFactory<Kozbeszerzes, String>("kozbeszvege"));
        tblBecsultErtKozbeszKereses.setCellValueFactory(new PropertyValueFactory<Kozbeszerzes, String>("bertek"));
        
        KozbeszerzesekTable.setRowFactory(tv -> {
           TableRow<Kozbeszerzes> row = new TableRow<>();
           row.setOnMouseClicked(event -> {
             if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
             && event.getClickCount() == 2) {

                Kozbeszerzes kivalasztott = row.getItem();
                Stage stage = (Stage) row.getScene().getWindow();
                Parent root = null;
                 try {
                     root = FXMLLoader.load(getClass().getResource("szerzrogzites.fxml"));
                 } catch (IOException ex) {
                     Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
                 }
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
               
            
        }
    });
    return row ;
    
});
        
        String sql1 = "SELECT KEJID, KOZBESZERZESIELJARASFAJTAI FROM KOZBESZERZESIELJARASFAJTAI WHERE LATHATO=TRUE";//meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql1);
            ObservableList obListKej = FXCollections.observableArrayList();
            
            while (rs.next()) {
                listKej.add(new ErtekLista(rs.getObject(1).toString(),
                        rs.getObject(2).toString()));
                obListKej.add(rs.getObject(2).toString());
            }
            KozbeszFajtKozbeszKereses.getItems().clear();
            KozbeszFajtKozbeszKereses.setItems(FXCollections.observableList(obListKej));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
         try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String sql2 = "SELECT SZERZODESFAJTAID, SZERZODESFAJTA FROM SZERZODESFAJTAI WHERE LATHATO=TRUE";//meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql2);
            ObservableList obListSzerzF = FXCollections.observableArrayList();
            while (rs.next()) {
                listSzerzF.add(new ErtekLista(rs.getObject(1).toString(),
                        rs.getObject(2).toString()));
                obListSzerzF.add(rs.getObject(2).toString());
            }
            SzerzFajtKozbeszKereses.getItems().clear();
            SzerzFajtKozbeszKereses.setItems(FXCollections.observableList(obListSzerzF));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        //meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        String sql3 = "SELECT CPVID, CPVKOD FROM CPVKODOK WHERE LATHATO=TRUE";
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql3);
            ObservableList obListCpv = FXCollections.observableArrayList();
            while (rs.next()) {
                listCpv.add(new ErtekLista(rs.getObject(1).toString(),
                       rs.getObject(2).toString()));
                obListCpv.add(rs.getObject(2).toString());
            }
            CPVKozbeszKereses.getItems().clear();
            CPVKozbeszKereses.setItems(FXCollections.observableList(obListCpv));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        String sql4 = "SELECT PROJEKTID, PROJEKT FROM PROJEKTEK WHERE LATHATO=TRUE";//meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql4);
            ObservableList obListProjekt = FXCollections.observableArrayList();
            while (rs.next()) {
                listProjekt.add(new ErtekLista(rs.getObject(1).toString(),
                        rs.getObject(2).toString()));
                obListProjekt.add(rs.getObject(2).toString());
            }
            ProjektKozbeszKereses.getItems().clear();
            ProjektKozbeszKereses.setItems(FXCollections.observableList(obListProjekt));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(KozbeszrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }
    
    @FXML
    private void kereses(ActionEvent event) {
        if (KozbeszTolKozbeszKereses.getValue() != null && KozbeszTolKozbeszKereses.getValue().isAfter(KozbeszIgKozbeszKereses.getValue())) {
            uzenet.setText("Az -ig dátum nem lehet nagyobb a -tól dátumnál!");
        } else {
            uzenet.setText("");
        }
        if (KozbeszLezarTolKozbeszKereses.getValue() != null && KozbeszLezarTolKozbeszKereses.getValue().isAfter(KozbeszLezarIgKozbeszKereses.getValue())) {
            uzenet.setText("Az -ig dátum nem lehet nagyobb a -tól dátumnál!");
        } else {
            uzenet.setText("");
        }
        
        String sql;
        sql = "SELECT `kozbeszerzes`.`sorszam`,\n" +
        "    `kozbeszerzes`.`besznev`,\n" +
        "    `kozbeszerzes`.`keljarasazon`,\n" +
        "    `kozbeszerzes`.`bertek`,\n" +
        "    `kozbeszerzesieljarasfajtai`.`kozbeszerzesieljarasfajtai`,\n" +
        "    `szerzodesfajtai`.`szerzodesfajta`,\n" +
        "    `cpvkodok`.`cpvkod`,\n" +
        "    `projektek`.`projekt`,\n" +
        "    `kozbeszerzes`.`kozbeszkezdete`,\n" +
        "    `kozbeszerzes`.`kozbeszvege`\n" +
        "FROM `adattar`.`kozbeszerzes`, `adattar`.`projektek`, `adattar`.`szerzodesfajtai`,\n" +
        "`adattar`.`cpvkodok`, `adattar`.`kozbeszerzesieljarasfajtai` WHERE  \n" +
        "`kozbeszerzes`.`projekt`=`projektek`.`projektid` and `kozbeszerzes`.`szerzodesfajtaja`=`szerzodesfajtai`.`szerzodesfajtaid` and \n" +
        "`kozbeszerzes`.`kozbeszerzesieljarasfajta`=`kozbeszerzesieljarasfajtai`.`kejid` and `kozbeszerzes`.`cpvkod`=`cpvkodok`.`cpvid`\n ";

        if (BeszSorszamKozbeszKereses.getText() != null && !BeszSorszamKozbeszKereses.getText().equals("")) {
            sql += "and `kozbeszerzes`.`sorszam` = '" + BeszSorszamKozbeszKereses.getText() + "' ";
        }
        if (SzerzNevKozbeszKereses.getText() != null && !SzerzNevKozbeszKereses.getText().equals("")) {
            sql += "and `kozbeszerzes`.`besznev` = '" + SzerzNevKozbeszKereses.getText() + "' ";
        }
        if (KozbeszAzonKozbeszKereses.getText() != null && !KozbeszAzonKozbeszKereses.getText().equals("")) {
            sql += "and keljarasazon`kozbeszerzes`.`keljarasazon` = '" + KozbeszAzonKozbeszKereses.getText() + "' ";
        }
       
        if (KozbeszFajtKozbeszKereses.getValue() != null) {
            sql += "and `kozbeszerzesieljarasfajtai`.`kozbeszerzesieljarasfajtai` = '" + KozbeszFajtKozbeszKereses.getValue() + "' ";
        }
        if (SzerzFajtKozbeszKereses.getValue() != null) {
            sql += "and `szerzodesfajtai`.`szerzodesfajta` = '" + SzerzFajtKozbeszKereses.getValue() + "' ";
        }
        if (CPVKozbeszKereses.getValue() != null) {
            sql += "and `cpvkodok`.`cpvkod` = '" + CPVKozbeszKereses.getValue() + "' ";
        }
        if (ProjektKozbeszKereses.getValue() != null) {
            sql += "and `projektek`.`projekt` = '" + ProjektKozbeszKereses.getValue() + "' ";
        }

        if (KozbeszTolKozbeszKereses.getValue() != null) {
            sql += "and `kozbeszerzes`.`kozbeszkezdete` >= '" + KozbeszTolKozbeszKereses.getValue() + "' ";
        }
        if (KozbeszIgKozbeszKereses.getValue() != null) {
            sql += "and `kozbeszerzes`.`kozbeszkezdete` <= '" + KozbeszIgKozbeszKereses.getValue() + "' ";
        }

        if (KozbeszLezarTolKozbeszKereses.getValue() != null) {
            sql += "and `kozbeszerzes`.`kozbeszvege` >= '" + KozbeszLezarTolKozbeszKereses.getValue() + "' ";
        }
        if (KozbeszLezarIgKozbeszKereses.getValue() != null) {
            sql += "and `kozbeszerzes`.`kozbeszvege` <= '" + KozbeszLezarIgKozbeszKereses.getValue() + "' ";
        }
        if (BecsultErtekMinKozbeszKereses.getText() != null) {
            sql += "and `kozbeszerzes`.`bertek` >= '" + BecsultErtekMinKozbeszKereses.getText() + "' ";
        }
        if (BecsultErtekMaxKozbeszKereses.getText() != null) {
            sql += "and `kozbeszerzes`.`bertek` <= '" + BecsultErtekMaxKozbeszKereses.getText() + "' ";
        }
        sql += "group by sorszam";
        System.out.println(sql);
        ArrayList<Kozbeszerzes> KozbeszerzesekLista = null;
        try {
           
                    
                    KozbeszerzesekLista = serverImpl.kozbeszKereses(sql);
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
//        projektEgybentartasLista.add(new ProjektEgybentartas("projekt neve", "15"));
        KozbeszerzesekTable.setItems(FXCollections.observableArrayList(KozbeszerzesekLista));
        
        
    }

    @FXML
    private void keresesVissza(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlKozbeszKeresesVissza.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("fomenu.fxml"));
        Scene scene = new Scene(root);
        File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }
    
}
