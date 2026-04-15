package ArvoreAVL;
import ArvoreBinaria.NodeABP;

public class NodeAVL extends NodeABP {
    private int fb;
    private NodeAVL Pai;

    public NodeAVL(int node) {
        super(node); //chama constructor 
        this.fb = 0; 
        this.Pai = null;
    }

    public int getFB() {
        return fb;
    }
    public void setFB(int fb) {
        this.fb = fb;
    }

    public NodeAVL getPai() {
        return Pai;
    }
    public void setPai(NodeAVL pai) {
        this.Pai = pai;
    }
}