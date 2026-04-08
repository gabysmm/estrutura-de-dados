package ArvoreAVL;
import ArvoreBinaria.ABP;
import ArvoreBinaria.NodeABP;

public class AVL extends ABP {
    public AVL() {
        super(); //constructor da abp
    }

    private NodeAVL rotacaoEsquerda(NodeAVL A) {
        NodeAVL B = (NodeAVL) A.getDir();

        A.setDir(B.getEsq());
        B.setEsq(A);

        //clculo fb
        int fbA = A.getFB();
        int fbB = B.getFB();

        int fbB_novo = fbB + 1 - Math.min(fbA, 0);
        int fbA_novo = fbA + 1 + Math.max(fbB_noxo, 0);

        B.setFB(fbB_novo);
        A.setFB(fbA_novo);

        return B;
    }
}