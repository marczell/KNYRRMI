/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knyrrmi2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private TableView<?> KozbeszerzesekTable;
    @FXML
    private TableColumn<?, ?> KözbeszSorszamKozbeszKereses;
    @FXML
    private TableColumn<?, ?> KozbeszAzonKozbeszKeres;
    @FXML
    private TableColumn<?, ?> BeszNevKozbeszKeres;
    @FXML
    private TableColumn<?, ?> KozbeszFajtKozbeszKeres;
    @FXML
    private TableColumn<?, ?> SzerzFajtKozbeszKeres;
    @FXML
    private TableColumn<?, ?> CPVKozbeszKeres;
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

    @FXML
    private void keresesVissza(ActionEvent event) {
    }
    
}
