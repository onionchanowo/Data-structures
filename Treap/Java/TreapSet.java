public class TreapSet <E extends Comparable<E>> implements MyTreeSet<E> {

    private Treap treap;

    public TreapSet(){
        treap = new Treap();
    }

    @Override
    public void insert(int x){
        treap.insert(x);
    }

    @Override
    public void remove(int e){
        treap.remove(e);
    }

    @Override
    public int rank(int e){
        return treap.rank(e);
    }

    @Override
    public int select(int k){
        return treap.select(k);
    }

    @Override
    public int predecessor(int e){
        return treap.predecessor(e);
    }

    @Override
    public int successor(int e){
        return treap.successor(e);
    }
}
