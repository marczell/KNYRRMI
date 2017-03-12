/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knyrrmi2;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.ResourceBundle;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.DataEgybentartas;

/**
 * FXML Controller class
 *
 * @author Marcell
 */
public class CPV_egybController implements Initializable {

    @FXML
    private Button CtrlCpvEgybe;
    @FXML
    private DatePicker CtrlCpvTol;
    @FXML
    private DatePicker CtrlCpvIg;
    @FXML
    private Button CtrlCpvVissza;
    @FXML
    private TableView<DataEgybentartas> cpvEgybenTable;
    @FXML
    private TableColumn<DataEgybentartas, String> cpvEgybNev;
    @FXML
    private TableColumn<DataEgybentartas, String> cpvEgybErtek;
    
    //private Kapcsolat kapcsolat = new Kapcsolat();
    @FXML
    private Label hibaLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cpvEgybNev.setCellValueFactory(new PropertyValueFactory<DataEgybentartas, String>("nev"));
        cpvEgybErtek.setCellValueFactory(new PropertyValueFactory<DataEgybentartas, String>("ertek"));
    }

    @FXML
    private void lekerdezesAction(ActionEvent event) {
        if (CtrlCpvTol.getValue()!=null && CtrlCpvTol.getValue().isAfter(CtrlCpvIg.getValue())) {
            hibaLabel.setText("Az -ig dátum nem lehet nagyobb a -tól dátumnál!");
        } else {
            hibaLabel.setText("");
        }
        String sql;
        sql = "select c.cpvkod, sum(sz.szerzodeserteke) as osszeg \n"
                + "from cpvkodok c, szerzodes sz \n"
                + "where sz.cpvkod=c.cpvid ";
        if (CtrlCpvTol.getValue() != null && CtrlCpvTol.getValue() != null) {
            sql += "and sz.szerzodeskotesdatuma >= '" + CtrlCpvTol.getValue() + "' ";
        }
        if (CtrlCpvIg.getValue() != null) {
            sql += "and sz.szerzodeskotesdatuma <= '" + CtrlCpvIg.getValue() + "' ";
        }
        sql += "group by c.cpvkod";        
        System.out.println(sql);
        
        ArrayList<DataEgybentartas> dataEgybentartasLista=null;
        try {
            Registry myRegistry= LocateRegistry.getRegistry("127.0.0.1",1099);
            KnyrInterface serverImpl = (KnyrInterface)myRegistry.lookup("knyr");
            dataEgybentartasLista=serverImpl.cpvEgybOsszes(sql);
            cpvEgybenTable.setItems(FXCollections.observableArrayList(dataEgybentartasLista));
            //KnyrInterface knyrInterface = (KnyrInterface) Naming.lookup("almafa");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
//        cpvEgybentartasLista.add(new CpvEgybentartas("cpv neve", "15"));
        
    }

    @FXML
    private void visszaAction(ActionEvent event) throws IOException {
        Stage stage = (Stage) CtrlCpvVissza.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("fomenu.fxml"));
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
    }

}
