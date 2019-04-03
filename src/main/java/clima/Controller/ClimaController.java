package clima.Controller;

import clima.Constantes;
import clima.Model.Dia;
import clima.Model.Periodo;
import clima.Model.Rest.ClimaResponse;
import clima.Model.Rest.PeriodoResponse;
import clima.Service.ClimaService;
import clima.Thraad.Init;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

public class ClimaController {
    public static ClimaService climaService = new ClimaService();

    public ClimaResponse getClima(int dia){
        ClimaResponse response = new ClimaResponse(dia);

        String clima = climaService.getClima(dia);

        if (clima.equals("")){
            new Init().tarea();
            clima = climaService.getClima(dia);
        }

        response.setClima(clima);

        return response;
    }

    public PeriodoResponse getDatos(){
        List<Periodo> periodos = climaService.getAllPeriodo();
        List<Dia> dias = climaService.getDias(Constantes.PICODELLUVIA);
        List<Integer> diasPicos = new ArrayList<>();
        for (Dia dia : dias ){
            diasPicos.add(dia.getNumDia());
        }
        PeriodoResponse response = new PeriodoResponse(periodos, diasPicos);


        return  response;

    }

}
