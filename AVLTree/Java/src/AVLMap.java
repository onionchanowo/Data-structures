public class AVLMap<K extends Comparable<K>, V> implements Map<K, V> {

    private AVLTree<K, V> avl;

    AVLMap(){
        avl = new AVLTree<>();
    }

    @Override
    public void add(K key, V value) {
        avl.insert(key, value);
    }

    @Override
    public V remove(K key){
        return avl.remove(key);
    }

    @Override
    public boolean contains(K key){
        return avl.contains(key);
    }

    @Override
    public V get(K key) {
       return avl.get(key);
    }

    @Override
    public void set(K key, V newValue) {
        avl.set(key, newValue);
    }

    @Override
    public int getSize() {
        return avl.size();
    }

    @Override
    public boolean isEmpty() {
        return avl.isEmpty();
    }
}
