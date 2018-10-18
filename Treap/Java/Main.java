import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i <= 100000; i ++)
            list.add(i);

        Treap treap = new Treap();
        AVLTree<Integer, Object> avl = new AVLTree<>();
        BST<Integer> bst = new BST<>();

        long startTime = System.nanoTime();
        for(int e: list){
            treap.insert(e);
        }
        long endTime = System.nanoTime();

        System.out.println("Treap: " + (double)(endTime - startTime) / 1000000000.0);

        startTime = System.nanoTime();
        for(int e: list){
            avl.insert(e, null);
        }
        endTime = System.nanoTime();
        System.out.println("AVLTree: " + (double)(endTime - startTime) / 1000000000.0);

        startTime = System.nanoTime();
        for(int e: list){
            bst.add(e);
        }
        endTime = System.nanoTime();
        System.out.println("BST: " + (double)(endTime - startTime) / 1000000000.0);
    }
}
