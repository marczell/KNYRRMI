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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ErtekLista;
import model.SerializableResultSet;

/**
 *
 * @author zsofi
 */
public class BelepesController implements Initializable{

    @FXML
    private Label uzenet;
    @FXML
    private TextField FelhNev;
    @FXML
    private PasswordField Password;
    @FXML
    private Button CtrlBelepes;
    
    KnyrInterface serverImpl;
    @FXML
    private MenuItem menuBezaras;

   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            Registry myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            serverImpl = (KnyrInterface) myRegistry.lookup("knyr");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(BelepesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
     @FXML
    private void belepes(ActionEvent event) {
        boolean belephet = false;
        String felhnev = FelhNev.getText();
        String jelszo = Password.getText();
        
        ArrayList<String> jelszavak = new ArrayList<>();
        String sql = "SELECT `user`.`JELSZO` FROM `adattar`.`user` WHERE NEV = '" + FelhNev.getText()+"'";
        System.out.println(sql);
        try {
            jelszavak = serverImpl.jelszoKereses(sql);
        } catch (RemoteException ex) {
            Logger.getLogger(BelepesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NullPointerException e){
            Logger.getLogger(BelepesController.class.getName()).log(Level.SEVERE, null, e);
            uzenet.setText("Nincs ilyen felhasználó!");
        }
        Iterator it = jelszavak.iterator();
 
            while(it.hasNext()) {
            String jelszoKapott = (String)it.next();
            System.out.println(jelszoKapott);
            if (jelszoKapott.equals(jelszo)){
                    belephet = true;
                }
        }
       
        if (belephet == true){
       Stage stage = (Stage) CtrlBelepes.getScene().getWindow();
                Parent root = null;
                 try {
                     root = FXMLLoader.load(getClass().getResource("fomenu.fxml"));
                 } catch (IOException ex) {
                     Logger.getLogger(BelepesController.class.getName()).log(Level.SEVERE, null, ex);
                 }
        Scene scene = new Scene(root);
         File f = new File("alkfejl.css");
        scene.getStylesheets().clear();
        scene.getStylesheets().add("file:///" + f.getAbsolutePath().replace("\\", "/"));
        stage.setScene(scene);
        stage.show();
        }
        else {
            uzenet.setText("Hibás jelszó.");
            Password.clear();
        }
}
//        @FXML
//      private void closeAction(ActionEvent event){
//     Platform.exit();
////        // get a handle to the stage
////        Stage stage = (Stage) menuKilepes.getScene().getWindow();
////        // do what you have to do
////        stage.close();
//}
}
