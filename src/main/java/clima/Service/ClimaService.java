package clima.Service;

import clima.Database.Database;
import clima.Model.Dia;
import clima.Model.Periodo;

import java.util.ArrayList;
import java.util.List;

public class ClimaService {

    Database database;

    public ClimaService() {
        database = new Database();
    }

    public void createTables(){
        try {
            database.createTables();
        }
        catch (Exception ex){
            System.out.println(ex);
        }
    }

    public boolean addClima(Dia dia){
        boolean result = false;
        try {
            String clima = database.selectClima(dia.getNumDia());
            if (clima.equals("") ){
                result = database.insertClima(dia.getNumDia(),dia.getClima(),dia.planetas[0].getAngulo(),dia.planetas[1].getAngulo(),dia.planetas[2].getAngulo());
            }
            else{
                result = database.updateClima(dia.getNumDia(),dia.getClima(),dia.planetas[0].getAngulo(),dia.planetas[1].getAngulo(),dia.planetas[2].getAngulo());
            }
        }
        catch (Exception ex){
            System.out.println("ClimaService.addClima");
        }

        return result;
    }

    public String getClima(int dia){
        String result = "";
        try {
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
            response = database.getCantDiaClima(clima);
        }
        catch (Exception ex){
            System.out.println("ClimaService.getDias");
        }

        return response;

    }

    public void insertPeriodo(Periodo periodo) {
        try {
            List<Periodo> periodos = database.getPeriodos();

            for (Periodo p : periodos){
                if (p.getPeriodo().equals(periodo.getPeriodo())){
                    database.updatePeriodo(periodo.getPeriodo(), periodo.getCant());
                    return;
                }
            }

            database.insertPeriodo(periodo.getPeriodo(), periodo.getCant());

        }
        catch (Exception ex){
            System.out.println("ClimaService.insertPeriodo");
        }
    }

    public List<Periodo> getAllPeriodo() {
        List<Periodo> periodos = new ArrayList<>();
        try {
             periodos = database.getPeriodos();
        }
        catch (Exception ex){
            System.out.println("ClimaService.insertPeriodo");
        }

        return periodos;
    }

    public void updatePicoLuvia(Dia dia) {
        try {
            database.updateDia(dia);
        }
        catch (Exception ex){
            System.out.println("ClimaService.updatePicoLuvia");
        }
    }
}
