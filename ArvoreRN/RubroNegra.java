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

    public void remove(int valor) {
        // encontrar o no q vai ser removido e o pai dele
        NodeRN p = raiz;
        NodeRN pai = null;
        while (p != null && p.getNode() != valor) {
            pai = p;
            if (valor < p.getNode())
                p = p.getEsq();
            else
                p = p.getDir();
        }
        if (p == null) {
            return; //n encontrou 
        } 

        NodeRN filho = null;
        NodeRN paiFilho = null;
        boolean corOriginal = p.getCor();

        // Caso 2 filhos
        if (p.getEsq() != null && p.getDir() != null) {
            NodeRN suc = p.getDir();
            while (suc.getEsq() != null)
                suc = suc.getEsq();

            p.setNode(suc.getNode());      // copia o valor do sucessor
            p = suc;                       // p aponta pro no q vai ser removido
            corOriginal = p.getCor();      // atualiza a cor original
            pai = p.getPai();              // atualiza o pai
        }

        // Caso 0 ou 1 filho 
        filho = (p.getEsq() != null) ? p.getEsq() : p.getDir();
        paiFilho = p.getPai();

        if (filho != null)
            filho.setPai(paiFilho);

        if (paiFilho == null) {
            raiz = filho;
        } else if (p == paiFilho.getEsq()) {
            paiFilho.setEsq(filho);
        } else {
            paiFilho.setDir(filho);
        }

        NodeRN v = p;           // coloquei p como v pra n me perder
        NodeRN x = filho;       //no q entrou no lugar do removido

        if (corOriginal == true && (x == null || x.getCor() == true)) {
            return;
        } else if (corOriginal == false && (x != null && x.getCor() == true)) {
            x.setCor(false); 
            return;
        } else if (corOriginal == false && (x == null || x.getCor() == false)) {
            situacao3(v, x, paiFilho);
        } else if (corOriginal == true && (x != null && x.getCor() == false)) {
            situacao4(v, x, paiFilho);
        }
    }

    private void situacao4(NodeRN v, NodeRN x, NodeRN pai) {
        if (x != null) {
            x.setCor(true);
        }
        situacao3(v, x, pai);
    }

    private void situacao3(NodeRN v, NodeRN x, NodeRN pai) {
        while (x != raiz && (x == null || x.getCor() == false)) {
            NodeRN paiAtual;
            if (x == null) {
                paiAtual = pai;
            } else {
                paiAtual = x.getPai();
            }
            if (paiAtual == null) break;

            // definindo irmão, sobrinho perto e sobrinho longe
            NodeRN irmao;
            NodeRN sobrinhoPerto = null;
            NodeRN sobrinhoLonge = null;

            // Irmão é o filho oposto ao x
            if (x == paiAtual.getEsq()) {
                irmao = paiAtual.getDir();
            } else {
                irmao = paiAtual.getEsq();
            }

            // Se existir irmão, define sobrinhos conforme o lado do irmão
            if (irmao != null) {
                if (paiAtual.getEsq() == irmao) {   // irmão é filho esquerdo
                    sobrinhoPerto = irmao.getDir(); // perto = direito
                    sobrinhoLonge = irmao.getEsq(); // longe = esquerdo
                } else {                            // irmão é filho direito
                    sobrinhoPerto = irmao.getEsq(); // perto = esquerdo
                    sobrinhoLonge = irmao.getDir(); // longe = direito
                }
            }

            //Caso 1: irmão vermelho
            if (irmao != null && irmao.getCor() == true) {
                irmao.setCor(false);
                paiAtual.setCor(true);
                if (paiAtual.getDir() == irmao) {
                    rotacaoEsq(paiAtual);
                } else {
                    rotacaoDir(paiAtual);
                }
                // recalcula irmão e sobrinhos após rotação
                irmao = (x == paiAtual.getEsq()) ? paiAtual.getDir() : paiAtual.getEsq();
                if (irmao != null) {
                    if (paiAtual.getEsq() == irmao) {
                        sobrinhoPerto = irmao.getDir();
                        sobrinhoLonge = irmao.getEsq();
                    } else {
                        sobrinhoPerto = irmao.getEsq();
                        sobrinhoLonge = irmao.getDir();
                    }
                }
            }

            // ------------ Caso 2a/2b: irmão preto com filhos pretos ------------
            if ((sobrinhoPerto == null || sobrinhoPerto.getCor() == false) && (sobrinhoLonge == null || sobrinhoLonge.getCor() == false) && (irmao == null || irmao.getCor() == false)) {
                if (irmao != null) {
                    irmao.setCor(true);
                }
                if (paiAtual.getCor() == false) {   // Caso 2a: pai negro → sobe duplo negro
                    x = paiAtual;
                    pai = x.getPai();
                    continue;
                } else {                            // Caso 2b: pai rubro → pinta de negro e termina
                    paiAtual.setCor(false);
                    break;
                }
             }

            // ------------ Caso 3: sobrinho perto rubro, irmão negro ------------
            if ((sobrinhoPerto != null && sobrinhoPerto.getCor() == true) &&
               (irmao == null || irmao.getCor() == false) &&
               (sobrinhoLonge == null || sobrinhoLonge.getCor() == false)) {

                sobrinhoPerto.setCor(false);
                if (irmao != null) {
                    irmao.setCor(true);
                    if (paiAtual.getEsq() == irmao) {
                        rotacaoEsq(irmao);
                    } else {
                        rotacaoDir(irmao);
                    }
                }
                // recalcula irmão e sobrinhos após rotação
                irmao = (x == paiAtual.getEsq()) ? paiAtual.getDir() : paiAtual.getEsq();
                if (irmao != null) {
                    if (paiAtual.getEsq() == irmao) {
                        sobrinhoPerto = irmao.getDir();
                        sobrinhoLonge = irmao.getEsq();
                    } else {
                        sobrinhoPerto = irmao.getEsq();
                        sobrinhoLonge = irmao.getDir();
                    }
                }
            }

            // ------------ Caso 4: irmão negro e sobrinho longe rubro ------------
            if ((irmao == null || irmao.getCor() == false) &&
                (sobrinhoLonge != null && sobrinhoLonge.getCor() == true)) {

                if (irmao != null) {
                    irmao.setCor(paiAtual.getCor());
                    if (paiAtual.getDir() == irmao) {
                        rotacaoEsq(paiAtual);
                    } else {
                        rotacaoDir(paiAtual);
                    }
                }
                paiAtual.setCor(false);
                if (sobrinhoLonge != null) {
                    sobrinhoLonge.setCor(false);
                }
                break;
            }
        }
        if (raiz != null) {
            raiz.setCor(false);
        }
    }
}
