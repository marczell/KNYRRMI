/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knyrrmi2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import model.Beszerzes;

/**
 *
 * @author zsofi
 */
public class KereseOsszesEredmenyController {

    @FXML
    private Label uzenet;
    @FXML
    private MenuItem menuKijelentkezes;
    @FXML
    private MenuItem menuBezaras;
    @FXML
    private Button CtrlKeresesVissza;
    @FXML
    private TableView<Beszerzes> SzerzodesekTable;
    @FXML
    private TableColumn<Beszerzes, String> tblSorszam;
    @FXML
    private TableColumn<Beszerzes, String> tblBeszNev;
    @FXML
    private TableColumn<Beszerzes, String> tblSzerzAzon;
    @FXML
    private TableColumn<Beszerzes, String> tblSzerzFel;
    @FXML
    private TableColumn<Beszerzes, String> tblSzerztargya;
    @FXML
    private TableColumn<Beszerzes, String> tblSzerzKot;
    @FXML
    private TableColumn<Beszerzes, String> tblSzerzErrtek;
    @FXML
    private TableColumn<Beszerzes, String> tblSzerzModAzon;
    @FXML
    private TableColumn<Beszerzes, String> tblSzerzmodDatum;
    
    
    public void initialize(URL url, ResourceBundle rb) {
      
        tblSorszam.setCellValueFactory(new PropertyValueFactory<Beszerzes, String>("sorszam"));
        tblBeszNev.setCellValueFactory(new PropertyValueFactory<Beszerzes, String>("besznev"));
        tblSzerzAzon.setCellValueFactory(new PropertyValueFactory<Beszerzes, String>("keljarasazon"));
        tblSzerzFel.setCellValueFactory(new PropertyValueFactory<Beszerzes, String>("szerzodoFel"));
        tblSzerztargya.setCellValueFactory(new PropertyValueFactory<Beszerzes, String>("szerztargy"));
        tblSzerzKot.setCellValueFactory(new PropertyValueFactory<Beszerzes, String>("szerzodeskotesdatuma"));
        tblSzerzErrtek.setCellValueFactory(new PropertyValueFactory<Beszerzes, String>("szerzodeserteke"));
        tblSzerzModAzon.setCellValueFactory(new PropertyValueFactory<Beszerzes, String>("szerzmodazon"));
        tblSzerzmodDatum.setCellValueFactory(new PropertyValueFactory<Beszerzes, String>("szerzmoddatum"));
         SzerzodesekTable.setRowFactory(tv -> {
           TableRow<Beszerzes> row = new TableRow<>();
           row.setOnMouseClicked(event -> {
             if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
             && event.getClickCount() == 2) {

                Beszerzes kivalasztott = row.getItem();
                Stage stage = (Stage) row.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader();
                Parent  root=null;
                 try {
                     root = loader.load(getClass().getResource("szerz_adatlap.fxml").openStream());
                 } catch (IOException ex) {
                     Logger.getLogger(KozbeszKeresesKontroller.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 AdatlapController szerzcon = (AdatlapController)loader.getController();
                 szerzcon.initData(kivalasztott);
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
  
    public void initData(ArrayList<Beszerzes> beszerzesek) {
        SzerzodesekTable.setItems(FXCollections.observableArrayList(beszerzesek));
    
  }
   
    @FXML
    private void keresesVissza(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlKeresesVissza.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("kereses_osszes.fxml"));
        Scene scene = new Scene(root);
        File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }
    
}
