package clima.Model;

public class Posicion {
    private Double x;
    private Double y;

    public Posicion(Double radio, Double angulo) {
        this.x = radio * Math.cos(angulo);
        this.y = radio * Math.sin(angulo);
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }
}
