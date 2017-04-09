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
import model.Beszerzes;
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
    private TableView<Beszerzes> KozbeszerzesekTable;
    @FXML
    private TableColumn<Beszerzes, String> KözbeszSorszamKozbeszKereses;
    @FXML
    private TableColumn<Beszerzes, String> KozbeszAzonKozbeszKeres;
    @FXML
    private TableColumn<Beszerzes, String> BeszNevKozbeszKeres;
    @FXML
    private TableColumn<Beszerzes, String> KozbeszFajtKozbeszKeres;
    @FXML
    private TableColumn<Beszerzes, String> SzerzFajtKozbeszKeres;
    @FXML
    private TableColumn<Beszerzes, String> CPVKozbeszKeres;
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
    
    
    public void initialize(URL url, ResourceBundle rb) {
        
       
    }
    
   
        

    @FXML
    private void keresesVissza(ActionEvent event) {
    }
    
}
