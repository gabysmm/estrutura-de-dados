package ArvoreAVL;

public class Teste {
    public static void main(String[] args) {
        AVL avl = new AVL();   

        int[] valores = {10, 5, 15, 2, 8, 22};
        for (int v : valores) {
            avl.insert(v);
        }
        avl.mostrar();
    
        System.out.println("\nInserir 25");
        avl.insert(25);
        System.out.println("rotoção simples esquerda em 15");
        avl.mostrar();
        
        System.out.println("\nremover 5");
        avl.remove(5);
        avl.mostrar();
    }
}