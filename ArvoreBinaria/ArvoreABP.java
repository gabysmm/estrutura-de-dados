package ArvoreBinaria;

public class ABP {
    NodeABP raiz;

    public ABP() {
        this.raiz = null;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public void insert(int valor) {
        NodeABP novo = new NodeABP(valor);
        if (isEmpty()) {
            raiz = novo;
        } else {
            NodeABP p = raiz;
            while (p != null) {
                if(valor < p.getNode()) {
                    if (p.getEsq() == null) {
                        p.setEsq(novo);
                        break;
                    } else {
                        p = p.getEsq();
                    }
                } else {
                    if (p.getDir() == null) {
                        p.setDir(novo);
                        break;
                    } else {
                        p = p.getDir();
                    }
                }
            }
        }
    } 
    // fim inserir

    public NodeABP search(int valor) {
        NodeABP p = raiz;
        while (p != null) {
            if (valor == p.getNode()) {
                return p;
            } else if (valor < p.getNode()) {
                p = p.getEsq();
            } else {
                p = p.getDir();
            }
        }
        return null;
    }
    //fim buscar

    public void remove(int valor) {
        if (isEmpty()) {
            System.out.println("Árvore vazia!");
            return;
        }
        NodeABP pai = null;
        NodeABP p = raiz;

        //buscar o valor do no e do seu pai
        while(p != null && p.getNode() != valor) {
            pai = p;
            if (valor < p.getNode()) {
                p = p.getEsq();
            } else {
                p = p.getDir();
            }
        }

        //verificar aq filhos do no
        boolean temFilhoEsq = (p.getEsq() != null);
        boolean temFilhoDir= (p.getDir() != null);

        // CASO 1 = quando é um no folha
        if (!temFilhoEsq && !temFilhoDir) {
            if (pai == null) {
                raiz = null; //seria raiz, ent tiraria raiz
            } else if (p == pai.getEsq()) {
                pai.setEsq(null);
            } else {
                pai.setDir(null);
            }
        } else if (temFilhoEsq || temFilhoDir) { // CASO 2 = nó com 1 filho
            NodeABP filhoUnico = (temFilhoEsq) ? p.getEsq() : p.getDir();
            if (pai == null) {
                raiz = filhoUnico;
            } else if (p == pai.getEsq()) {
                pai.setEsq(filhoUnico);
            } else {
                pai.setDir(filhoUnico);
            }
        } else { //CASO 3 - com 2 filhos
            NodeABP paiSucessor = p;
            NodeABP sucessor = p.getDir();

            while (sucessor.getEsq() != null) {
                paiSucessor = sucessor;
                sucessor = sucessor.getEsq();
            }

            p.setNode(sucessor.getNode());

            if (paiSucessor.getEsq() == sucessor) {
                paiSucessor.setEsq(sucessor.getDir());
            } else {
                paiSucessor.setDir(sucessor.getDir());
            }
            System.out.println("removido node " + valor);
        }
    }
} 
// abp