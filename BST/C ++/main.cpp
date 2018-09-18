#include <iostream>
#include <cassert>
#include <queue>
#include <cmath>
using namespace std;

template <typename Key, typename Value>
class AVLTree{

private:
    struct Node{
        Key key;
        Value value;
        Node *left;
        Node *right;
        int height;

        Node(Key key, Value value){
            this->key = key;
            this->value = value;
            height = 1;
            left = right = NULL;
        }

        Node(Node *node){
            this->key = node->key;
            this->value = node->value;
            this->left = node->left;
            this->right = node->right;
            this->height = node->height;
        }
    };

    Node *root;
    int count;

    // 向以node为根的二分搜索树中，插入(key, value)
    // 返回插入新节点后的二分搜索树的根
    Node *insert(Node *node, Key key, Value value){

        if(node == NULL){
            count ++;
            return new Node(key, value);
        }

        if(key == node->key){
            node->value = value;
        }
        else if(key < node->key){
            node->left = insert(node->left, key, value);
        }
        else{  // key > node->key
            node->right = insert(node->right, key, value);
        }

        return node;
    }

    // 在以node为根的二分搜索树中，查询是否存在key
    bool contains(Node *node, Key key){

        if(node == NULL){
            return false;
        }

        if(key == node->key){
            return true;
        }
        else if(key < node->key){
            return contains(node->left, key);
        }
        else{
            return contains(node->right, key);
        }
    }

    //  在以node为根的二分搜索树中，查找key所对应的value
    Value search(Node *node, Key key){

        assert(contains(key));

        if(key == node->key){
            return node->value;
        }
        else if(key < node->key){
            return search(node->left, key);
        }
        else{
            return search(node->right, key);
        }
    }

    // 对以node为根的二分搜索树前序遍历
    void preOrder(Node *node){

        if(node == NULL{
            return;
        }

        cout << node->value << endl;
        preOrder(node->left);
        preOrder(node->right);
    }

    // 对以node为根的二分搜索树中序遍历
    void inOrder(Node *node){

        if(node == NULL){
            return;
        }

        inOrder(node->left);
        cout << node->value << endl;
        inOrder(node->right);
    }

    // 对以node为根的二分搜索树后续遍历
    void postOrder(Node *node){

        if(node == NULL){
            return;
        }

        postOrder(node->left);
        postOrder(node->right);
        cout << node->value << endl;
    }

    void destroy(Node *node){

        if(node == NULL){
            return;
        }

        destroy(node->left);
        destroy(node->right);

        delete node;
        count --;
    }

    // 在以node为根的二分搜索中，返回最小值的节点
    Node *minimum(Node *node){

        if(node->left == NULL){
            return node;
        }

        return minimum(node->left);
    }

    // 在以node为根的二分搜索树中，返回最大值的节点
    Node *maximum(Node *node){

        if(node->right == NULL){
            return node;
        }

        return maximum(node->right);
    }

    // 删除以node为根的二分搜索树的最小值
    // 返回新的二分搜索树的根
    Node *removeMin(Node *node){

        if(node->left == NULL){
            Node *rightNode = node->right;
            delete node;
            count --;
            return rightNode;
        }

        node->left = removeMin(node->left);
        return node;
    }

    // 删除以node为根的二分搜索树的最大值
    // 返回新的二分搜索树的根
    Node *removeMax(Node *node){

        if(node->right == NULL){
            Node *leftNode = node->left;
            delete node;
            count --;
            return leftNode;
        }

        node->right = removeMax(node->right);
        return node;
    }

    // 删除以node为根的二分搜索树中键值为key的节点
    // 返回新的二分搜索树的根
    Node *remove(Node *node, Key key){

        if(node == NULL){
            return NULL;
        }

        if(key < node->key){
            return remove(node->left, key);
        }
        if(key > node->key){
            return remove(node->right, key);
        }

        // key == node->key
        if(node->left == NULL){
            Node *rightNode = node->right;
            delete node;
            count --;
            return rightNode;
        }
        if(node->right == NULL){
            Node *leftNode = node->left;
            delete node;
            count --;
            return leftNode;
        }
        // !!!
        Node *successor = new Node(minimum(node->right));
        count ++;
        successor->right = removeMax(node->right);
        successor->left = node->left;
        delete node;
        count --;
        return successor;
    }
    

public:
    AVLTree(){
        root = NULL;
        count = 0;
    }

    ~AVLTree(){
        destroy(root);
    }

    int size(){
        return count;
    }

    bool isEmpty(){
        return count == 0;
    }

    void insert(Key key, Value value){
        root = insert(root, key, value);
    }

    bool contains(Key key){
        return contains(root, key);
    }

    Value search(Key key){
        return search(root, key);
    }

    // 前序遍历
    void preOrder(){
        preOrder(root);
    }

    // 中序遍历
    void inOrder(){
        inOrder(root);
    }

    // 后续遍历
    void postOrder(){
        postOrder(root);
    }

    // 层序遍历
    void levelOrder(){

        queue<Node*> q;
        q.push(root);

        while(!q.empty()){
            Node *node = q.front();
            q.pop();

            cout << node->value << endl;
            if(node->left != NULL){
                q.push(node->left);
            }
            if(node->right != NULL){
                q.push(node->right);
            }
        }
    }

    // 寻找最小键值
    Key minimum(){

        assert(count != 0);
        Node *node = minimum(root);
        return node->key;
    }

    // 寻找最大键值
    Key maximum(){

        assert(count != 0);
        Node *node = maximum(root);
        return node->key;
    }

    // 删除最小值
    void removeMin(){

        if(root != NULL){
            root = removeMin(root);
        }
    }

    // 删除最大值
    void removeMax(){

        if(root != NULL){
            root = removeMax(root);
        }
    }

    // 删除键值为key的节点
    void remove(Key key){

        root = remove(root, key);
    }
};

int main(){

    return 0;
}