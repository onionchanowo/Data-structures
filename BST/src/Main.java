import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        BST<Integer> bst = new BST<>();

        int n = 1000;

        for(int i = 0; i < n; i ++){
            bst.insert((int)(Math.random() * 10000));
        }

        ArrayList<Integer> list = new ArrayList<>();
        while(!bst.isEmpty()){
            list.add(bst.removeMin());
        }

        for(int i = 1; i < list.size(); i ++){
            if(list.get(i - 1) > list.get(i))
                throw new IllegalArgumentException("Error.");
        }
        System.out.println(list);
        System.out.println("removeMin test completed.");
    }
}
