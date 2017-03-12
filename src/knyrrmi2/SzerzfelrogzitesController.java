/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knyrrmi2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Marcell
 */
public class SzerzfelrogzitesController implements Initializable {

    private static final String EMAIL_PATTERN
            = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @FXML
    private Button CtrlSzerzfelMentes;
    @FXML
    private TextField SzerzFel;
    @FXML
    private TextField Varos;
    @FXML
    private TextField Irszam;
    @FXML
    private TextField Koterulet;
    @FXML
    private TextField Hazszam;
    @FXML
    private TextField Telszam;
    @FXML
    private TextField Faxszam;
    @FXML
    private TextField Email;
    @FXML
    private TextField Cegjszam;
    @FXML
    private TextField Adoszam;
    @FXML
    private TextField Kapcstartnev;
    @FXML
    private TextField Kapcstarttelszam;
    @FXML
    private TextField Kapcstartemail;
    @FXML
    private Button CtrlSzerzfelVissza;

    private Kapcsolat kapcsolat = new Kapcsolat();

    @FXML
    private Label uzenet;

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
    private void mentesAction(ActionEvent event) {
        //a szerződő fél adatainak rögzítése
                if (SzerzFel.getText().length() <= 100
                        && Varos.getText().length() <= 50
                        && Irszam.getText().matches("[0-9]{4}")
                        && Koterulet.getText().length() <= 100
                        && Hazszam.getText().matches("[0-9]{1,3}")
                        && Telszam.getText().length() <= 25
                        && Faxszam.getText().length() <= 25
                        && Email.getText().matches(EMAIL_PATTERN)
                        && Cegjszam.getText().matches("[0-9]{2}-[0-9]{2}-[0-9]{6}")//2-2-6
                        && Adoszam.getText().matches("[0-9]{8}-[0-9]{1}-[0-9]{2}")//8-1-2
                        && Kapcstartnev.getText().length() <= 100
                        && Kapcstarttelszam.getText().length() <= 25
                        && Kapcstartemail.getText().matches(EMAIL_PATTERN)) {
//        if (true) { //teszteléshez egyszerübb adatbevitel
            String sql = "INSERT INTO `szerzodo_fel`(`szerzodofel`, `szekhely-varos`, `szekhely-iranyitoszam`, `szekhely-kozterulet`, \n"
                    + "`szekhely-hazszam`, `telefonszam`, `faxszam`, `e-mail`, `cegjegyzekszam`, `adoszam`, `kapcsolattarto-neve`,\n"
                    + "`kapcsolattarto-tel`, `kapcsolattarto-email`) \n"
                    + "VALUES ('" + SzerzFel.getText() + "','" + Varos.getText() + "'," + Irszam.getText() + ",'" + Koterulet.getText() + "',"
                    + Hazszam.getText() + ",'" + Telszam.getText() + "','" + Faxszam.getText() + "','" + Email.getText() + "','"
                    + Cegjszam.getText() + "','" + Adoszam.getText() + "','" + Kapcstartnev.getText() + "','"
                    + Kapcstarttelszam.getText() + "','" + Kapcstartemail.getText() + "')";
            System.out.println(sql);
            try {
                kapcsolat.adatbazisbaInsertalas(sql);
                uzenet.setText("Sikeres mentése a " + SzerzFel.getText());
                SzerzFel.clear();
                Varos.clear();
                Irszam.clear();
                Koterulet.clear();
                Hazszam.clear();
                Telszam.clear();
                Faxszam.clear();
                Email.clear();
                Cegjszam.clear();
                Adoszam.clear();
                Kapcstartnev.clear();
                Kapcstarttelszam.clear();
                Kapcstartemail.clear();
                
            } catch (SQLException ex) {
                Logger.getLogger(SzerzfelrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
                uzenet.setText("Hiba az adatbázis művelet során!");
            } finally {
                kapcsolat.closeConnection();
            }
        } else {
            uzenet.setText("Hibás adatok!");
        }
    }

    @FXML
    private void visszaAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlSzerzfelVissza.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("fomenu.fxml"));
        Scene scene = new Scene(root);
        File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }
}
