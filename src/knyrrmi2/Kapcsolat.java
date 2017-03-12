package knyrrmi2;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DataEgybentartas;
import model.ProjektEgybentartas;
import model.Szerzodes;

/**
 *
 * @author Marcell
 */
public class Kapcsolat {

    private Connection conn = null;

    private void createConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/knyr?zeroDateTimeBehavior=convertToNull", "root", "");
            conn = DriverManager.getConnection("jdbc:mysql://eu-cdbr-azure-north-e.cloudapp.net:3306/knyrmy?zeroDateTimeBehavior=convertToNull&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8", "b7a129b7fa7986", "f64d84e6");
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/knyr?zeroDateTimeBehavior=convertToNull?user='root'&password=''&useUnicode=true&characterEncoding=utf8&characterSetResults=utf8");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Kapcsolat.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Kapcsolat.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(Kapcsolat.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

//    public boolean login(String user, String jelszo) {
//        createConnection();
//        try {
//            Statement stmt = conn.createStatement();
////            stmt.execute();
//            String sql = "SELECT NEV, ADMINE, SZERKESZTHET FROM USER "
//                    + "WHERE JELSZO='" + jelszo + "' "
//                    + "and NEV='" + user + "'";
//            System.out.println(sql);
//            ResultSet rs = stmt.executeQuery(sql);
//            ResultSetMetaData rsmd = rs.getMetaData();
//            System.out.println("lekérdezés");
//            while (rs.next()) {
//                felhasznalok = new Felhasznalok(rs.getString(1),
//                        Integer.parseInt(rs.getString(2)) == 1,
//                        Integer.parseInt(rs.getString(3)) == 1);
//            }
//            return felhasznalok != null;
//        } catch (SQLException ex) {
//            Logger.getLogger(Kapcsolat.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            closeConnection();
//        }
//        return false;
//    }
    public void adatbazisbaInsertalas(String sql) throws SQLException {
        createConnection();

        Statement stmt = conn.createStatement();
//            stmt.execute();
        System.out.println(sql);
        stmt.executeUpdate(sql);
//            stmt.executeQuery(sql);
        System.out.println("beszúrás");

    }

    public ResultSet adatbazisReport(String sql) throws SQLException { //metódus sql selecthez
        createConnection();

        Statement stmt = conn.createStatement();
//            stmt.execute();
        System.out.println(sql);
        //stmt.executeUpdate(sql);
        ResultSet rs = stmt.executeQuery(sql);
        System.out.println("lekérdezés");
        return rs;

    }

    public ArrayList<ProjektEgybentartas> projektEgybOsszes(String sql) {
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

    public ArrayList<DataEgybentartas> cpvEgybOsszes(String sql) {
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

    ArrayList<Szerzodes> szerzodesKereses(String sql) {
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
