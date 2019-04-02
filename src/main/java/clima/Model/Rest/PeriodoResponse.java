package clima.Model.Rest;

import clima.Model.Periodo;

import java.util.List;

public class PeriodoResponse {
    private List<Periodo> periodos;
    private List<Integer> picosLuvia;

    public PeriodoResponse() {
    }

    public PeriodoResponse(List<Periodo> periodos, List<Integer> picosLuvia) {
        this.periodos = periodos;
        this.picosLuvia = picosLuvia;
    }

    public List<Periodo> getPeriodos() {
        return periodos;
    }

    public void setPeriodos(List<Periodo> periodos) {
        this.periodos = periodos;
    }

    public List<Integer> getPicosLuvia() {
        return picosLuvia;
    }

    public void setPicosLuvia(List<Integer> picosLuvia) {
        this.picosLuvia = picosLuvia;
    }
}
