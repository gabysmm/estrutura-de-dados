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
        
        System.out.println("buscando 99 " + (rn.buscarNode(99) == null ? "Não encontrado" : "Encontrado"));
    }
}