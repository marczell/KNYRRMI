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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marcell
 */
public class FomenuController implements Initializable {

    private Button CtrlSzerzfel;
    private Button CtrlSzerz;
    private Button CtrlCpv;
    private Button CtrlProjekt;
    private Button CtrlKeres;
    private Button CtrlErtek;
    private Button CtrlKozbesz;
    private Button CtrlSzerzmod;
    @FXML
    private Label uzenet;
    @FXML
    private TextField FelhNev;
    @FXML
    private PasswordField Password;
    @FXML
    private Button CtrlBelepes;
    
    KnyrInterface serverImpl;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            serverImpl = (KnyrInterface) myRegistry.lookup("knyr");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(BelepesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void szerzodoFelRogziteseAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlSzerzfel.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("szerzfelrogzites.fxml"));
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void szerzodesRogziteseAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlSzerz.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("kozbeszkereses.fxml"));
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void cpvEgybeszamitasAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlCpv.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("CPV_egyb.fxml"));
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void projektEgybenszamitasAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlProjekt.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("projekt_egyb.fxml"));
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void keresesAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlKeres.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("kereses_osszes.fxml"));
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void erteklistakAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlErtek.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("erteklista_mod.fxml"));
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }
    @FXML    
    private void kozbeszRogzitesAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlKozbesz.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("kozbeszrogzites.fxml"));
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    } 
     @FXML
    private void SzerzModRogzitesAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlSzerzmod.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("erteklista_mod.fxml"));
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }    
}
