package clima.Model;

public class Planeta {
    private String nombre;
    private double velocidad;
    private double distancia;
    private Boolean horario;
    private Double angulo;
    private Posicion posicion;

    public Planeta(String nombre, int velocidad, int distancia, Boolean horario) {
        this.nombre = nombre;
        this.velocidad = velocidad;
        this.distancia = distancia;
        this.horario = horario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public Boolean getHorario() {
        return horario;
    }

    public void setHorario(Boolean horario) {
        this.horario = horario;
    }

    public Double getAngulo() {
        return angulo;
    }

    public void setAngulo(Double angulo) {
        this.angulo = angulo;
    }

    public Posicion getPosicion() {
        return posicion;
    }

    public void setPosicion(Posicion posicion) {
        this.posicion = posicion;
    }

    public double calcularAngulo(int dia){
        int diaAux = dia % 365;
        double grado = (diaAux * this.velocidad) % 360;

        if (!this.horario)
        {
            grado = (360 - grado) % 360;
        }

        return grado;
    }
    
}
