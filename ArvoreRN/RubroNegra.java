package ArvoreRN;

public class RubroNegra {
    private NodeRN raiz;

    public RubroNegra() {
        this.raiz = null;
    }

    public void mostrar() {
        mostrarRec(raiz, 0);
    }

    private void mostrarRec(NodeRN no, int nivel) {
        if (no == null) return;
        mostrarRec(no.getDir(), nivel + 1);
        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }
        String cor = no.getCor() ? "V" : "P";
        System.out.println(no.getNode() + "(" + cor + ")");
        mostrarRec(no.getEsq(), nivel + 1);
    }

    public NodeRN buscarNode(int valor) {
        NodeRN atual = raiz;
        while (atual != null) {
            if (valor < atual.getNode())
                atual = atual.getEsq();
            else if (valor > atual.getNode())
                atual = atual.getDir();
            else 
                return atual; //achou
        }
        return null;
    } 

    public void insert(int valor) {
        NodeRN novo = new NodeRN(valor); //sempre inicia vermelho

        if(raiz == null) {
            raiz = novo;
            raiz.setCor(false); //pinta de preto pra n desobedecer regra
            System.out.println("Primeiro nó é uma raiz, então foi inserido o valor " + valor + "na cor preto");
            return;
        }

        NodeRN atual = raiz;
        NodeRN pai = null;
        while(atual != null) { // faz uma busca do pai
            pai = atual;
            if (valor < atual.getNode()) 
                atual = atual.getEsq();
            else if (valor > atual.getNode()) 
                atual = atual.getDir();
            else 
                return;
        }
        novo.setPai(pai); //conecta o novo no ao seu pai e escolhe em q lado fica
        if (valor < pai.getNode()) {
            pai.setEsq(novo);
        }
        else {
            pai.setDir(novo);
        }

        balancearInsert(novo);
    }

    private void rotacaoEsq(NodeRN x) {
        NodeRN y = x.getDir();           // y é o filho direito de x
        x.setDir(y.getEsq());            // filho esquerdo de y vira direito de x
        if (y.getEsq() != null) {
            y.getEsq().setPai(x);        
        }
        y.setPai(x.getPai());            // quebra e y sobe pr o lugar de x
        if (x.getPai() == null) {
            raiz = y;                    
        } else if (x == x.getPai().getEsq()) {
            x.getPai().setEsq(y);
        } else {
            x.getPai().setDir(y);
        }
        y.setEsq(x);                     // x vira filho esq d y
        x.setPai(y);
    }

    private void rotacaoDir(NodeRN y) {
        NodeRN x = y.getEsq();           // x é o filho esq de y
        y.setEsq(x.getDir());            // filho dir de x vira esquerdo de y
        if (x.getDir() != null) {
            x.getDir().setPai(y);
        }
        x.setPai(y.getPai());            // x fica no lugar d y
        if (y.getPai() == null) {
            raiz = x;
        } else if (y == y.getPai().getEsq()) {
            y.getPai().setEsq(x);
        } else {
            y.getPai().setDir(x);
        }
        x.setDir(y);                     // y vira filho direito de x
        y.setPai(x);
    }

    public void balancearInsert(NodeRN no) {
        while (no != raiz && no.getPai() != null && no.getPai().getCor() == true) {
            NodeRN pai = no.getPai();
            NodeRN avo = pai.getPai();
            NodeRN tio;
            if (pai == avo.getEsq()) {
                tio = avo.getDir();
            } else {
                tio = avo.getEsq();
            }

            //caso 1 onde pai e tio é vermelho
            if (tio != null && tio.getCor() == true) {
                pai.setCor(false);
                tio.setCor(false);
                avo.setCor(true);
                no = avo; //sobe o no
            } else {
                //caso 2 onde pai é vermelho e tem um tio folha preto
                if (pai == avo.getEsq()) {
                    if (no == pai.getDir()) { //caso esq-dir 
                        rotacaoEsq(pai); //rotacao pai
                        no = pai;
                        pai = no.getPai();
                    }
                    //rotacao direita avo
                    rotacaoDir(avo);
                    pai.setCor(false);
                    avo.setCor(true);
                } else {
                    if (no == pai.getEsq()) {
                        rotacaoDir(pai);
                        no = pai;
                        pai = no.getPai();
                    }
                    rotacaoEsq(avo);
                    pai.setCor(false);
                    avo.setCor(true);
                }
                break;
            }
        }
        raiz.setCor(false); //raiz sempre preta
    }
}
