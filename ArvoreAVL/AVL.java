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
        int fbA_novo = fbA + 1 + Math.max(fbB_novo, 0);

        B.setFB(fbB_novo);
        A.setFB(fbA_novo);

        return B;
    }

    private NodeAVL rotacaoDireita(NodeAVL A) {
        NodeAVL B  = (NodeAVL)  A.getEsq();

        A.setEsq(B.getDir());
        B.setDir(A);

        int fbA = A.getFB();
        int fbB = B.getFB();

        int fbB_novo = fbB - 1 - Math.max(fbA, 0);
        int fbA_novo = fbA - 1 + Math.min(fbB_novo, 0);

        B.setFB(fbB_novo);
        A.setFB(fbA_novo);

        return B;
    }

    private NodeAVL rotacaoDuplaEsquerda(NodeAVL A) {
        A.setDir(rotacaoDireita((NodeAVL) A.getDir()));
        return rotacaoEsquerda(A);
    }

    private NodeAVL rotacaoDuplaDireita(NodeAVL A) {
        A.setEsq(rotacaoEsquerda((NodeAVL) A.getEsq()));
        return rotacaoDireita(A);
    }

    private NodeAVL balancear(NodeAVL node) {
        if (node.getFB() == -2) {
            NodeAVL filhoDir = (NodeAVL) node.getDir();
            if (filhoDir.getFB() <= 0){
                return rotacaoEsquerda(node);
            } else {
                return rotacaoDuplaEsquerda(node);
            }
        } else if (node.getFB() == 2) {
            NodeAVL filhoEsq = (NodeAVL) node.getEsq();
            if (filhoEsq.getFB() >= 0) {
                return rotacaoDireita(node);
            } else {
                return rotacaoDuplaDireita(node);
            }
        }
        return node;
    }

    public void insert(int valor) {
        if (isEmpty()) {
            raiz = new NodeAVL(valor);
            return;
        }
        // desce guardando pai
        NodeAVL pai = null;
        NodeAVL p = (NodeAVL) raiz;
        while (p != null) {
            pai = p;
            if (valor < p.getNode()) p = (NodeAVL) p.getEsq();
            else p = (NodeAVL) p.getDir();
        }
        // insert
        NodeAVL novo = new NodeAVL(valor);
        novo.setPai(pai);
        if (valor < pai.getNode()) pai.setEsq(novo);
        else pai.setDir(novo);

        // sobe atualizando FB
        NodeAVL atual = pai;
        while (atual != null) {
            if (valor < atual.getNode()) atual.setFB(atual.getFB() + 1);
            else atual.setFB(atual.getFB() - 1);

            if (atual.getFB() == 0) break; 
            if (atual.getFB() == 2 || atual.getFB() == -2) {
                NodeAVL novaRaiz = balancear(atual);
                if (atual.getPai() == null) {
                    raiz = novaRaiz; 
                } else {
                    if (atual.getPai().getEsq() == atual) {
                        atual.getPai().setEsq(novaRaiz);
                    } else {
                        atual.getPai().setDir(novaRaiz);
                    }
                }
                novaRaiz.setPai(atual.getPai());
                break;
            }
            atual = (NodeAVL) atual.getPai();
        }
    }
}