package ArvoreRN;

public class TesteRN {
    public static void main(String[] args) {
        RubroNegra rn = new RubroNegra();   

        int[] valores = {10, 5, 15, 2, 8, 22};
        for (int v : valores) {
            rn.insert(v);
        }
        System.out.println("\nÁrvore");
        rn.mostrar(); 

        System.out.println("\ninsere 25");
        rn.insert(25);
        rn.mostrar();

        System.out.println("\ninsere 1");
        rn.insert(1);
        rn.mostrar();

        System.out.println("\nRemover 8 (Situação 1: v vermelho, x vermelho)");
        rn.remove(8);
        rn.mostrar();

        System.out.println("\nRemover 2 (Situação 2: v preto, x vermelho)");
        rn.remove(2);
        rn.mostrar();

        System.out.println("\nRemover 1 (situação 3: duplo negro)");
        rn.remove(1);
        rn.mostrar();

        System.out.println("\nRemover 22 (testa caso com irmão)");
        rn.remove(22);
        rn.mostrar();

        System.out.println("\nRemover 10 (raiz)");
        rn.remove(10);
        rn.mostrar();

        System.out.println("\nRemover 5");
        rn.remove(5);
        rn.mostrar();

        System.out.println("\nRemover 15");
        rn.remove(15);
        rn.mostrar();

        System.out.println("\nRemover 25");
        rn.remove(25);
        rn.mostrar();
        
        System.out.println("buscando 99 " + (rn.buscarNode(99) == null ? "Não encontrado" : "Encontrado"));
    }
}