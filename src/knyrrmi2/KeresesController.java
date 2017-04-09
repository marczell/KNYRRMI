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
 * FXML Controller class
 *
 * @author Marcell
 */
public class KeresesController implements Initializable {

    
    @FXML
    private Button CtrlKereses;
    @FXML
    private TextField SzerzAzonKereses;
    @FXML
    private TextField sorszamKereses;
    @FXML
    private TextField SzerzMinErtekKereses;
    @FXML
    private TextField SzerzMaxErtekKereses;
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
    private Label uzenet;
    @FXML
    private TableView<Szerzodes> SzerzodesekTable;
    @FXML
    private TableColumn<Szerzodes, String> SorszamKeres;
    @FXML
    private TableColumn<Szerzodes, String> SzerzAzonKeres;
    @FXML
    private TableColumn<Szerzodes, String> SzerzFelKeres;
    @FXML
    private TableColumn<Szerzodes, String> SzerztargyaKeres;
    @FXML
    private TableColumn<Szerzodes, String> SzerzKotKeres;
    @FXML
    private TableColumn<Szerzodes, String> SzerzLezarKeres;
    @FXML
    private TableColumn<Szerzodes, String> SzerzertekKeres;
    @FXML
    private Button CtrlKeresesVissza;
    @FXML
    private TextField SzerztargyKereses;
    KnyrInterface serverImpl;
 
   

    ArrayList<ErtekLista> listKej = new ArrayList<>();
    ArrayList<ErtekLista> listSzerzF = new ArrayList<>();
    ArrayList<ErtekLista> listCpv = new ArrayList<>();
    ArrayList<ErtekLista> listProjekt = new ArrayList<>();
    ArrayList<ErtekLista> listSzerzFel = new ArrayList<>();
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
        
        
       
        SorszamKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("sorszam"));
        SzerzAzonKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzazon"));
        SzerzFelKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodoFel"));
        SzerztargyaKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerztargy"));
        SzerzKotKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodeskotesdatuma"));
        SzerzLezarKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodestervezettlezarasa"));
        SzerzertekKeres.setCellValueFactory(new PropertyValueFactory<Szerzodes, String>("szerzodeserteke"));
       
       
        String sql5 = "SELECT SZFID, SZERZODOFEL FROM SZERZODO_FEL";
        try {
            ResultSet rs = serverImpl.adatbazisReport(sql5);
            ObservableList obListSzerzFel = FXCollections.observableArrayList();
            while (rs.next()) {
                listSzerzFel.add(new ErtekLista(rs.getObject(1).toString(),
                        rs.getObject(2).toString()));
                obListSzerzFel.add(rs.getObject(2).toString());
            }
            SzerzFelKereses.getItems().clear();
            SzerzFelKereses.setItems(FXCollections.observableList(obListSzerzFel));
        } catch (SQLException ex) {
            Logger.getLogger(KeresesController.class.getName()).log(Level.SEVERE, null, ex);
            uzenet.setText("Hiba az értékkeresés során!");// kell a felületre egy hibaüzenet label
        } catch (RemoteException ex) {
            Logger.getLogger(KeresesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
        }
           SzerzodesekTable.setRowFactory(tv -> {
           TableRow<Szerzodes> row = new TableRow<>();
           row.setOnMouseClicked(event -> {
             if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
             && event.getClickCount() == 2) {

                Szerzodes kivalasztott = row.getItem();
                Stage stage = (Stage) row.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                Parent  root=null;
                 try {
                     root = loader.load(getClass().getResource("szerzmodrogzites.fxml").openStream());
                 } catch (IOException ex) {
                     Logger.getLogger(KeresesController.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 SzerzmodrogzitesController szerzmodcon = (SzerzmodrogzitesController)loader.getController();
                 szerzmodcon.initData(kivalasztott);
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
    }
    
    @FXML
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
        sql = "SELECT sz.sorszam, sz.szerzazon, szf.szerzodofel, sz.szerzodeserteke, sz.szerztargy, sz.szerzodeskotesdatuma, sz.szerzodestervezettlezarasa\n" +
        "FROM szerzodes sz, szerzodo_fel szf\n" +
        "WHERE  sz.szerzodofel= szf.szfid";

       if (!sorszamKereses.getText().isEmpty()) {
            sql += "and sorszam = '" + sorszamKereses.getText() + "' ";
        }
        if (!SzerzAzonKereses.getText().isEmpty()) {
            sql += "and szerzazon = '" + SzerzAzonKereses.getText() + "' ";
        }
        if (SzerzFelKereses.getValue() != null) {
            sql += "and szerzodofel = '" + SzerzFelKereses.getValue() + "' ";
        }
        if (!SzerztargyKereses.getText().isEmpty()) {
            sql += "and szerztargy = '" + SzerztargyKereses.getText() + "' ";
        }
         if (!SzerzMinErtekKereses.getText().isEmpty()) {
            sql += "and sz.szerzodeserteke >= '" + SzerzMinErtekKereses.getText() + "' ";
        }
        if (!SzerzMaxErtekKereses.getText().isEmpty()) {
            sql += "and sz.szerzodeserteke <= '" + SzerzMaxErtekKereses.getText() + "' ";
        }
        if (SzerzKotTolKereses.getValue() != null) {
            sql += "and sz.szerzodeskotesdatuma >= '" + SzerzKotTolKereses.getValue() + "' ";
        }
        if (SzerzKotIgKereses.getValue() != null) {
            sql += "and sz.szerzodeskotesdatuma <= '" + SzerzKotIgKereses.getValue() + "' ";
        }

        if (SzerzLezarTolKereses.getValue() != null) {
            sql += "and sz.szerzodestervezettlezarasa >= '" + SzerzLezarTolKereses.getValue() + "' ";
        }
        if (SzerzLezarIgKereses.getValue() != null) {
            sql += "and sz.szerzodestervezettlezarasa <= '" + SzerzLezarIgKereses.getValue() + "' ";
        }
        sql += " group by szerzazon";
        System.out.println(sql);
        ArrayList<Szerzodes> szerzodesLista = null;
        try {
            szerzodesLista = //new ArrayList<>();
                    serverImpl.szerzodesKereses(sql);
        } catch (RemoteException ex) {
            Logger.getLogger(KeresesController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        projektEgybentartasLista.add(new ProjektEgybentartas("projekt neve", "15"));
        SzerzodesekTable.setItems(FXCollections.observableArrayList(szerzodesLista));
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
