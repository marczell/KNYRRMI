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
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.SzerzodoFel;

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
    private TextField Kozterulet;
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
    @FXML
    private Button CtrlSzerzfelKereses;
    
    KnyrInterface serverImpl;       
    Registry myRegistry;

    @FXML
    private Label uzenet;
    @FXML
    private Label szerzfelid;
    @FXML
    private TableView<SzerzodoFel> SzerzfelTable;
    @FXML
    private TableColumn<SzerzodoFel, String> tblszfid;
    @FXML
    private TableColumn<SzerzodoFel, String> tblSzerzfel;
    @FXML
    private TableColumn<SzerzodoFel, String> tblVaros;
    @FXML
    private TableColumn<SzerzodoFel, String> tblIrszam;
    @FXML
    private TableColumn<SzerzodoFel, String> tblHazszam;
    @FXML
    private TableColumn<SzerzodoFel, String> tblKozterulet;
    @FXML
    private TableColumn<SzerzodoFel, String> tblCegjszam;
    @FXML
    private TableColumn<SzerzodoFel, String> tblAdoszam;
    @FXML
    private TableColumn<SzerzodoFel, String> tblKapcstarto;
    @FXML
    private MenuItem menuBezaras;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
            myRegistry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            serverImpl = (KnyrInterface) myRegistry.lookup("knyr");
        } catch (RemoteException | NotBoundException ex) {
            Logger.getLogger(SzerzfelrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        }
       tblszfid.setCellValueFactory(new PropertyValueFactory<SzerzodoFel, String>("szfid"));
       tblSzerzfel.setCellValueFactory(new PropertyValueFactory<SzerzodoFel, String>("szerzodofel"));
        tblVaros.setCellValueFactory(new PropertyValueFactory<SzerzodoFel, String>("szekhelyVaros"));
        tblIrszam.setCellValueFactory(new PropertyValueFactory<SzerzodoFel, String>("szekhelyIranyitoszam"));
        tblHazszam.setCellValueFactory(new PropertyValueFactory<SzerzodoFel, String>("szekhelyHazszam"));
        tblKozterulet.setCellValueFactory(new PropertyValueFactory<SzerzodoFel, String>("szekhelyKozterulet"));
        tblCegjszam.setCellValueFactory(new PropertyValueFactory<SzerzodoFel, String>("cegjegyzekszam"));
        tblAdoszam.setCellValueFactory(new PropertyValueFactory<SzerzodoFel, String>("adoszam"));
        tblKapcstarto.setCellValueFactory(new PropertyValueFactory<SzerzodoFel, String>("kapcsolattartoNeve"));
       // SzerzodoFel szerzodofel = SzerzfelTable.getSelectionModel().getSelectedItem();
        //táblázta sorának kiválasztása
        SzerzfelTable.setRowFactory((TableView<SzerzodoFel> tv) -> {
           TableRow<SzerzodoFel> row = new TableRow<>();
           row.setOnMouseClicked((MouseEvent event) -> {
             if (! row.isEmpty() && event.getButton()==MouseButton.PRIMARY 
             && event.getClickCount() == 2) {

                SzerzodoFel kivalasztott = row.getItem();
                szerzfelid.setText(kivalasztott.getSzfid());
                SzerzFel.setText(kivalasztott.getSzerzodofel());
                Varos.setText(kivalasztott.getSzekhelyVaros());
                Irszam.setText(kivalasztott.getSzekhelyIranyitoszam());
                Kozterulet.setText(kivalasztott.getSzekhelyKozterulet());
                Hazszam.setText(kivalasztott.getSzekhelyHazszam());
                Telszam.setText(kivalasztott.getTelefonszam());
                Faxszam.setText(kivalasztott.getFaxszam());
                Email.setText(kivalasztott.getEMail());
                Cegjszam.setText(kivalasztott.getCegjegyzekszam());
                Adoszam.setText(kivalasztott.getAdoszam());
                Kapcstartnev.setText(kivalasztott.getKapcsolattartoNeve());
                Kapcstarttelszam.setText(kivalasztott.getKapcsolattartoTel());
                Kapcstartemail.setText(kivalasztott.getKapcsolattartoEmail());
            
        }
    });
    return row ;
});
    }
    
    @FXML
    private void mentesAction(ActionEvent event) throws RemoteException {
       String sql;
        //a szerződő fél adatainak ellenőrzése
                if (SzerzFel.getText().length() <= 100 &&  SzerzFel.getText().length() >= 1   
                        && Varos.getText().length() <= 50 && Varos.getText().length() >= 1
                        && Irszam.getText().matches("[0-9]{4}")
                        && Kozterulet.getText().length() <= 100  && Kozterulet.getText().length() >= 1
                        && Hazszam.getText().matches("[0-9]{1,3}")
                        && Telszam.getText().length() <= 25 && Telszam.getText().length() >= 1
                        && Faxszam.getText().length() <= 25 && Faxszam.getText().length() >= 1
                        && Email.getText().matches(EMAIL_PATTERN)
                        && Cegjszam.getText().matches("[0-9]{2}-[0-9]{2}-[0-9]{6}")//2-2-6
                        && Adoszam.getText().matches("[0-9]{8}-[0-9]{1}-[0-9]{2}")//8-1-2
                        && Kapcstartnev.getText().length() <= 100 && Kapcstartnev.getText().length() >= 100
                        && Kapcstarttelszam.getText().length() <= 25 && Kapcstarttelszam.getText().length() >= 25
                        && Kapcstartemail.getText().matches(EMAIL_PATTERN)) {
                     if (szerzfelid.getText().equals("")){

                    sql = "INSERT INTO `szerzodo_fel`(`szerzodofel`, `szekhely-varos`, `szekhely-iranyitoszam`, `szekhely-kozterulet`, \n"
                    + "`szekhely-hazszam`, `telefonszam`, `faxszam`, `e-mail`, `cegjegyzekszam`, `adoszam`, `kapcsolattarto-neve`,\n"
                    + "`kapcsolattarto-tel`, `kapcsolattarto-email`) \n"
                    + "VALUES ('" + SzerzFel.getText() + "','" + Varos.getText() + "'," + Irszam.getText() + ",'" + Kozterulet.getText() + "',"
                    + Hazszam.getText() + ",'" + Telszam.getText() + "','" + Faxszam.getText() + "','" + Email.getText() + "','"
                    + Cegjszam.getText() + "','" + Adoszam.getText() + "','" + Kapcstartnev.getText() + "','"
                    + Kapcstarttelszam.getText() + "','" + Kapcstartemail.getText() + "')";
                     System.out.println(sql);
                } else{
                        sql = "UPDATE `adattar`.`szerzodo_fel` SET `szfid` = "+ szerzfelid.getText()+",`szerzodofel`='"+SzerzFel.getText()+"',"
                            +"`szekhely-varos`='"+Varos.getText()+"',`szekhely-iranyitoszam`="+ Irszam.getText() +" ,"
                            +"`szekhely-kozterulet`='" + Kozterulet.getText() + "',`szekhely-hazszam`="+ Hazszam.getText()+","
                            +"`telefonszam`=" + Telszam.getText() + ",`faxszam`=" + Faxszam.getText() + ",`e-mail`='" + Email.getText() + "',"
                            +"`cegjegyzekszam`="+ Cegjszam.getText() + ",`adoszam`=" + Adoszam.getText() + ","
                            +"`kapcsolattarto-neve`='" + Kapcstartnev.getText() + "',`kapcsolattarto-tel`="+ Kapcstarttelszam.getText() + ","
                            +"`kapcsolattarto-email`='" + Kapcstartemail.getText()+"' WHERE `szfid` = "+ szerzfelid.getText();
                             System.out.println(sql);  
                }
            
            
            try {
                serverImpl.adatbazisbaInsertalas(sql);
                uzenet.setText("Sikeres mentése a " + SzerzFel.getText()+ " szerződőfélnek!");
                szerzfelid.setText("");
                SzerzFel.clear();
                Varos.clear();
                Irszam.clear();
                Kozterulet.clear();
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
               
            }
        } else {
            uzenet.setText("Hibás adatok!");
        }
    }
    
