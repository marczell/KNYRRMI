/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knyrrmi2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marcell
 */
public class FomenuController implements Initializable {

    @FXML
    private Button CtrlSzerzfel;
    @FXML
    private Button CtrlSzerz;
    @FXML
    private Button CtrlCpv;
    @FXML
    private Button CtrlProjekt;
    @FXML
    private Button CtrlKeres;
    @FXML
    private Button CtrlErtek;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
        Stage stage = (Stage) CtrlSzerzfel.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("szerzrogzites.fxml"));
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void cpvEgybeszamitasAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlSzerzfel.getScene().getWindow();
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
        Stage stage = (Stage) CtrlSzerzfel.getScene().getWindow();
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
        Stage stage = (Stage) CtrlSzerzfel.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("kereses.fxml"));
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void erteklistakAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlSzerzfel.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("erteklista_mod.fxml"));
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }    
}
