package Grafos;

public class Vertice {
    private int distancia; //menor distancia conhecida até a origem
    private int antecessor; //vertice anterior no caminho até aqui

    public Vertice(int distancia) {
        this.distancia = distancia;
        this.antecessor = -1; //ainda sem antecessor
    }

    public int getDistancia() {
        return distancia;
    }
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    public int getAntecessor() {
        return antecessor;
    }
    public void setAntecessor(int antecessor) {
        this.antecessor = antecessor;
    }
}
