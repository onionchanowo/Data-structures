public interface MyTreeSet <E extends Comparable<E>>{
    void insert(int x);
    void remove(int e);
    int rank(int e);
    int select(int k);
    int predecessor(int e);
    int successor(int e);
}
