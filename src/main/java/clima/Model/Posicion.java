package clima.Model;

import javax.swing.text.MutableAttributeSet;

public class Posicion {
    private Double x;
    private Double y;

    public Posicion(Double radio, Double angulo) {
        double cos = Math.cos(Math.toRadians(angulo));
        double sen = Math.sin(Math.toRadians(angulo));
        this.x = cos * radio;
        this.y = sen * radio;
    }

    public Posicion() {
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
