package clima.Database;

import clima.Model.Dia;
import clima.Model.Periodo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Connection con;

    private static final String TBL_CLIMA = "CREATE TABLE DATOS ("+
            "DIA INT,"+
            "CLIMA VARCHAR(100),"+
            "PLANETA1 DOUBLE,"+
            "PLANETA2 DOUBLE,"+
            "PLANETA3 DOUBLE,"+
            "PRIMARY KEY (DIA),"+
            ");";

    private  static  final String TBL_PERIODO = "CREATE TABLE PERIODO ("+
            "NOMBRE VARCHAR(100),"+
            "CANTIDAD INT,"+
            ");";
    private static Database database;

    public Database() throws SQLException {
        {
            String url = "jdbc:h2:mem:";
            con = DriverManager.getConnection(url);
        }
    }

    public static Database instance(){
        if (database == null){
            try {
                database = new Database();

            }
            catch (Exception ex){
                System.out.println(ex);
            }
        }

        return  database;
    }

    public void createTables() throws SQLException{
        Statement stmt = con.createStatement();
        stmt.executeUpdate(TBL_CLIMA);
        stmt.executeUpdate(TBL_PERIODO);
    }

    public boolean insertClima(int dia, String clima, double planeta1, double planeta2, double planeta3) throws SQLException{
        String sql = "INSERT INTO DATOS (DIA, CLIMA, PLANETA1, PLANETA2, PLANETA3) VALUES (?,?,?,?,?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setInt(1, dia);
        stmt.setString(2, clima);
        stmt.setDouble(3, planeta1 );
        stmt.setDouble(4, planeta2);
        stmt.setDouble(5, planeta3);

        boolean result = stmt.execute();
        return  result;
    }

    public boolean insertPeriodo(String nombre, int cantidad) throws SQLException{
        String sql = "INSERT INTO PERIODO (NOMBRE, CANTIDAD) VALUES (?,?);";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, nombre);
        stmt.setInt(2, cantidad);

        boolean result = stmt.execute();

        return  result;
    }

    public String selectClima(int dia) throws SQLException{
        String clima = new String();
        Statement  stmt = con.createStatement();
        String sql = "SELECT * FROM DATOS WHERE DIA = " + dia + ";";
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            clima = rs.getString("CLIMA");
        }
        rs.close();

        return clima;
    }

    public List<Dia> getCantDiaClima(String clima) throws SQLException {
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

        return dias;
    }

    public List<Periodo> getPeriodos() throws SQLException {
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

        return periodos;
    }


    public void updateDia(Dia dia) throws SQLException {
        String sql = "UPDATE DATOS SET CLIMA = ? WHERE DIA = ?;";
        PreparedStatement stmt = con.prepareStatement(sql);
        stmt.setString(1, dia.getClima());
        stmt.setInt(2, dia.getNumDia());

        stmt.execute();
    }
}
