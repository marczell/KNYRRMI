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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Beszerzes;
import model.ErtekLista;

/**
 * FXML Controller class
 *
 * @author Marcell
 */
public class KeresesOsszesController implements Initializable {

    @FXML
    private TextField SorszamKereses;
    @FXML
    private TextField BesznevKereses;
    @FXML
    private TextField KozbeszAzonKereses;
    @FXML
    private ComboBox<?> KozbeszFajtKereses;
    @FXML
    private ComboBox<?> SzerzFajtaKereses;
    @FXML
    private ComboBox<?> CPVKereses;
    @FXML
    private ComboBox<?> ProjektKereses;
     @FXML
    private DatePicker KozbeszKezdetTolKereses;
    @FXML
    private DatePicker KozbeszKezdetIgKereses;
    @FXML
    private DatePicker KozbeszVegeTolKereses;
    @FXML
    private DatePicker KozbeszVegeIgKereses;
    @FXML
    private TextField BecsultErtekMinKereses;
    @FXML
    private TextField BecsultErtekMaxKereses;
    @FXML
    private DatePicker SzerzKotTolKereses;
    @FXML
    private DatePicker SzerzLezarTolKereses;
    @FXML
    private ComboBox<?> SzerzFelKereses;
     @FXML
    private TextField SzerzazonKereses;
    @FXML
    private DatePicker SzerzKotIgKereses;
    @FXML
    private DatePicker SzerzLezarIgKereses;
     @FXML
    private TextField SzerzErtekMinKereses;
    @FXML
    private TextField SzerzErtekMaxKereses;
    @FXML
    private Button CtrlKereses;
       
    KnyrInterface serverImpl;
    Registry myRegistry;

    @FXML
    private Label uzenet;
    @FXML
    private Button CtrlKozbeszKeresesVissza;
    @FXML
    private MenuItem menuKijelentkezes;
    @FXML
    private MenuItem menuBezaras;

