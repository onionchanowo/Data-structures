import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Treap {

    private class Node{
        public int value;
        public int priority;
        public Node[] ch = new Node[2];
        public int sum;
        public int recy;
        public Node father;

        public Node(int value, int priority){
            this.value = value;
            this.priority = priority;
            ch[0] = ch[1] = null;
            sum = recy = 1;
        }

        public int identify(int e){
            if(e == value) return -1;
            return e < value ? 0 : 1;
        }

        public void maintain(){
            sum = recy;
            sum += ch[0] == null ? 0 : ch[0].sum;
            sum += ch[1] == null ? 0 : ch[1].sum;
        }

        @Override
        public String toString(){
            StringBuilder res = new StringBuilder();
            res.append("(").append("value=").append(value).append(", ").
                    append("priority=").append(priority).append(")");
            return res.toString();
        }
    }
    private Random random;
    private Node root;
    private static final int DEFAULT_RD = 1 << 10;
    private static final int INTERGER_INF = 0x3f3f3f3f;

    // 对node节点的旋转
    // dir = 0 --> 左旋转， dir = 1 --> 右旋转
    private Node rotate(Node node, int dir){
        Node k = node.ch[dir^1];
        node.ch[dir^1] = k.ch[dir];
        k.ch[dir] = node;
        node.maintain();
        k.maintain();
        return k;
    }

    public Treap(){
        root = null;
        random = new Random();
    }

    public void insert(int e){
        root = insert(root, e);
    }

    private Node insert(Node node, int e){
        if(node == null){
            return new Node(e, random.nextInt(DEFAULT_RD));
        }

        int dir = node.identify(e);
        // 如果e存在
        if(dir == -1) node.recy ++;
        else{
            node.ch[dir] = insert(node.ch[dir], e);
            // 如果插入节点后左右子树的优先级大于原来节点的优先级,就旋转该节点x
            if(node.ch[dir].priority > node.priority){
                node = rotate(node, dir^1);
            }
        }
        node.maintain();
        //System.out.println(node.priority);
        return node;
    }

    public boolean find(int e){
        return find(root, e);
    }

    private boolean find(Node node, int e){
        if(node == null)
            return false;

        int dir = node.identify(e);
        if(dir == -1) return true;
        return find(node.ch[dir], e);
    }

    public void remove(int e){
        if(!find(e))
            throw new IllegalArgumentException("the element is not exist in Treap.");
        root = remove(root, e);
    }

    private Node remove(Node node, int e){
        int dir = node.identify(e);
        if(dir == -1){
            if(node.recy > 1) node.recy --;
            else{
                if(node.ch[0] == null) return node.ch[1];
                else if(node.ch[1] == null) return node.ch[0];
                else{
                    int pre = node.ch[1].priority > node.ch[0].priority ? 0 : 1;
                    node = rotate(node, pre);
                    node.ch[pre] = remove(node.ch[pre], e);
                }
            }
        }
        else node.ch[dir] = remove(node.ch[dir], e);
        if(node != null) node.maintain();
        return node;
    }

    private void inOrder(Node node, ArrayList<Node> nodeList){
        if(node == null) return;
        inOrder(node.ch[0], nodeList);
        //System.out.println(node.value);
        nodeList.add(node);
        inOrder(node.ch[1], nodeList);
    }

    public int rank(int e){
        if(!find(e))
            throw new IllegalArgumentException("the element is not exist in Treap.");
        return rank(root, e, 0);
    }

    private int rank(Node node, int e, int res){
        int dir = node.identify(e);
        if(dir == -1){
            if(node.ch[0] != null) res += node.ch[0].sum;
            return res + 1;
        }
        else if(dir == 0){
            return rank(node.ch[0], e, res);
        }
        else{
            if(node.ch[0] != null) res += node.ch[0].sum;
            return rank(node.ch[1], e, res + node.recy);
        }
    }

    public int select(int k){
        return select(root, k);
    }

    private int select(Node node, int k){
        if(node == null || k <= 0 || k > node.sum)
            return -1;
        int s = node.ch[0] == null ? 0 : node.ch[0].sum;
        if(s < k && k <= s + node.recy)
            return node.value;
        if(k <= s) return select(node.ch[0], k);
        return select(node.ch[1], k - s - node.recy);
    }

    public int predecessor(int e){
        Node cur = root;
        int ret = -INTERGER_INF;
        while(cur != null){
            if(cur.value < e && cur.value > ret) ret = cur.value;
            if(cur.value >= e) cur = cur.ch[0];
            else cur = cur.ch[1];
        }
        return ret;
    }

    public int successor(int e){
        Node cur = root;
        int ret = INTERGER_INF;
        while(cur != null){
            if(cur.value > e && cur.value < ret) ret = cur.value;
            if(cur.value <= e) cur = cur.ch[1];
            else cur = cur.ch[0];
        }
        return ret;
    }


    @Override
    public String toString(){
        ArrayList<Node> nodeList = new ArrayList<>();
        inOrder(root, nodeList);
        return nodeList.toString();
    }

    public static void main(String[] args){
        Treap treap = new Treap();
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        while(m -- > 0){
            int opt, x;
            opt = sc.nextInt();
            x = sc.nextInt();
            if(opt == 1){
                treap.insert(x);
            }
            else if(opt == 2){
                treap.remove(x);
            }
            else if(opt == 3){
                System.out.println(treap.rank(x));
            }
            else if(opt == 4){
                System.out.println(treap.select(x));
            }
            else if(opt == 5){
                System.out.println(treap.predecessor(x));
            }
            else if(opt == 6){
                System.out.println(treap.successor(x));
            }
        }
    }
}
