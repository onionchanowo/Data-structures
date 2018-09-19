import java.nio.file.FileVisitOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {

    private class Node{
        public K key;
        public V value;
        Node left;
        Node right;

        Node(K key, V value){
            this.key = key;
            this.value = value;
            left = right = null;
        }
    }
    private Node root;
    private int size;

    public BST(){
        root = null;
        size = 0;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }


    // 向二分搜索树中用递归的方式添加元素e
    public void insert(K key, V value){
        root = insert(root, key, value);
    }

    private Node insert(Node node, K key, V value){

        if(node == null){
            size ++;
            return new Node(key, value);
        }

        if(key.compareTo(node.key) < 0)
            node.left = insert(node.left, key, value);
        else if(key.compareTo(node. key) > 0)
            node.right = insert(node.right, key, value);

        return node;
    }

    //  看二分搜索树中是否存在元素e
    public boolean contains(K key){
        return contains(root, key);
    }

    private boolean contains(Node node, K key){

        if(node == null)
            return false;

        if(key.equals(node.key))
            return true;
        else if(key.compareTo(node.key) < 0)
            return contains(node.left, key);
        else
            return contains(node.right, key);
    }

    public void set(K key, V value){
        set(root, key, value);
    }

    private void set(Node node, K key, V value){

        if(node == null){
            return;
        }

        if(key.equals(node.key)){
            node.value = value;
            return;
        }
        else if(key.compareTo(node.key) < 0){
            set(node.left, key, value);
        }
        else{
            set(node.right, key, value);
        }
    }

    public V get(K key){
        return get(root, key);
    }

    private V get(Node node, K key){

        if(node == null){
            return null;
        }

        if(key.equals(node.key)){
            return node.value;
        }
        else if(key.compareTo(node.key) < 0){
            return get(node.left, key);
        }
        else{
            return get(node.right, key);
        }
    }

    // 前序遍历
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){

        if(node == null)
            return;

        System.out.println(node.value);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 非递归前序遍历
    public void preOrderNR(){

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.value);
            if(cur.right != null) stack.push(cur.right);
            if(cur.left != null) stack.push(cur.left);
        }
    }

    // 中序遍历
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){

        if(node == null)
            return;

        inOrder(node.left);
        System.out.println(node.value);
        inOrder(node.right);
    }

    // 后序遍历
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){

        if(node == null)
            return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.value);
    }

    // 层序遍历
    public void levelOrder(){

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.value);
            if(cur.left != null) q.add(cur.left);
            if(cur.right != null) q.add(cur.right);
        }
    }

    // 寻找二分搜索树的最小元素
    public V minimum(){

        if(size == 0)
            throw new IllegalArgumentException("BST is empty.");

        return minimum(root).value;
    }

    private Node minimum(Node node){

        if(node.left == null){
            return node;
        }

        return minimum(node.left);
    }

    // 寻找二分搜索树的最大元素
    public V maximum(){

        if(size == 0)
            throw new IllegalArgumentException("BST is empty.");

        return minimum(root).value;
    }

    private Node maximum(Node node){

        if(node.right == null){
            return node;
        }

        return maximum(node.right);
    }

    // 删除二分搜索树中最小元素
    public V removeMin(){

        V ret = minimum();
        root = removeMin(root);
        return ret;
    }

    private Node removeMin(Node node){

        if(node.left == null){
            Node rightNode = node.right;
            node.right = null;
            size --;
            return rightNode;
        }

        node.left = removeMin(node.left);
        return node;
    }

    // 删除二分搜索树中最大元素
    public V removeMax(){

        V ret = maximum();
        root = removeMax(root);
        return ret;
    }

    private Node removeMax(Node node){

        if(node.right == null){
            Node leftNode = node.left;
            node.left = null;
            size --;
            return leftNode;
        }

        node.right = removeMax(node.right);
        return node;
    }

    //  删除二分搜索树中的元素e
    public void remove(K key){
        root = remove(root, key);
    }

    private Node remove(Node node, K key){

        if(node == null){
            return null;
        }

        if(key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
            return node;
        }
        else if(key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
            return node;
        }
        else{
            if(node.left == null){
                Node rightNode = node.right;
                node.right = null;
                size --;
                return rightNode;
            }
            if(node.right == null){
                Node leftNode = node.left;
                node.left = null;
                size --;
                return leftNode;
            }
            Node successor = minimum(node.right);
            successor.right = removeMin(node.right);
            successor.left = node.left;
            node.left = node.right = null;
            return successor;
        }
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0, res);
        return res.toString();
    }

    // 生成以node为根节点，深度为depth的描述二叉树的字符串
    private void generateBSTString(Node node, int depth, StringBuilder res){

        if(node == null){
            res.append(generateDepthString(depth)).append("null\n");
            return;
        }

        res.append(generateDepthString(depth)).append(node.value).append("\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    private String generateDepthString(int depth){

        StringBuilder res = new StringBuilder();
        for(int i = 0; i < depth; i ++){
            res.append("--");
        }
        return res.toString();
    }

    public static void main(String[] args){

        System.out.println("Pride and Prejudice");

        ArrayList<String> words = new ArrayList<>();
        if(FileOperation.readFile("pride-and-prejudice.txt", words)){
            System.out.println("Total words:" + words.size());

            BST<String, Integer> map = new BST<>();
            for(String word: words){
                if(map.contains(word))
                    map.set(word, map.get(word) + 1);
                else
                    map.insert(word, 1);
            }

            System.out.println("Total different words: " + map.size());
            System.out.println("Frequency of PRIDE: " + map.get("pride"));
            System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        }
        else
            System.out.println(":(");
    }
}
