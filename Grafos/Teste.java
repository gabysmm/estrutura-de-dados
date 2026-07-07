package Grafos;

public class Teste {
    public static void main(String[] args) {
        //grafo com 5 vertices: v1 a v5 representados pelos indices 0 a 4
        Dijkstra g = new Dijkstra(5);

        //arestas nao direcionadas, por isso add os dois sentidos
        adicionar(g, 0, 1, 10);  // v1-v2
        adicionar(g, 0, 3, 30);  // v1-v4
        adicionar(g, 0, 4, 100); // v1-v5
        adicionar(g, 1, 2, 50);  // v2-v3
        adicionar(g, 2, 3, 20);  // v3-v4
        adicionar(g, 3, 4, 60);  // v4-v5
        adicionar(g, 2, 4, 10);  // v3-v5

        System.out.println("Dijkstra a partir de v1");
        g.mostrar(0);
    }

    private static void adicionar(Dijkstra g, int origem, int destino, int peso) {
        g.adicionarAresta(origem, destino, peso);
        g.adicionarAresta(destino, origem, peso);
    }
}
