package ArvoreAVL;
import ArvoreBinaria.NodeABP

public class NodeAVL extends NodeABP {
    private int fb;

    public NodeAVL(int node) {
        super(node); //chama constructor 
        this.fb = 0; 
    }

    public int getFB() {
        return fb;
    }
    public void setFB(int fb) {
        this.fb = fb;
    }
}