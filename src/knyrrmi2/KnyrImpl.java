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
import model.Beszerzes;
import model.DataEgybentartas;
import model.Kozbeszerzes;
import model.ProjektEgybentartas;
import model.SerializableResultSet;
import model.Szerzodes;
import model.SzerzodoFel;

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
            Logger.getLogger(KnyrImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return data;
    }

    @Override
    public void createConnection() throws RemoteException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://br-cdbr-azure-south-a.cloudapp.net:3306/adattar?zeroDateTimeBehavior=convertToNull", "bae55a4830fff7", "0e873d5e");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KnyrImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(KnyrImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void closeConnection() throws RemoteException {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(KnyrImpl.class.getName()).log(Level.SEVERE, null, ex);
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
        closeConnection();
    }

    @Override
    public SerializableResultSet adatbazisReport(String sql) throws SQLException, RemoteException {
        createConnection();
        
        Statement stmt = conn.createStatement();
        System.out.println(sql);
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("lekérdezés");
   //     closeConnection();
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
            Logger.getLogger(KnyrImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return data;
    }

    @Override
    public ArrayList<Kozbeszerzes> kozbeszKereses(String sql) throws RemoteException {
        createConnection();
        ArrayList<Kozbeszerzes> data = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            String[] colnames = new String[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
            }
            while (rs.next()) {
                Kozbeszerzes kozbesz
                        = new Kozbeszerzes(rs.getObject(1).toString(),rs.getObject(2).toString(),
                            rs.getObject(3).toString(), rs.getObject(4).toString(),
                            rs.getObject(5).toString(), rs.getObject(6).toString(), rs.getObject(7).toString(),
                            rs.getObject(8).toString(), rs.getObject(9).toString(), rs.getObject(10).toString());
                data.add(kozbesz);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KnyrImpl.class.getName()).log(Level.SEVERE, null, ex);
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
                        = new Szerzodes(rs.getObject(1).toString(),rs.getObject(2).toString(),
                            rs.getObject(3).toString(), rs.getObject(4).toString(),
                            rs.getObject(5).toString(), rs.getObject(6).toString(), rs.getObject(7).toString());
                data.add(szerzodes);
            }
        } catch (SQLException ex) {
            Logger.getLogger(KnyrImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return data;
    }
  
    @Override
    public ArrayList<SzerzodoFel> szerzodoFelKereses(String sql) throws RemoteException {
        createConnection();
        ArrayList<SzerzodoFel> data = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            String[] colnames = new String[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
            }
            
            while (rs.next()) {
                System.out.println("Object 3: " + (rs.getObject(3)));
                System.out.println("Object 3: " + (rs.getObject(4)));
                SzerzodoFel szerzodofel
                        = new SzerzodoFel (rs.getObject(1).toString(), rs.getObject(2).toString(),
                            
                                (rs.getObject(3) == null ? "null" : rs.getObject(3).toString()), (rs.getObject(4) == null ? "null" : rs.getObject(4).toString()),
                            rs.getObject(5).toString(), rs.getObject(6).toString(),
                            rs.getObject(7).toString(), rs.getObject(8).toString(),
                            rs.getObject(9).toString(), rs.getObject(10).toString(), 
                            rs.getObject(11).toString(), rs.getObject(12).toString(), rs.getObject(13).toString(),
                            rs.getObject(14).toString());
                
                data.add(szerzodofel);
                System.out.println(szerzodofel.getSzerzodofel());
            }
        } catch (SQLException ex) {
            Logger.getLogger(KnyrImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           closeConnection();
        }
        return data;
    }
    
     public ArrayList<String> jelszoKereses(String sql) throws RemoteException {
        createConnection();
        ArrayList<String> data = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            String[] colnames = new String[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
            }
            
            while (rs.next()) {
                
                String jelszoKapott = rs.getObject(1).toString();
                data.add(jelszoKapott);
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(KnyrImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
           closeConnection();
        }
        return data;
    }
    
     @Override
    public ArrayList<Beszerzes> beszerzesKereses(String sql) throws RemoteException {
        createConnection();
        ArrayList<Beszerzes> data = new ArrayList<>();
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            String[] colnames = new String[rsmd.getColumnCount()];
            for (int i = 0; i < rsmd.getColumnCount(); i++) {
                colnames[i] = rsmd.getColumnName(i + 1);
            }
            while (rs.next()) {
                Beszerzes beszerzes
                        = new Beszerzes(rs.getObject(1).toString(),rs.getObject(2).toString(),
                            rs.getObject(3).toString(), rs.getObject(4).toString(),
                            rs.getObject(5).toString(), rs.getObject(6).toString(), rs.getObject(7).toString()
                        , rs.getObject(8).toString(), rs.getObject(9).toString(), rs.getObject(10).toString()
                        , (rs.getObject(11) == null ? "null" : rs.getObject(11).toString()), (rs.getObject(12) == null ? "null" : rs.getObject(12).toString()), (rs.getObject(13) == null ? "null" : rs.getObject(13).toString())
                        , (rs.getObject(14) == null ? "null" : rs.getObject(14).toString()), (rs.getObject(15) == null ? "null" : rs.getObject(15).toString()), (rs.getObject(16) == null ? "null" : rs.getObject(16).toString())
                        , (rs.getObject(17) == null ? "null" : rs.getObject(17).toString()), (rs.getObject(18) == null ? "null" : rs.getObject(18).toString()), (rs.getObject(19) == null ? "null" : rs.getObject(19).toString())
                        , (rs.getObject(20) == null ? "null" : rs.getObject(20).toString()), (rs.getObject(21) == null ? "null" : rs.getObject(21).toString()));
                data.add(beszerzes);
           //     System.out.println(beszerzes.getBesznev());
            }
        } catch (SQLException ex) {
            Logger.getLogger(KnyrImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            closeConnection();
        }
        return data;
    }
    
}
