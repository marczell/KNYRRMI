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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author zsofi
 */
public class SzerzrogzitesController implements Initializable {

    @FXML
    private MenuItem menuKilepes;
    @FXML
    private MenuItem menuKijelentkezes;
    @FXML
    private Label uzenet;
    @FXML
    private Label txtBeszSorszam;
    @FXML
    private TextField SzerzNevSzerz;
    @FXML
    private TextField KozbeszAzon;
    @FXML
    private TextField BecsErtek;
    @FXML
    private ChoiceBox<?> KozbeszfajtSzerz;
    @FXML
    private ChoiceBox<?> SzerzFajtSzerz;
    @FXML
    private ChoiceBox<?> CpvSzerz;
    @FXML
    private ChoiceBox<?> ProjektSzerz;
    @FXML
    private DatePicker KozbeszKezd;
    @FXML
    private DatePicker KozbeszVege;
    @FXML
    private ChoiceBox<?> SzerzfelSzerz;
    @FXML
    private TextField SzerzTargya;
    @FXML
    private TextField SzerzertekSzerz;
    @FXML
    private DatePicker SzerzkotSzerz;
    @FXML
    private DatePicker SzerzlezarSzerz;
    @FXML
    private Button CtrlSzerzMentes;
    @FXML
    private Button CtrlSzerzVissza;
    @FXML
    private TextField SzerzAzonSzerz;
    
    KnyrInterface serverImpl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            serverImpl = (KnyrInterface) myRegistry.lookup("knyr");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(SzerzrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    

    @FXML
    private void mentesAction(ActionEvent event) {
    }

    @FXML
    private void visszaAction(ActionEvent event) {
    }
    
}
