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
import java.util.Arrays;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ErtekLista;
import model.Szerzodes;

/**
 * FXML Controller class
 *
 * @author Marcell
 */
public class KeresesOsszesController implements Initializable {

    private TextField SzerzNevKereses;
    private ComboBox<ErtekLista> KozbeszFajtKereses;
    private ComboBox<?> SzerzFajtKereses;
    private ComboBox<?> CPVKereses;
    private ComboBox<?> ProjektKereses;
    @FXML
    private DatePicker SzerzKotTolKereses;
    @FXML
    private DatePicker SzerzLezarTolKereses;
    @FXML
    private ComboBox<?> SzerzFelKereses;
    @FXML
    private DatePicker SzerzKotIgKereses;
    @FXML
    private DatePicker SzerzLezarIgKereses;
    @FXML
    private TextField SorszamKereses;
    private TableColumn<Szerzodes, String> SzerzKotKereses;
    private TableColumn<Szerzodes, String> SzerzLezarKereses;
    private TableColumn<Szerzodes, String> SzerzertekKereses;
    private Button CtrlKeresesVissza;
    private TableColumn<Szerzodes, String> SzerzNevKeres;
    private TableColumn<Szerzodes, String> SzerzFelKeres;
    private TableColumn<Szerzodes, String> KozbeszFajtKeres;
    private TableColumn<Szerzodes, String> SzerzFajtKeres;
    private TableColumn<Szerzodes, String> CPVKeres;
    private TableColumn<Szerzodes, String> ProjektKeres;
        KnyrInterface serverImpl;
 
   

    ArrayList<ErtekLista> listKej = new ArrayList<>();
    ArrayList<ErtekLista> listSzerzF = new ArrayList<>();
    ArrayList<ErtekLista> listCpv = new ArrayList<>();
    ArrayList<ErtekLista> listProjekt = new ArrayList<>();
    ArrayList<ErtekLista> listSzerzFel = new ArrayList<>();
    
    @FXML
    private Label uzenet;
    private TableView<Szerzodes> SzerzodesekTable;
    @FXML
    private Button CtrlKozbeszKeresesVissza;
    @FXML
    private TextField SzerzNevKozbeszKereses;
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
    private TextField KozbeszSorszamKozbeszKereses;
    @FXML
    private MenuItem menuKijelentkezes;
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

    private void kereses(ActionEvent event) {
//        System.out.println(KozbeszFajtKereses.getId());
//        System.out.println(idKereso(ertekListaLista));
        if (SzerzKotTolKereses.getValue() != null && SzerzKotTolKereses.getValue().isAfter(SzerzKotIgKereses.getValue())) {
            uzenet.setText("Az -ig dátum nem lehet nagyobb a -tól dátumnál!");
        } else {
            uzenet.setText("");
        }
        if (SzerzLezarTolKereses.getValue() != null && SzerzLezarTolKereses.getValue().isAfter(SzerzLezarIgKereses.getValue())) {
            uzenet.setText("Az -ig dátum nem lehet nagyobb a -tól dátumnál!");
        } else {
            uzenet.setText("");
        }
        String sql;
        sql = "select sorszam, szerzodesneve, szf.szerzodofel, kef.kozbeszerzesieljarasfajtai, "
                + "szfaj.szerzodesfajta, c.cpvkod, p.projekt, sz.szerzodeskotesdatuma, "
                + "sz.szerzodestervezettlezarasa, sz.szerzodeserteke \n"
                + "from szerzodes sz, projektek p, szerzodo_fel szf, szerzodesfajtai szfaj, "
                + "kozbeszerzesieljarasfajtai kef, cpvkodok c\n"
                + "where sz.projekt=p.projektid "
                + "and sz.szerzodofel= szf.szfid "
                + "and sz.szerzodesfajtaja=szfaj.szerzodesfajtaid "
                + "and sz.kozbeszerzesieljarasfajta=kef.kejid "
                + "and sz.cpvkod=c.cpvid\n ";

        if (SzerzNevKereses.getText() != null && !SzerzNevKereses.getText().equals("")) {
            sql += "and szerzodesneve = '" + SzerzNevKereses.getText() + "' ";
        }
        if (SzerzFelKereses.getValue() != null) {
            sql += "and szerzodofel = '" + SzerzFelKereses.getId() + "' ";
        }

        if (KozbeszFajtKereses.getValue() != null) {
            sql += "and kozbeszerzesieljarasfajta <= '" + KozbeszFajtKereses.getValue() + "' ";
        }
        if (SzerzFajtKereses.getValue() != null) {
            sql += "and szerzodesfajtaja <= '" + SzerzKotIgKereses.getValue() + "' ";
        }
        if (CPVKereses.getValue() != null) {
            sql += "and cpvkod = '" + CPVKereses.getValue() + "' ";
        }
        if (ProjektKereses.getValue() != null) {
            sql += "and projekt = '" + ProjektKereses.getValue() + "' ";
        }

        if (SzerzKotTolKereses.getValue() != null) {
            sql += "and sz.szerzodeskotesdatuma >= '" + SzerzKotTolKereses.getValue() + "' ";
        }
        if (SzerzKotIgKereses.getValue() != null) {
            sql += "and sz.szerzodeskotesdatuma <= '" + SzerzKotIgKereses.getValue() + "' ";
        }

        if (SzerzLezarTolKereses.getValue() != null) {
            sql += "and sz.szezodeskotesdatuma >= '" + SzerzLezarTolKereses.getValue() + "' ";
        }
        if (SzerzLezarIgKereses.getValue() != null) {
            sql += "and sz.szerzodeskotesdatuma <= '" + SzerzLezarIgKereses.getValue() + "' ";
        }
        sql += "group by sorszam";
        System.out.println(sql);
        ArrayList<Szerzodes> projektEgybentartasLista = null;
        try {
            projektEgybentartasLista = //new ArrayList<>();
                    serverImpl.szerzodesKereses(sql);
        } catch (RemoteException ex) {
            Logger.getLogger(KeresesController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        projektEgybentartasLista.add(new ProjektEgybentartas("projekt neve", "15"));
        SzerzodesekTable.setItems(FXCollections.observableArrayList(projektEgybentartasLista));
    }

    @FXML
    private void keresesVissza(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlKeresesVissza.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("fomenu.fxml"));
        Scene scene = new Scene(root);
        File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }

}
