package ArvoreBinaria;

public class NodeABP {
    int node;
    NodeABP esq, dir;

    public NodeABP(int node){
        this.node = node;
        this.esq = null;
        this.dir = null;
    }
}