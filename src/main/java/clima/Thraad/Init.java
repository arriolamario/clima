package clima.Thraad;

import clima.Constantes;
import clima.Model.Dia;
import clima.Model.Periodo;
import clima.Model.Planeta;
import clima.Service.ClimaService;
import org.apache.log4j.pattern.LineSeparatorPatternConverter;

import java.util.ArrayList;
import java.util.List;

public class Init extends Thread {
    private static final int DAYYEAR = 365;
    private static final int CANTYEAR = 10;
    Periodo[] periodos;
    public Init() {
        periodos = new Periodo[4];
        periodos[0] = new Periodo(Constantes.LLUEVE);
        periodos[1] = new Periodo(Constantes.NOLLUEVE);
        periodos[2] = new Periodo(Constantes.OPTIMO);
        periodos[3] = new Periodo(Constantes.SEQUIA);
    }

    @Override
    public void run() {
        ClimaService climaService = new ClimaService();
        climaService.createTables();
        int days = DAYYEAR * CANTYEAR;
        double perimetroMaximo = 0.0;

        String periodoActual = "";
        List<Dia> dias = new ArrayList<>();
        for (int i = 0; i < days; i++) {

            Planeta[] planestas = new Planeta[3];
            planestas[0] = new Planeta("Ferengi",1,500,true);
            planestas[1] = new Planeta("Betasoide",3,2000,true);
            planestas[2] = new Planeta("Vulcano",5,1000,false);

            Dia dia = new Dia(planestas, i);

            if (periodoActual == "" || periodoActual != dia.getClima()){
                periodoActual = dia.getClima();
                for (Periodo periodo : periodos){
                    if (periodoActual == periodo.getPeriodo()){
                        periodo.AddPeriodo();
                    }
                }
            }

            if(dia.getClima() == Constantes.LLUEVE){
                double perimetro = dia.perimetroTriangulo();
                if (perimetro > perimetroMaximo) {
                    perimetroMaximo = perimetro;
                }
            }

            climaService.addClima(dia);
            dias.add(dia);
        }

        for (Dia dia : dias){
            if (dia.perimetroTriangulo() == perimetroMaximo){
                dia.setClima(Constantes.PICODELLUVIA);
                climaService.updatePicoLuvia(dia);
            }
        }

        for (Periodo periodo : periodos){
            climaService.insertPeriodo(periodo);
        }

        System.out.println("Termino la carga de datos!!!");
    }
    private void CreacionTablas(){

    }
}
