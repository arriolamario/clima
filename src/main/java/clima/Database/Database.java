package clima.Database;

import clima.Model.Dia;
import clima.Model.Periodo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    Connection con;
    String url;
    private static final String TBL_CLIMA = "CREATE TABLE IF NOT EXISTS DATOS ("+
            "DIA INT,"+
            "CLIMA VARCHAR(100),"+
            "PLANETA1 DOUBLE,"+
            "PLANETA2 DOUBLE,"+
            "PLANETA3 DOUBLE,"+
            "PRIMARY KEY (DIA),"+
            ");";

    private  static  final String TBL_PERIODO = "CREATE TABLE IF NOT EXISTS PERIODO ("+
            "NOMBRE VARCHAR(100),"+
            "CANTIDAD INT,"+
            ");";
    private static Database database;

    public Database(){
        {
            //url = "jdbc:h2:mem:";
            url = "jdbc:h2:mem:meliclimabase";

        }
    }

    private Connection instancia() throws SQLException {
        if (con == null || con.isClosed()){
            con = DriverManager.getConnection(url);
        }

        return  con;
    }

    public void createTables() throws SQLException{
        Connection con = instancia();
        Statement stmt = con.createStatement();
        /*
        try{
            stmt.executeUpdate("DROP TABLE EXISTS PERIODO;");
            stmt.executeUpdate("DROP TABLE EXISTS DATOS;");
        }
        catch (Exception ex){
            System.out.println(ex);
        }
        */
        stmt.executeUpdate(TBL_CLIMA);
        stmt.executeUpdate(TBL_PERIODO);
        stmt.executeUpdate("COMMIT;");

        stmt.close();
    }

    public boolean insertClima(int dia, String clima, double planeta1, double planeta2, double planeta3) throws SQLException{
        Connection con = instancia();

        String sql = "INSERT INTO DATOS (DIA, CLIMA, PLANETA1, PLANETA2, PLANETA3) VALUES (?,?,?,?,?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, dia);
        stmt.setString(2, clima);
        stmt.setDouble(3, planeta1 );
        stmt.setDouble(4, planeta2);
        stmt.setDouble(5, planeta3);

        boolean result = stmt.execute();
        stmt.close();
        return  result;
    }

    public boolean insertPeriodo(String nombre, int cantidad) throws SQLException{
        Connection con = instancia();

        String sql = "INSERT INTO PERIODO (NOMBRE, CANTIDAD) VALUES (?,?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nombre);
        stmt.setInt(2, cantidad);

        boolean result = stmt.execute();
        stmt.close();
        return  result;
    }

    public String selectClima(int dia) throws SQLException{
        Connection con = instancia();

        String clima = new String();
        Statement  stmt = con.createStatement();
        String sql = "SELECT * FROM DATOS WHERE DIA = " + dia + ";";
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            clima = rs.getString("CLIMA");
        }
        rs.close();
        stmt.close();
        return clima;
    }

    public List<Dia> getCantDiaClima(String clima) throws SQLException {
        Connection con = instancia();

        List<Dia> dias = new ArrayList<>();
        Statement  stmt = con.createStatement();
        String sql = "SELECT *  FROM DATOS WHERE CLIMA = '" + clima + "';";
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            Dia dia = new Dia(
                    rs.getInt("DIA"),
                    rs.getString("CLIMA")
            );

            dias.add(dia);
        }
        rs.close();
        stmt.close();return dias;
    }

    public List<Periodo> getPeriodos() throws SQLException {
        Connection con = instancia();

        List<Periodo> periodos = new ArrayList<>();
        Statement  stmt = con.createStatement();
        String sql = "SELECT *  FROM PERIODO;";
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            Periodo periodo = new Periodo(
                    rs.getString("NOMBRE"),
                    rs.getInt("CANTIDAD")
            );

            periodos.add(periodo);
        }
        rs.close();
        stmt.close();

        return periodos;
    }


    public void updateDia(Dia dia) throws SQLException {
        Connection con = instancia();

        String sql = "UPDATE DATOS SET CLIMA = ? WHERE DIA = ?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, dia.getClima());
        stmt.setInt(2, dia.getNumDia());

        stmt.execute();
        stmt.close();
    }

    public boolean updateClima(int dia, String clima, Double planeta1, Double planeta2, Double planeta3) throws SQLException {
        Connection con = instancia();

        String sql = "UPDATE DATOS SET CLIMA = ?, PLANETA1 = ?, PLANETA2 = ?, PLANETA3 = ? WHERE DIA = ?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, clima);
        stmt.setDouble(2, planeta1 );
        stmt.setDouble(3, planeta2);
        stmt.setDouble(4, planeta3);
        stmt.setInt(5, dia);

        boolean result = stmt.execute();
        stmt.close();
        return  result;
    }

    public boolean updatePeriodo(String nombre, int cantidad) throws SQLException {
        Connection con = instancia();

        String sql = "UPDATE PERIODO SET CANTIDAD = ? WHERE NOMBRE = ?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, cantidad);
        stmt.setString(2, nombre);

        boolean result = stmt.execute();
        stmt.close();
        return  result;
    }
}
