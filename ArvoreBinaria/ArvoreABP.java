package ArvoreBinaria;

public class ABP {
    NodeABP raiz;

    public ABP() {
        this.raiz = null;
    }

    public boolean isEmpty() {
        return raiz == null;
    }

    public void inserir(int valor) {
        NodeABP novo = new NodeABP(valor);
        if (isEmpty()) {
            raiz = novo;
        } else {
            NodeABP p = raiz;
            while (p != null) {
                if(valor < p.node) {
                    if (p.esq == null) {
                        p.esq = novo;
                        break;
                    } else {
                        p = p.esq;
                    }
                } else {
                    if (p.dir == null) {
                        p.dir = novo;
                        break;
                    } else {
                        p = p.dir;
                    }
                }
            }
        }
    } 
    // fim inserir

} 
// abp