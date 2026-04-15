package ArvoreAVL;
import ArvoreBinaria.NodeABP

public class NodeAVL extends NodeABP {
    private int fb;
    private NodeAVL pai;

    public NodeAVL(int node) {
        super(node); //chama constructor 
        this.fb = 0; 
        this.pai = null;
    }

    public int getFB() {
        return fb;
    }
    public void setFB(int fb) {
        this.fb = fb;
    }

    public NodeAVL getpai() {
        return pai;
    }
    public void setPai(NodeAVL pai) {
        this.pai = pai;
    }
}