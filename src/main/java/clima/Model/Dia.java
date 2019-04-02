package clima.Model;

import clima.Constantes;

public class Dia {
    public Planeta[] planetas;
    private int numDia;
    private String clima;
    private Posicion sol;

    public Dia(Planeta[] planetas, int numDia) {
        this.planetas = planetas;
        this.numDia = numDia;
        sol = new Posicion(0.0,0.0);
        calcularPosicion();
        calcularClima();
    }

    public Dia(int numDia, String clima) {
        this.numDia = numDia;
        this.clima = clima;
    }

    public int getNumDia() {
        return numDia;
    }

    public void setNumDia(int numDia) {
        this.numDia = numDia;
    }

    public String getClima() {
        return clima;
    }

    public void setClima(String clima) {
        this.clima = clima;
    }

    public Posicion getSol() {
        return sol;
    }

    public void setSol(Posicion sol) {
        this.sol = sol;
    }
    
    private void calcularPosicion(){
        for (Planeta itemPlaneta : planetas) {
            Double angulo = itemPlaneta.calcularAngulo(this.numDia);
            itemPlaneta.setAngulo(angulo);
            Posicion posicion = new Posicion(itemPlaneta.getDistancia(), itemPlaneta.getAngulo());
            itemPlaneta.setPosicion(posicion);
        }
    }

    public Boolean calcularAlineacion(Posicion p1, Posicion p2, Posicion p3)
    {
        //y = m x + b
        double m = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
        double b = p1.getY() - (m * p1.getX());

        return p3.getY() == m * p3.getX() + b;
    }

    //(A1.x - A3.x) * (A2.y - A3.y) - (A1.y - A3.y) * (A2.x - A3.x)
    public Orientacion calcularOrientacion(Posicion A1, Posicion A2, Posicion A3)
    {
        double orientacion = ((A1.getX() - A3.getX()) * (A2.getY() - A3.getY())) - ((A1.getY() - A3.getY()) * (A2.getX() - A3.getX()));

        if (orientacion >= 0)
        {
            return Orientacion.POSITIVA;
        }
        else
        {
            return Orientacion.NEGATIVA;
        }
    }

    public double perimetroTriangulo(){
        Posicion p1 = planetas[0].getPosicion();
        Posicion p2 = planetas[1].getPosicion();
        Posicion p3 = planetas[2].getPosicion();

        double disLado1 = distanciaEntePuntos(p1, p2);
        double disLado2 = distanciaEntePuntos(p1, p3);
        double disLado3 = distanciaEntePuntos(p2, p3);

        return disLado1 + disLado2 + disLado3;
    }

    //p1(x1, y1) p2(x2, y2)
    private  double distanciaEntePuntos(Posicion p1, Posicion p2){
        double auxX = Math.pow((p2.getX() - p1.getX()), 2);
        double auxY = Math.pow((p2.getY() - p1.getY()), 2);

        return Math.sqrt(auxX + auxY);
    }
    
    private void calcularClima()
    {
        //clima óptimas de presión y temperatura
        if (calcularAlineacion(planetas[0].getPosicion(), planetas[1].getPosicion(), planetas[2].getPosicion()))
        {//Alineado los 3 planetas
            if (calcularAlineacion(planetas[0].getPosicion(), planetas[1].getPosicion(), sol))
            {//Alineado los 3 planeta y el sol
                clima = Constantes.SEQUIA;
            }
            else
            {//Alineado los 3 planetas
                clima = Constantes.OPTIMO;
            }
        }
        else
        {
            Orientacion orientacion = calcularOrientacion(planetas[0].getPosicion(), planetas[1].getPosicion(), planetas[2].getPosicion());
            Orientacion orientacionP1 = calcularOrientacion(planetas[0].getPosicion(), planetas[1].getPosicion(), sol);
            Orientacion orientacionP2 = calcularOrientacion(sol, planetas[1].getPosicion(), planetas[2].getPosicion());
            Orientacion orientacionP3 = calcularOrientacion(planetas[0].getPosicion(), sol, planetas[2].getPosicion());

            if (orientacion == Orientacion.POSITIVA)
            {
                if (orientacionP1 == Orientacion.POSITIVA && orientacionP2 == Orientacion.POSITIVA && orientacionP3 == Orientacion.POSITIVA)
                {//El sol esta adentro
                    clima = Constantes.LLUEVE;
                }
                else
                {//El sol esta afuera
                    clima = Constantes.NOLLUEVE;
                }
            }
            else
            {
                if (orientacionP1 == Orientacion.NEGATIVA && orientacionP2 == Orientacion.NEGATIVA && orientacionP3 == Orientacion.NEGATIVA)
                {//El sol esta adentro
                    clima = Constantes.LLUEVE;
                }
                else
                {//El sol esta afuera
                    clima = Constantes.NOLLUEVE;
                }
            }
        }
    }
}





