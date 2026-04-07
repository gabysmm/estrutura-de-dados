package ArvoreBinaria;

public class NodeABP {
    private int node;
    private NodeABP esq, dir;

    public NodeABP(int node){
        this.node = node;
        this.esq = null;
        this.dir = null;
    }

    public int getNode() {
        return node;
    }
    public void setNode(int node) {
        this.node = node;
    }

    public NodeABP getEsq() {
        return esq;
    }
    public void setEsq(NodeABP esq) {
        this.esq = esq;
    }

    public NodeABP getDir() {
        return dir;
    } 
    public void setDir(NodeABP dir) {
        this.dir = dir;
    }
}