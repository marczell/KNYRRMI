/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knyrrmi2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import model.DataEgybentartas;
import model.ProjektEgybentartas;
import model.SerializableResultSet;
import model.Szerzodes;

/**
 *
 * @author Marcell
 */
public interface KnyrInterface extends Remote{
    String NAME = "KNYR";
    
    public void createConnection() throws RemoteException;
    public void closeConnection() throws RemoteException;
    public void adatbazisbaInsertalas(String sql) throws SQLException,RemoteException;
    public SerializableResultSet adatbazisReport(String sql) throws SQLException,RemoteException;
    
    public ArrayList<DataEgybentartas> cpvEgybOsszes(String sql) throws RemoteException;
    public ArrayList<ProjektEgybentartas> projektEgybOsszes(String sql) throws RemoteException;
    public ArrayList<Szerzodes> szerzodesKereses(String sql) throws RemoteException;
}
