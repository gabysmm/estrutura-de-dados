package ArvoreRN;

public class NodeRN {
    private int node;
    private boolean cor;
    private NodeRN esq, dir, pai;

    public NodeRN(int node) {
        this.node = node;
        this.cor = true; //sempre add no da cor vermelha
        this.pai = null;
        this.esq = null;
        this.dir = null;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }

    public boolean getCor() {
        return cor;
    }
    public void setCor(boolean cor) {
        this.cor = cor;
    }

    public NodeRN getPai() {
        return pai;
    }
    public void setPai(NodeRN pai) {
        this.pai = pai;
    }

    public NodeRN getEsq() {
        return esq;
    }

    public void setEsq(NodeRN esq) {
        this.esq = esq;
    }

    public NodeRN getDir() {
        return dir;
    }

    public void setDir(NodeRN dir) {
        this.dir = dir;
    }
}