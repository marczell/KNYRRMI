/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knyrrmi2;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Beszerzes;

/**
 *
 * @author zsofi
 */
public class AdatlapController {

    @FXML
    private MenuItem menuKijelentkezes;
    @FXML
    private MenuItem menuBezaras;
    @FXML
    private Label uzenet;
    @FXML
    private TextField SzerzNevSzerz;
    @FXML
    private Label txtKozbeszSorszam;
    @FXML
    private TextField KozbeszAzon;
    @FXML
    private TextField BecsErtek;
    @FXML
    private TextField KozbeszfajtSzerz;
    @FXML
    private TextField SzerzFajtSzerz;
    @FXML
    private TextField CpvSzerz;
    @FXML
    private TextField ProjektSzerz;
    @FXML
    private DatePicker KozbeszKezd;
    @FXML
    private DatePicker KozbeszVege;
    @FXML
    private Label Sorszam;
    @FXML
    private TextField SzerzfelSzerz;
    @FXML
    private TextField SzerzTargya;
    @FXML
    private TextField SzerzertekSzerz;
    @FXML
    private DatePicker SzerzkotSzerz;
    @FXML
    private DatePicker SzerzlezarSzerz;
    @FXML
    private DatePicker SzerzkotSzerzMod;
    @FXML
    private DatePicker SzerzlezarSzerzMod;
    @FXML
    private TextField SzerzertekMod;
    @FXML
    private TextField SzerzTargyaMod;
    @FXML
    private Button CtrlSzerzVissza;
    @FXML
    private Label txtSzerzmodSorszam;

    public void initData(Beszerzes beszerzes) {
    
    SzerzNevSzerz .setText(beszerzes.getBesznev());
    txtKozbeszSorszam.setText(beszerzes.getSorszam());
    KozbeszAzon.setText(beszerzes.getKeljarasazon());
    BecsErtek.setText(beszerzes.getBertek());
    KozbeszfajtSzerz.setText(beszerzes.getKozbeszerzesieljarasfajta());
    SzerzFajtSzerz.setText(beszerzes.getSzerzodesfajtaja());
    CpvSzerz.setText(beszerzes.getCpvkod());
    ProjektSzerz.setText(beszerzes.getProjekt());
    KozbeszKezd.setValue(LocalDate.parse(beszerzes.getKozbeszkezdete()));
    KozbeszVege.setValue(LocalDate.parse(beszerzes.getKozbeszvege()));
    Sorszam.setText(beszerzes.getSzerzazon());
    SzerzfelSzerz.setText(beszerzes.getSzerzodoFel());
    SzerzertekSzerz.setText(beszerzes.getSzerzodeserteke());
    SzerzTargya.setText(beszerzes.getSzerztargy());
    SzerzkotSzerz.setValue(LocalDate.parse(beszerzes.getSzerzodeskotesdatuma()));
    SzerzlezarSzerz.setValue(LocalDate.parse(beszerzes.getSzerzodestervezettlezarasa()));
    txtSzerzmodSorszam.setText(beszerzes.getSzerzmodazon());
    SzerzertekMod.setText(beszerzes.getSzerzmodertek());
    SzerzkotSzerzMod.setValue(LocalDate.parse(beszerzes.getSzerzmoddatum()));
    SzerzlezarSzerzMod.setValue(LocalDate.parse(beszerzes.getSzerzmodvege()));
    SzerzTargyaMod.setText(beszerzes.getSzerzmodtargy());
  }
  
    @FXML
    private void visszaAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlSzerzVissza.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("kereses_osszes.fxml"));
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
      }
    });

}  
}
