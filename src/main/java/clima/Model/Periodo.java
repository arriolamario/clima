package clima.Model;

public class Periodo {
    private String periodo;
    private  int cant;

    public Periodo(String periodo, int cant) {
        this.periodo = periodo;
        this.cant = cant;
    }

    public Periodo(String periodo) {
        this.periodo = periodo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public void AddPeriodo(){
        cant = cant + 1;
    }
}
