package Grafos;

public class Dijkstra {
    public static final int INFINITO = Integer.MAX_VALUE; //representa "sem caminho" entre dois vertices

    private int[][] matriz; //matriz de adjacência: matriz[i][j] = peso da aresta de i pra j
    private int nVertices;

    public Dijkstra(int nVertices) {
        this.nVertices = nVertices;
        this.matriz = new int[nVertices][nVertices];
        for (int i = 0; i < nVertices; i++) {
            for (int j = 0; j < nVertices; j++) {
                matriz[i][j] = (i == j) ? 0 : INFINITO; //distancia de um vertice pra ele mesmo é 0, o resto comeca infinito
            }
        }
    }

    public int getNVertices() {
        return nVertices;
    }

    public void adicionarAresta(int origem, int destino, int peso) {
        matriz[origem][destino] = peso;
    }

    //calcula a menor distancia da origem até cada vertice do grafo
    public Vertice[] calcular(int origem) {
        Vertice[] D = new Vertice[nVertices];
        boolean[] visitado = new boolean[nVertices]; //quem já teve a menor distancia definida

        for (int v = 0; v < nVertices; v++) {
            D[v] = new Vertice(matriz[origem][v]); //comeca com a distancia direta até a origem
        }
        D[origem].setDistancia(0);
        visitado[origem] = true;

        int totalVisitados = 1;
        while (totalVisitados < nVertices) { //repete até definir a distancia de todo mundo
            //entre os ainda não visitados, acha o mais proximo da origem
            int w = -1;
            int menor = INFINITO;
            for (int v = 0; v < nVertices; v++) {
                if (!visitado[v] && D[v].getDistancia() < menor) {
                    menor = D[v].getDistancia();
                    w = v;
                }
            }

            if (w == -1) break; //os que restam não tem caminho até a origem

            visitado[w] = true;
            totalVisitados++;

            //tenta melhorar a distancia dos vizinhos de w passando por ele
            for (int v = 0; v < nVertices; v++) {
                if (!visitado[v] && matriz[w][v] != INFINITO && D[w].getDistancia() + matriz[w][v] < D[v].getDistancia()) {
                    D[v].setDistancia(D[w].getDistancia() + matriz[w][v]);
                    D[v].setAntecessor(w); //guarda por onde passou pra chegar em v
                }
            }
        }
        return D;
    }

    public void mostrar(int origem) {
        Vertice[] D = calcular(origem);
        for (int v = 0; v < nVertices; v++) {
            System.out.print("v" + (v + 1) + ": d=" + D[v].getDistancia());
            if (D[v].getAntecessor() != -1) {
                System.out.println(", antecessor=v" + (D[v].getAntecessor() + 1));
            } else {
                System.out.println();
            }
        }
    }
}
