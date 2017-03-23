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
import model.Szerzodes;
import model.SzerzodoFel;

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
    private TableView<Szerzodes> KozbeszerzesekTable;
    @FXML
    private TableColumn<Szerzodes, String> tblBeszSorszamKozbeszKereses;
    @FXML
    private TableColumn<Szerzodes, String> tblKozbeszAzonKozbeszKeres;
    @FXML
    private TableColumn<Szerzodes, String> tblBeszNevKozbeszKeres;
    @FXML
    private TableColumn<Szerzodes, String> tblKozbeszFajtKozbeszKeres;
    @FXML
    private TableColumn<Szerzodes, String> tblSzerzFajtKozbeszKeres;
    @FXML
    private TableColumn<Szerzodes, String> tblCPVKozbeszKeres;
    @FXML
    private TableColumn<Szerzodes, String> tblProjektKozbeszKeres;
    @FXML
    private TableColumn<Szerzodes, String> tblKozbeszKezdetKozbeszKereses;
    @FXML
    private TableColumn<Szerzodes, String> tblKozbeszVegeKozbeszKereses;
    @FXML
    private TableColumn<Szerzodes, String> tblBecsultErtKozbeszKereses;

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
        tblBeszSorszamKozbeszKereses.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("sorszam"));
        tblKozbeszAzonKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("keljarasAzon"));
        tblBeszNevKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("beszerzesNeve"));
        tblKozbeszFajtKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("kozbeszerzesFajtaja"));
        tblSzerzFajtKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodesFajtaja"));
        tblCPVKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("cpvKod"));
        tblProjektKozbeszKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("projekt"));
        tblKozbeszKezdetKozbeszKereses.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("kozbeszKezdete"));
        tblKozbeszVegeKozbeszKereses.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("kozbeszVege"));
        tblBecsultErtKozbeszKereses.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("becsultErtek"));
        
        KozbeszerzesekTable.setRowFactory(tv -> {
           TableRow<Szerzodes> row = new TableRow<>();
           row.setOnMouseClicked(event -> {
             if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
             && event.getClickCount() == 2) {

                Szerzodes kivalasztott = row.getItem();
                Stage stage = (Stage) row.getScene().getWindow();
                Parent root = null;
                 try {
                     root = FXMLLoader.load(getClass().getResource("KozbeszrogzitesController.fxml"));
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
        
        String sql1 = "SELECT KOZBESZERZESIELJARASFAJTAI, KEJID FROM KOZBESZERZESIELJARASFAJTAI WHERE LATHATO=TRUE";//meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql1);
            ObservableList obListKej = FXCollections.observableArrayList();
            while (rs.next()) {
                listKej.add(new ErtekLista(new BigDecimal(rs.getString("KEJID")),
                        rs.getString("KOZBESZERZESIELJARASFAJTAI")));
                obListKej.add(rs.getString("KOZBESZERZESIELJARASFAJTAI"));
            }
            KozbeszFajtKozbeszKereses.getItems().clear();
            KozbeszFajtKozbeszKereses.setItems(FXCollections.observableList(obListKej));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
         
        }
        String sql2 = "SELECT SZERZODESFAJTAID, SZERZODESFAJTA FROM SZERZODESFAJTAI WHERE LATHATO=TRUE";//meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql2);
            ObservableList obListSzerzF = FXCollections.observableArrayList();
            while (rs.next()) {
                listSzerzF.add(new ErtekLista(new BigDecimal(rs.getString("SZERZODESFAJTAID")),
                        rs.getString("SZERZODESFAJTA")));
                obListSzerzF.add(rs.getString("SZERZODESFAJTA"));
            }
            SzerzFajtKozbeszKereses.getItems().clear();
            SzerzFajtKozbeszKereses.setItems(FXCollections.observableList(obListSzerzF));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
        //meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        String sql3 = "SELECT CPVKOD, CPVID FROM CPVKODOK WHERE LATHATO=TRUE";
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql3);
            ObservableList obListCpv = FXCollections.observableArrayList();
            while (rs.next()) {
                listCpv.add(new ErtekLista(new BigDecimal(rs.getString("CPVID")),
                        rs.getString("CPVKOD")));
                obListCpv.add(rs.getString("CPVKOD"));
            }
            CPVKozbeszKereses.getItems().clear();
            CPVKozbeszKereses.setItems(FXCollections.observableList(obListCpv));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
        String sql4 = "SELECT PROJEKT, PROJEKTID FROM PROJEKTEK WHERE LATHATO=TRUE";//meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql4);
            ObservableList obListProjekt = FXCollections.observableArrayList();
            while (rs.next()) {
                listProjekt.add(new ErtekLista(new BigDecimal(rs.getString("PROJEKTID")),
                        rs.getString("PROJEKT")));
                obListProjekt.add(rs.getString("PROJEKT"));
            }
            ProjektKozbeszKereses.getItems().clear();
            ProjektKozbeszKereses.setItems(FXCollections.observableList(obListProjekt));
        } catch (SQLException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
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
        sql = "select sorszam, besznev, keljarasazon, bertek, kef.kozbeszerzesieljarasfajta, "
                + "szfaj.szerzodesfajtaja, c.cpvkod, p.projekt, sz.kozbeszkezdete, "
                + "sz.kozbeszvege, \n"
                + "from szerzodes sz, projektek p, szerzodesfajtai szfaj, "
                + "kozbeszerzesieljarasfajtai kef, cpvkodok c\n"
                + "where sz.projekt=p.projektid "
                + "and sz.szerzodesfajtaja=szfaj.szerzodesfajtaid "
                + "and sz.kozbeszerzesieljarasfajta=kef.kejid "
                + "and sz.cpvkod=c.cpvid\n ";

        if (BeszSorszamKozbeszKereses.getText() != null && !BeszSorszamKozbeszKereses.getText().equals("")) {
            sql += "and sorszam = '" + BeszSorszamKozbeszKereses.getText() + "' ";
        }
        if (SzerzNevKozbeszKereses.getText() != null && !SzerzNevKozbeszKereses.getText().equals("")) {
            sql += "and besznev = '" + SzerzNevKozbeszKereses.getText() + "' ";
        }
        if (KozbeszAzonKozbeszKereses.getText() != null && !KozbeszAzonKozbeszKereses.getText().equals("")) {
            sql += "and keljarasazon = '" + KozbeszAzonKozbeszKereses.getText() + "' ";
        }
       
        if (KozbeszFajtKozbeszKereses.getValue() != null) {
            sql += "and kozbeszerzesieljarasfajta <= '" + KozbeszFajtKozbeszKereses.getValue() + "' ";
        }
        if (SzerzFajtKozbeszKereses.getValue() != null) {
            sql += "and szerzodesfajtaja <= '" + SzerzFajtKozbeszKereses.getValue() + "' ";
        }
        if (CPVKozbeszKereses.getValue() != null) {
            sql += "and cpvkod = '" + CPVKozbeszKereses.getValue() + "' ";
        }
        if (ProjektKozbeszKereses.getValue() != null) {
            sql += "and projekt = '" + ProjektKozbeszKereses.getValue() + "' ";
        }

        if (KozbeszTolKozbeszKereses.getValue() != null) {
            sql += "and sz.kozbeszkezdete >= '" + KozbeszTolKozbeszKereses.getValue() + "' ";
        }
        if (KozbeszIgKozbeszKereses.getValue() != null) {
            sql += "and sz.kozbeszkezdete <= '" + KozbeszIgKozbeszKereses.getValue() + "' ";
        }

        if (KozbeszLezarTolKozbeszKereses.getValue() != null) {
            sql += "and sz.kozbeszvege >= '" + KozbeszLezarTolKozbeszKereses.getValue() + "' ";
        }
        if (KozbeszLezarIgKozbeszKereses.getValue() != null) {
            sql += "and sz.kozbeszvege <= '" + KozbeszLezarIgKozbeszKereses.getValue() + "' ";
        }
        if (BecsultErtekMinKozbeszKereses.getText() != null) {
            sql += "and bertek >= '" + BecsultErtekMinKozbeszKereses.getText() + "' ";
        }
        if (BecsultErtekMaxKozbeszKereses.getText() != null) {
            sql += "and bertek <= '" + BecsultErtekMaxKozbeszKereses.getText() + "' ";
        }
        sql += "group by sorszam";
        System.out.println(sql);
        ArrayList<Szerzodes> projektEgybentartasLista = null;
        try {
           
                    
                    serverImpl.szerzodesKereses(sql);
        } catch (RemoteException ex) {
            Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
//        projektEgybentartasLista.add(new ProjektEgybentartas("projekt neve", "15"));
        KozbeszerzesekTable.setItems(FXCollections.observableArrayList(projektEgybentartasLista));
        
        
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
