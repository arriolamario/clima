package clima.Service;

import clima.Database.Database;
import clima.Model.Dia;
import clima.Model.Periodo;

import java.util.ArrayList;
import java.util.List;

public class ClimaService {

    public void createTables(){
        try {
            Database database = Database.instance();
            database.createTables();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    public boolean addClima(Dia dia){
        boolean result = false;
        try {
            Database database = Database.instance();
            result = database.insertClima(dia.getNumDia(),dia.getClima(),dia.planetas[0].getAngulo(),dia.planetas[1].getAngulo(),dia.planetas[2].getAngulo());

        }
        catch (Exception ex){
            System.out.println("ClimaService.addClima");
        }

        return result;
    }

    public String getClima(int dia){
        String result = "";
        try {
            Database database = Database.instance();
            result = database.selectClima(dia);
        }
        catch (Exception ex){
            System.out.println("ClimaService.getClima");
        }

        return result;
    }

    public List<Dia> getDias(String clima) {
        List<Dia> response = new ArrayList<>();

        try {
            Database database = Database.instance();
            response = database.getCantDiaClima(clima);
        }
        catch (Exception ex){
            System.out.println("ClimaService.getDias");
        }

        return response;

    }

    public void insertPeriodo(Periodo periodo) {
        try {
            Database database = Database.instance();
            database.insertPeriodo(periodo.getPeriodo(), periodo.getCant());
        }
        catch (Exception ex){
            System.out.println("ClimaService.insertPeriodo");
        }
    }

    public List<Periodo> getAllPeriodo() {
        List<Periodo> periodos = new ArrayList<>();
        try {
            Database database = Database.instance();
             periodos = database.getPeriodos();
        }
        catch (Exception ex){
            System.out.println("ClimaService.insertPeriodo");
        }

        return periodos;
    }

    public void updatePicoLuvia(Dia dia) {
        try {
            Database database = Database.instance();
            database.updateDia(dia);
        }
        catch (Exception ex){
            System.out.println("ClimaService.updatePicoLuvia");
        }
    }
}
