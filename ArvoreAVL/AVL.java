package ArvoreAVL;
import ArvoreBinaria.ABP;

public class AVL extends ABP {
    public AVL() {
        super();
    }
    public void mostrar() {
        mostrarRec((NodeAVL) raiz, 0);
    }
    private void mostrarRec(NodeAVL no, int nivel) {
        if (no == null) return;
        mostrarRec((NodeAVL) no.getDir(), nivel + 1);
        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }
        System.out.println(no.getNode() + " [" + no.getFB() + "]");
        mostrarRec((NodeAVL) no.getEsq(), nivel + 1);
    }

    private NodeAVL rotacaoEsquerda(NodeAVL B) { 
        NodeAVL A = (NodeAVL) B.getDir();
        B.setDir(A.getEsq()); //pega filho de A
        if (A.getEsq() != null) ((NodeAVL) A.getEsq()).setPai(B); //ajuste do ponteiro do ex filho de A

        A.setEsq(B); //rotaciona B pra ser filho de A e ajusta ponteiros pros pai certo
        A.setPai(B.getPai()); 
        B.setPai(A);

        int fbA = A.getFB();
        int fbB = B.getFB();

        int fbB_novo = fbB + 1 - Math.min(fbA, 0);
        int fbA_novo = fbA + 1 + Math.max(fbB_novo, 0);

        A.setFB(fbA_novo);
        B.setFB(fbB_novo);

        return A;
    }

    private NodeAVL rotacaoDireita(NodeAVL B) {
        NodeAVL A = (NodeAVL) B.getEsq();
        B.setEsq(A.getDir());
        if (A.getDir() != null) ((NodeAVL) A.getDir()).setPai(B);

        A.setDir(B);
        A.setPai(B.getPai());
        B.setPai(A);

        int fbA = A.getFB();
        int fbB = B.getFB();

        int fbB_novo = fbB - 1 - Math.max(fbA, 0);
        int fbA_novo = fbA - 1 + Math.min(fbB_novo, 0);

        A.setFB(fbA_novo);
        B.setFB(fbB_novo);

        return A;
    }

    private NodeAVL rotacaoDuplaEsquerda(NodeAVL B) {
        B.setDir(rotacaoDireita((NodeAVL) B.getDir()));
        return rotacaoEsquerda(B);
    }

    private NodeAVL rotacaoDuplaDireita(NodeAVL B) {
        B.setEsq(rotacaoEsquerda((NodeAVL) B.getEsq()));
        return rotacaoDireita(B);
    }

    private NodeAVL balancear(NodeAVL node) {
        if (node.getFB() == -2) {
            NodeAVL dir = (NodeAVL) node.getDir();
            if (dir.getFB() <= 0) 
                return rotacaoEsquerda(node);
            else
                return rotacaoDuplaEsquerda(node); //regrinha la do -2 +1 
        }
        if (node.getFB() == 2) {
            NodeAVL esq = (NodeAVL) node.getEsq();
            if (esq.getFB() >= 0)
                return rotacaoDireita(node);
            else
                return rotacaoDuplaDireita(node); //regra do +2 -1 
        }
        return node;
    }

    @Override
    public void insert(int valor) {
        if (isEmpty()) {
            raiz = new NodeAVL(valor);
            return;
        }

        NodeAVL pai = null;
        NodeAVL p = (NodeAVL) raiz; //ponteiro q vai descer a arvore
        //descendo
        while (p != null) { 
            pai = p;
            if (valor < p.getNode())
                p = (NodeAVL) p.getEsq();
            else
                p = (NodeAVL) p.getDir();
        }

        NodeAVL novo = new NodeAVL(valor);
        novo.setPai(pai);

        if (valor < pai.getNode()) 
            pai.setEsq(novo);
        else
            pai.setDir(novo);

        //atualização de fbs
        NodeAVL atual = pai;
        NodeAVL filho = novo;
        //subindo
        while (atual != null) {
            if (filho == atual.getEsq())
                atual.setFB(atual.getFB() + 1);
            else
                atual.setFB(atual.getFB() - 1);
            if (atual.getFB() == 0) //antecessor for 0 a arvore ja abalanceou
                break;
            if (atual.getFB() == 2 || atual.getFB() == -2) { //se antecessor tiver fb desbalanceado manda p corrigir
                NodeAVL paiDoAtual = atual.getPai();
                NodeAVL novaRaiz = balancear(atual);

                if (paiDoAtual == null) { //atual parou na raiz ai vira raiz mesmo
                    raiz = novaRaiz;
                    novaRaiz.setPai(null);
                } else { //atual era subarvore
                    if (paiDoAtual.getEsq() == atual)
                        paiDoAtual.setEsq(novaRaiz);
                    else
                        paiDoAtual.setDir(novaRaiz);

                    novaRaiz.setPai(paiDoAtual);
                }
                break;
            }
            filho = atual;
            atual = atual.getPai();
        }
    }

    @Override
    public void remove(int valor) {
        NodeAVL p = (NodeAVL) raiz;
        while (p != null && p.getNode() != valor) {
            if (valor < p.getNode())
                p = (NodeAVL) p.getEsq();
            else
                p = (NodeAVL) p.getDir();
        }

        if (p == null) return;
        NodeAVL pai;
        //Caso: dois filhos
        if (p.getEsq() != null && p.getDir() != null) {
            NodeAVL suc = (NodeAVL) p.getDir();

            while (suc.getEsq() != null)
                suc = (NodeAVL) suc.getEsq();

            p.setNode(suc.getNode());
            p = suc; 
        }

        // Caso: 0 ou 1 filho
        NodeAVL filho = (NodeAVL) (p.getEsq() != null ? p.getEsq() : p.getDir());
        pai = (NodeAVL) p.getPai();
        if (filho != null)
            filho.setPai(pai);
        if (pai == null) {
            raiz = filho;
        } else if (pai.getEsq() == p) {
            pai.setEsq(filho);
        } else {
            pai.setDir(filho);
        }

        NodeAVL atual = pai;
        NodeAVL anterior = p;
        while (atual != null) {
            if (anterior == atual.getEsq())
                atual.setFB(atual.getFB() - 1);
            else
                atual.setFB(atual.getFB() + 1);

            if (atual.getFB() == 2 || atual.getFB() == -2) {
                NodeAVL paiDoAtual = (NodeAVL) atual.getPai();
                NodeAVL novaRaiz = balancear(atual);
                if (paiDoAtual == null) {
                    raiz = novaRaiz;
                    novaRaiz.setPai(null);
                } else {
                    if (paiDoAtual.getEsq() == atual)
                        paiDoAtual.setEsq(novaRaiz);
                    else
                        paiDoAtual.setDir(novaRaiz);

                    novaRaiz.setPai(paiDoAtual);
                }
                atual = novaRaiz;
            }
            if (atual.getFB() != 0)
                break;

            anterior = atual;
            atual = (NodeAVL) atual.getPai();
        }
    }
}