@FXML
    private void keresesAction(ActionEvent event) {
        String sql;
        sql = "SELECT `szfid`, `szerzodofel`, `szekhely-varos`, `szekhely-iranyitoszam`,"
                + " `szekhely-kozterulet`, `szekhely-hazszam`, `telefonszam`, `faxszam`, `e-mail`,"
                + " `cegjegyzekszam`, `adoszam`, `kapcsolattarto-neve`, `kapcsolattarto-tel`, "
                + "`kapcsolattarto-email` FROM `szerzodo_fel` WHERE szfid IS NOT null \n ";

        if (SzerzFel.getText() != null && !SzerzFel.getText().equals("")) {
            sql += "and szerzodofel = '" + SzerzFel.getText() + "' ";
        }
        if (Varos.getText() != null && !Varos.getText().equals("")) {
            sql += "and szekhely-varos = '" + Varos.getText() + "' ";
        }
        if (Irszam.getText() != null && !Irszam.getText().equals("")) {
            sql += "and szekhely-iranyitoszam = '" + Irszam.getText() + "' ";
        }
        if (Kozterulet.getText() != null && !Kozterulet.getText().equals("")) {
            sql += "and szekhely-kozterulet = '" + Kozterulet.getText() + "' ";
        }
        if (Hazszam.getText() != null && !Hazszam.getText().equals("")) {
            sql += "and szekhely-hazszam = '" + Hazszam.getText() + "' ";
        }
       if (Telszam.getText() != null && !Telszam.getText().equals("")) {
            sql += "and telefonszam = '" + Telszam.getText() + "' ";
        }
        if (Faxszam.getText() != null && !Faxszam.getText().equals("")) {
            sql += "and faxszam = '" + Faxszam.getText() + "' ";
        }
        if (Email.getText() != null && !Email.getText().equals("")) {
            sql += "and e-mail = '" + Email.getText() + "' ";
        }
        if (Cegjszam.getText() != null && !Cegjszam.getText().equals("")) {
            sql += "and cegjegyzekszam = '" + Cegjszam.getText() + "' ";
        }
        if (Adoszam.getText() != null && !Adoszam.getText().equals("")) {
            sql += "and adoszam = '" + Adoszam.getText() + "' ";
        }
        if (Kapcstartnev.getText() != null && !Kapcstartnev.getText().equals("")) {
            sql += "and kapcsolattarto-neve = '" + Kapcstartnev.getText() + "' ";
        }
        if (Kapcstarttelszam.getText() != null && !Kapcstarttelszam.getText().equals("")) {
            sql += "and kapcsolattarto-tel = '" + Kapcstarttelszam.getText() + "' ";
        }
        if (Kapcstartemail.getText() != null && !Kapcstartemail.getText().equals("")) {
            sql += "and kapcsolattarto-email = '" + Kapcstartemail.getText() + "' ";
        }
        sql += "group by szfid";
        System.out.println(sql);
        ArrayList<SzerzodoFel> szerzodoFelLista = new ArrayList<>();
        try {
           szerzodoFelLista = serverImpl.szerzodoFelKereses(sql);
           SzerzfelTable.setItems(FXCollections.observableArrayList(szerzodoFelLista));
  //         Iterator<SzerzodoFel> itr = szerzodoFelLista.iterator();
    //    while(itr.hasNext()){
      //      SzerzodoFel next = itr.next();
        //    System.out.println(next.getSzerzodofel());
      //  }
           

        } catch (RemoteException ex) {
            Logger.getLogger(SzerzfelrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NullPointerException e){
            Logger.getLogger(SzerzfelrogzitesController.class.getName()).log(Level.SEVERE, null, e);
            uzenet.setText("Nincs ilyen Szerződő fél!");
        }

        SzerzfelTable.setItems(FXCollections.observableArrayList(szerzodoFelLista));
        SzerzfelTable.getColumns().setAll(tblszfid, tblSzerzfel, tblVaros, tblIrszam, tblHazszam, tblKozterulet, tblCegjszam, tblAdoszam, tblKapcstarto);
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
        @FXML
    public void bezarasAction(ActionEvent event) throws IOException{
        menuBezaras.setOnAction(new EventHandler<ActionEvent>() {
      public void handle(ActionEvent event) {
        System.exit(0);
          try {
              myRegistry.unbind("knyr");
          } catch (RemoteException ex) {
              Logger.getLogger(SzerzfelrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
          } catch (NotBoundException ex) {
              Logger.getLogger(SzerzfelrogzitesController.class.getName()).log(Level.SEVERE, null, ex);
          }
        
      }
    });

}
}