    ArrayList<ErtekLista> listKej = new ArrayList<>();
    ArrayList<ErtekLista> listSzerzF = new ArrayList<>();
    ArrayList<ErtekLista> listCpv = new ArrayList<>();
    ArrayList<ErtekLista> listProjekt = new ArrayList<>();
    ArrayList<ErtekLista> listSzerzFel = new ArrayList<>();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            serverImpl = (KnyrInterface) myRegistry.lookup("knyr");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String sql1 = "SELECT KEJID, KOZBESZERZESIELJARASFAJTAI FROM KOZBESZERZESIELJARASFAJTAI";//meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        try {
            ResultSet rs = null;
            try {
                rs = serverImpl.adatbazisReport(sql1);
            } catch (RemoteException ex) {
                Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ObservableList obListKej = FXCollections.observableArrayList();
            while (rs.next()) {
                listKej.add(new ErtekLista(rs.getObject(1).toString(),
                        rs.getString(2)));
                obListKej.add(rs.getObject(2).toString());
            }
            KozbeszFajtKereses.getItems().clear();
            KozbeszFajtKereses.setItems(FXCollections.observableList(obListKej));
        } catch (SQLException ex) {
            Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String sql2 = "SELECT SZERZODESFAJTAID, SZERZODESFAJTA FROM SZERZODESFAJTAI";//meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql2);
            ObservableList obListSzerzF = FXCollections.observableArrayList();
            while (rs.next()) {
                listSzerzF.add(new ErtekLista(rs.getObject(1).toString(),
                        rs.getString(2)));
                obListSzerzF.add(rs.getObject(2).toString());
            }
            SzerzFajtaKereses.getItems().clear();
            SzerzFajtaKereses.setItems(FXCollections.observableList(obListSzerzF));
        } catch (SQLException ex) {
            Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } catch (RemoteException ex) {
            Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally { 
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       String sql3 = "SELECT CPVID, CPVKOD FROM CPVKODOK";
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql3);
            ObservableList obListCpv = FXCollections.observableArrayList();
            while (rs.next()) {
                listCpv.add(new ErtekLista(rs.getObject(1).toString(),
                       rs.getObject(2).toString()));
                obListCpv.add(rs.getObject(2).toString());
            }
            CPVKereses.getItems().clear();
            CPVKereses.setItems(FXCollections.observableList(obListCpv));
        } catch (SQLException ex) {
            Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } catch (RemoteException ex) {
            Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        String sql4 = "SELECT PROJEKTID, PROJEKT FROM PROJEKTEK";//meg kell nézni , hogy az oszlopot valóban lathatónak hívják e
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql4);
            ObservableList obListProjekt = FXCollections.observableArrayList();
            while (rs.next()) {
                listProjekt.add(new ErtekLista(rs.getObject(1).toString(),
                        rs.getObject(2).toString()));
                obListProjekt.add(rs.getString(2));
            }
            ProjektKereses.getItems().clear();
            ProjektKereses.setItems(FXCollections.observableList(obListProjekt));
        } catch (SQLException ex) {
            Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } catch (RemoteException ex) {
            Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String sql5 = "SELECT SZFID, SZERZODOFEL FROM SZERZODO_FEL";
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql5);
            ObservableList obListSzerzFel = FXCollections.observableArrayList();
            while (rs.next()) {
                listSzerzFel.add(new ErtekLista(rs.getObject(1).toString(),
                        rs.getString(2)));
                obListSzerzFel.add(rs.getObject(2).toString());
            }
            SzerzFelKereses.getItems().clear();
            SzerzFelKereses.setItems(FXCollections.observableList(obListSzerzFel));
        } catch (SQLException ex) {
            Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } catch (RemoteException ex) {
            Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           try {
                serverImpl.closeConnection();
            } catch (RemoteException ex) {
                Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    @FXML
    private void kereses(ActionEvent event) {
//        System.out.println(KozbeszFajtKereses.getId());
//        System.out.println(idKereso(ertekListaLista));
        if (KozbeszKezdetTolKereses.getValue() != null && KozbeszKezdetTolKereses.getValue().isAfter(KozbeszKezdetIgKereses.getValue())) {
            uzenet.setText("A közbeszerzése kezdete -ig dátum nem lehet nagyobb a -tól dátumnál!");
        } 
        if (KozbeszVegeTolKereses.getValue() != null && KozbeszVegeTolKereses.getValue().isAfter(KozbeszVegeIgKereses.getValue())) {
            uzenet.setText("A közbeszerzés vége -ig dátum nem lehet nagyobb a -tól dátumnál!");
        }       
        if (SzerzKotTolKereses.getValue() != null && SzerzKotTolKereses.getValue().isAfter(SzerzKotIgKereses.getValue())) {
            uzenet.setText("A szerződéskötés -ig dátum nem lehet nagyobb a -tól dátumnál!");
        } 
        if (SzerzLezarTolKereses.getValue() != null && SzerzLezarTolKereses.getValue().isAfter(SzerzLezarIgKereses.getValue())) {
            uzenet.setText("A szerződés tervezett lezárása -ig dátum nem lehet nagyobb a -tól dátumnál!");
        } 
 //       if (BecsultErtekMinKereses!=null && BecsultErtekMaxKereses !=null && (Integer.parseInt(BecsultErtekMinKereses.getText())>Integer.parseInt(BecsultErtekMaxKereses.getText()))){
  //      uzenet.setText("A becsült érték minimum összege nem lehet nagyobb a maximum összegnél.");
  //      } 
//        if (SzerzErtekMinKereses!=null && SzerzErtekMaxKereses !=null && (Integer.parseInt(SzerzErtekMinKereses.getText())>Integer.parseInt(SzerzErtekMaxKereses.getText()))){
//        uzenet.setText("A szerződött érték minimum összege nem lehet nagyobb a maximum összegnél.");
//        } 
        String sql;
        sql = "SELECT\n" +
        "    k.sorszam,\n" +
        "    k.besznev,\n" +
        "    k.keljarasazon,\n" +
        "    k.bertek,\n" +
        "    kef.kozbeszerzesieljarasfajtai,\n" +
        "    szf.szerzodesfajta,\n" +
        "    c.cpvkod,\n" +
        "    p.projekt,\n" +
        "    k.kozbeszkezdete,\n" +
        "    k.kozbeszvege,\n" +
        "    sz.szerzazon,\n" +
        "    sz.szerzodeserteke,\n" +
        "    sz.szerztargy,\n" +
        "    sz.szerzodeskotesdatuma,\n" +
        "    sz.szerzodestervezettlezarasa,\n" +
        "    szef.szerzodofel,\n" +
        "    szm.szerzmodazon,\n" +
        "    szm.szerzmodertek,\n" +
        "    szm.szerzmodtargy,\n" +
        "    szm.szerzmoddatum,\n" +
        "    szm.szerzmodvege\n" +
        "FROM\n" +
        "    kozbeszerzes k\n" +
        "        INNER JOIN\n" +
        "    kozbeszerzesieljarasfajtai kef ON kef.kejid = k.kozbeszerzesieljarasfajta\n" +
        "        INNER JOIN\n" +
        "    szerzodesfajtai szf ON szf.szerzodesfajtaid = k.szerzodesfajtaja\n" +
        "        INNER JOIN\n" +
        "    cpvkodok c ON c.cpvid = k.cpvkod\n" +
        "        INNER JOIN\n" +
        "    projektek p ON p.projektid = k.projekt\n" +
        "        LEFT JOIN\n" +
        "    szerzodes sz\n" +
        "        INNER JOIN\n" +
        "    szerzodo_fel szef ON szef.szfid = sz.szerzodofel ON k.sorszam = sz.sorszam\n" +
        "        LEFT JOIN\n" +
        "    szerzmodositas szm ON sz.szerzazon = szm.szerzazon WHERE k.sorszam IS NOT NULL";
       if (!BesznevKereses.getText().isEmpty()) {
            sql += " and k.besznev = '" + BesznevKereses.getText()+ "'";
        }
        if (!SorszamKereses.getText().isEmpty()) {
            sql += " and k.sorszam = '" + SorszamKereses.getText()+ "'";
        }
        if (!KozbeszAzonKereses.getText().isEmpty()) {
            sql += " and k.keljarasazon = '" + KozbeszAzonKereses.getText()+ "'";
        }
        if (KozbeszFajtKereses.getValue() != null) {
            sql += " and kef.kozbeszerzesieljarasfajtai = '" + KozbeszFajtKereses.getValue() + "'";
        }
         if (SzerzFajtaKereses.getValue() != null) {
            sql += " and szf.szerzodesfajta = '" + SzerzFajtaKereses.getValue() + "'";
        }
         if (CPVKereses.getValue() != null) {
            sql += " and c.cpvkod = '" + CPVKereses.getValue() + "'";
        }
        if (ProjektKereses.getValue() != null) {
            sql += " and p.projekt = '" + ProjektKereses.getValue() + "'";
        }
        if (KozbeszKezdetTolKereses.getValue() != null) {
            sql += " and k.kozbeszkezdete >= '" + KozbeszKezdetTolKereses.getValue() + "'";
        }
        if (KozbeszKezdetIgKereses.getValue() != null) {
            sql += " and k.kozbeszkezdete <= '" + KozbeszKezdetIgKereses.getValue() + "'";
        }
        if (KozbeszVegeTolKereses.getValue() != null) {
            sql += " and k.kozbeszvege >= '" + KozbeszVegeTolKereses.getValue() + "'";
        }
        if (KozbeszVegeIgKereses.getValue() != null) {
            sql += " and k.kozbeszvege <= '" + KozbeszVegeIgKereses.getValue() + "'";
        }
        if (!BecsultErtekMinKereses.getText().isEmpty()) {
            sql += " and k.bertek >= '" + BecsultErtekMinKereses.getText() + "'";
        }
        if (!BecsultErtekMaxKereses.getText().isEmpty()) {
            sql += " and k.bertek <= '" + BecsultErtekMaxKereses.getText() + "'";
        }
        if (SzerzFelKereses.getValue() != null) {
            sql += " and szef.szerzodofel = '" + SzerzFelKereses.getId() + "'";
        }
        if (!SzerzazonKereses.getText().isEmpty()) {
            sql += " and sz.szerzazon = '" + SzerzazonKereses.getText()+ "'";
        }
        if (SzerzKotTolKereses.getValue() != null) {
            sql += " and sz.szerzodeskotesdatuma >= '" + SzerzKotTolKereses.getValue() + "'";
        }
        if (SzerzKotIgKereses.getValue() != null) {
            sql += " and sz.szerzodeskotesdatuma <= '" + SzerzKotIgKereses.getValue() + "'";
        }
        if (SzerzLezarTolKereses.getValue() != null) {
            sql += " and sz.szerzodestervezettlezarasa>= '" + SzerzLezarTolKereses.getValue() + "'";
        }
        if (SzerzLezarIgKereses.getValue() != null) {
            sql += " and sz.szerzodestervezettlezarasa <= '" + SzerzLezarIgKereses.getValue() + "'";
        }
        if (!SzerzErtekMinKereses.getText().isEmpty()) {
            sql += " and sz.szerzodeserteke >= '" + SzerzErtekMinKereses.getText() + "'";
        }
        if (!SzerzErtekMaxKereses.getText().isEmpty()) {
            sql += " and sz.szerzodeserteke <= '" + SzerzErtekMaxKereses.getText() + "'";
        }
        
        System.out.println(sql);
        ArrayList<Beszerzes> beszerzesLista = null;
        try {
            beszerzesLista =  serverImpl.beszerzesKereses(sql);
      //      System.out.println(beszerzesLista.get(0).getBesznev());
        } catch (RemoteException ex) {
            Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NullPointerException e){
            Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, e);
            uzenet.setText("Nincs ilyen beszerzés!");
        }
        Stage stage = (Stage) CtrlKereses.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        Parent  root=null;
         try {
             root = loader.load(getClass().getResource("keresesosszes_eredmeny.fxml").openStream());
         } catch (IOException ex) {
             Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         
         KereseOsszesEredmenyController osszescon = (KereseOsszesEredmenyController)loader.getController();
         osszescon.initData(beszerzesLista);
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
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
    @FXML
    public void bezarasAction(ActionEvent event) throws IOException{
        menuBezaras.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        System.exit(0);
          try {
              myRegistry.unbind("knyr");
          } catch (RemoteException ex) {
              Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (NotBoundException ex) {
              Logger.getLogger(KeresesOsszesController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
      }
    });
}
}
