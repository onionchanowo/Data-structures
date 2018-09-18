import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<E extends Comparable<E>> {

    private class Node{
        public E e;
        Node left;
        Node right;

        Node(E e){
            this.e = e;
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

    // 向二分搜索树中用非递归的方式添加元素e
    public void add(E e){

        if(root == null){
            size ++;
            root = new Node(e);
        }

        Node pre = null;
        Node cur = root;

        while(cur != null){
            if(e.equals(cur.e)) return;
            else if(e.compareTo(cur.e) < 0){
                pre = cur;
                cur = cur.left;
            }
            else{
                pre = cur;
                cur = cur.right;
            }
        }

        if(e.compareTo(pre.e) < 0){
            pre.left = new Node(e);
            size ++;
        }
        else{
            pre.right = new Node(e);
            size ++;
        }
    }

    // 向二分搜索树中用递归的方式添加元素e
    public void insert(E e){
        root = insert(root, e);
    }

    private Node insert(Node node, E e){

        if(node == null){
            size ++;
            return new Node(e);
        }

        if(e.compareTo(node.e) < 0)
            node.left = insert(node.left, e);
        else if(e.compareTo(node. e) > 0)
            node.right = insert(node.right, e);

        return node;
    }

    //  看二分搜索树中是否存在元素e
    public boolean contains(E e){
        return contains(root, e);
    }

    private boolean contains(Node node, E e){

        if(node == null)
            return false;

        if(e.equals(node.e))
            return true;
        else if(e.compareTo(node.e) < 0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }

    // 前序遍历
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){

        if(node == null)
            return;

        System.out.println(node.e);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 非递归前序遍历
    public void preOrderNR(){

        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            Node cur = stack.pop();
            System.out.println(cur.e);
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
        System.out.println(node.e);
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
        System.out.println(node.e);
    }

    // 层序遍历
    public void levelOrder(){

        Queue<Node> q = new LinkedList<>();
        q.add(root);

        while(!q.isEmpty()){
            Node cur = q.remove();
            System.out.println(cur.e);
            if(cur.left != null) q.add(cur.left);
            if(cur.right != null) q.add(cur.right);
        }
    }

    // 寻找二分搜索树的最小元素
    public E minimum(){

        if(size == 0)
            throw new IllegalArgumentException("BST is empty.");

        return minimum(root).e;
    }

    private Node minimum(Node node){

        if(node.left == null){
            return node;
        }

        return minimum(node.left);
    }

    // 寻找二分搜索树的最大元素
    public E maximum(){

        if(size == 0)
            throw new IllegalArgumentException("BST is empty.");

        return minimum(root).e;
    }

    private Node maximum(Node node){

        if(node.right == null){
            return node;
        }

        return maximum(node.right);
    }

    // 删除二分搜索树中最小元素
    public E removeMin(){

        E ret = minimum();
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
    public E removeMax(){

        E ret = maximum();
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
    public void remove(E e){
        root = remove(root, e);
    }

    private Node remove(Node node, E e){

        if(node == null){
            return null;
        }

        if(e.compareTo(node.e) < 0){
            node.left = remove(node.left, e);
            return node;
        }
        else if(e.compareTo(node.e) > 0){
            node.right = remove(node.right, e);
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

        res.append(generateDepthString(depth)).append(node.e).append("\n");
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
}
