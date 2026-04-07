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

    

} 
// abp