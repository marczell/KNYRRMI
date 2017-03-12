/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knyrrmi2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DataEgybentartas;
import model.ProjektEgybentartas;
import model.SerializableResultSet;
import model.Szerzodes;

/**
 *
 * @author Marcell
 */
public class KnyrImpl extends UnicastRemoteObject implements KnyrInterface {

    private Connection conn = null;
    
    public KnyrImpl() throws RemoteException {
    }

    @Override
    public ArrayList<DataEgybentartas> cpvEgybOsszes(String sql) throws RemoteException {
        createConnection();
        ArrayList<DataEgybentartas> data = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            String[] colnames = new String[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
            }
            while (rs.next()) {
                DataEgybentartas cpvEgybentartas
                        = new DataEgybentartas(rs.getObject(1).toString(), rs.getObject(2).toString());
                data.add(cpvEgybentartas);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kapcsolat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return data;
    }

    @Override
    public void createConnection() throws RemoteException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/knyr?zeroDateTimeBehavior=convertToNull", "root", "");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kapcsolat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Kapcsolat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void closeConnection() throws RemoteException {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Kapcsolat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void adatbazisbaInsertalas(String sql) throws SQLException, RemoteException {
        createConnection();

        Statement stmt = conn.createStatement();
//            stmt.execute();
        System.out.println(sql);
        stmt.executeUpdate(sql);
//            stmt.executeQuery(sql);
        System.out.println("beszúrás");
    }

    @Override
    public SerializableResultSet adatbazisReport(String sql) throws SQLException, RemoteException {
        createConnection();
        
        Statement stmt = conn.createStatement();
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("lekérdezés");
        return new SerializableResultSet(rs);
    }
    

    @Override
    public ArrayList<ProjektEgybentartas> projektEgybOsszes(String sql) throws RemoteException {
        createConnection();
        ArrayList<ProjektEgybentartas> data = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);

            ResultSetMetaData rsmd = rs.getMetaData();
            String[] colnames = new String[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
            }
            while (rs.next()) {
                ProjektEgybentartas projektEgybentartas
                        = new ProjektEgybentartas((String) rs.getObject(1), rs.getObject(2).toString());
                data.add(projektEgybentartas);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kapcsolat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return data;
    }

    @Override
    public ArrayList<Szerzodes> szerzodesKereses(String sql) throws RemoteException {
        createConnection();
        ArrayList<Szerzodes> data = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            String[] colnames = new String[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
            }
            while (rs.next()) {
                Szerzodes szerzodes
                        = new Szerzodes(rs.getObject(1).toString(), rs.getObject(2).toString(),
                            rs.getObject(3).toString(), rs.getObject(8).toString(),
                            rs.getObject(5).toString(), rs.getObject(6).toString(),
                            rs.getObject(7).toString(), rs.getObject(8).toString(),
                            rs.getObject(9).toString(), rs.getObject(10).toString());
                data.add(szerzodes);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Kapcsolat.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return data;
    }
    
}
