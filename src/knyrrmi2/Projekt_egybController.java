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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.ProjektEgybentartas;

/**
 * FXML Controller class
 *
 * @author Marcell
 */
public class Projekt_egybController implements Initializable {

    @FXML
    private Button CtrlPrEgybe;
    @FXML
    private DatePicker PrEgybeTol;
    @FXML
    private DatePicker PrEgybeIg;
    @FXML
    private TableView<ProjektEgybentartas> TablePrEgybe;
    @FXML
    private TableColumn<ProjektEgybentartas, String> PrEgybeProjekt;
    @FXML
    private TableColumn<ProjektEgybentartas, String> PrEgybeOsszeg;
    @FXML
    private Button CtrlPrEgybeVissza;

    @FXML
    private Label hibaLabel;
    
    KnyrInterface serverImpl;
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
            Logger.getLogger(Projekt_egybController.class.getName()).log(Level.SEVERE, null, ex);
        }
        PrEgybeProjekt.setCellValueFactory(new PropertyValueFactory<ProjektEgybentartas, String>("projektNev"));
        PrEgybeOsszeg.setCellValueFactory(new PropertyValueFactory<ProjektEgybentartas, String>("projektErtek"));
    }

    @FXML
    private void visszaAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlPrEgybeVissza.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("fomenu.fxml"));
        Scene scene = new Scene(root);
        File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void lekerdezesAction(ActionEvent event) throws RemoteException {
        if (PrEgybeTol.getValue()!=null && PrEgybeTol.getValue().isAfter(PrEgybeIg.getValue())) {
            hibaLabel.setText("Az -ig dátum nem lehet nagyobb a -tól dátumnál!");
        } else {
            hibaLabel.setText("");
        }
        String sql;
        sql = "select p.projekt, sum(sz.bertek) as osszeg \n"
                + "from projektek p, kozbeszerzes sz \n"
                + "where sz.projekt=p.projektid \n";
        if (PrEgybeTol.getValue() != null) {
            sql += "and sz.kozbeszkezdete >= '" + PrEgybeTol.getValue() + "' ";
        }
        if (PrEgybeIg.getValue() != null) {
            sql += "and sz.kozbeszkezdete <= '" + PrEgybeIg.getValue() + "' ";
        }
        sql += "group by sz.projekt";
        System.out.println(sql);
        ArrayList<ProjektEgybentartas> projektEgybentartasLista = //new ArrayList<>();
                serverImpl.projektEgybOsszes(sql);
//        projektEgybentartasLista.add(new ProjektEgybentartas("projekt neve", "15"));
        TablePrEgybe.setItems(FXCollections.observableArrayList(projektEgybentartasLista));
    }

    @FXML
    private void tolValasztas(ActionEvent event) {
    }

    @FXML
    private void igValasztas(ActionEvent event) {
    }

}
