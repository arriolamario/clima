import clima.Controller.ClimaController;
import clima.Model.Dia;
import clima.Model.Periodo;
import clima.Model.Posicion;
import clima.Model.Rest.ClimaResponse;
import clima.Model.Rest.PeriodoResponse;
import clima.Thraad.Init;
import com.google.gson.Gson;
import org.apache.log4j.BasicConfigurator;
import spark.servlet.SparkApplication;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import java.util.List;

import static spark.Spark.get;

public class App implements SparkApplication {
    public static ClimaController climaController = new ClimaController();

    public static void main( String[] args )
    {
        new App().init();
    }

    @Override
    public void init() {
        BasicConfigurator.configure();
        Init init = new Init();
        init.start();

        get("/clima", (request, response) -> {
            int dia = Integer.parseInt(request.queryParams("dia"));

            ClimaResponse climaResposne = climaController.getClima(dia);

            Gson gson = new Gson();

            return gson.toJson(climaResposne);
        });

        get("/clima/datos", (request, response) -> {

            PeriodoResponse res = climaController.getDatos();

            Gson gson = new Gson();

            return gson.toJson(res);
        });
    }

    @WebFilter(
            filterName = "SparkInitFilter", urlPatterns = {"/*"},
            initParams = {
                    @WebInitParam(name = "applicationClass", value = "App")
            })
    public static class SparkInitFilter extends spark.servlet.SparkFilter {
    }
}
