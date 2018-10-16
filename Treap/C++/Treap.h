//
// Created by 杨宗霖 on 2018/10/16.
//

#ifndef TREAP_TREAP_H
#define TREAP_TREAP_H

#include <iostream>
#include <cassert>
const int inf = 0x3f3f3f3f;

using namespace std;

class Treap{

private:
    struct Node{
        int v, p; // v为节点的元素，p为该节点优先级
        int sum, recy; // sum ----> 该节点以及该节点所有字数的元素总数，recy ----> 自己重复次数
        Node *ch[2]; // 左，右孩子

        Node(int v): v(v){
            sum = recy = 1;
            ch[0] = ch[1] = NULL;
            p = rand();
        }

        // 确认寻找子树的方向
        int identify(int another){
            if(another == v) return -1;
            return another > v ? 1 : 0;
        }

        // 更新sum值
        void maintain(){
            sum = recy;
            sum += ch[0] == NULL ? 0 : ch[0]->sum;
            sum += ch[1] == NULL ? 0 : ch[1]->sum;
        }
    };

    Node *root;

    // rotate
    // 使整个Treap保持堆的性质
    // node ---> 要旋转的节点，dir ---> 旋转的方向： 0表示左旋转，1表示右旋转
    void rotate(Node* &node, int dir){
        Node *k = node->ch[dir^1];
        node->ch[dir^1] = k->ch[dir];
        k->ch[dir] = node;
        node->maintain();
        k->maintain();
        node = k;
    }

    // 向以根为node的Treap中插入元素e
    void insert(Node* &node, int e){
        if(node == NULL){
            node = new Node(e);
            //cout << node->p << endl;
            return;
        }
        int dir = node->identify(e);
        if(dir == -1) node->recy ++;
        else{
            insert(node->ch[dir], e);
            if(node->ch[dir]->p > node->p) rotate(node, dir^1);
        }
        node->maintain();
    }

    // 在以node为根的Treap总查找元素e
    bool find(Node *node, int e){
        if(node == NULL) return false;
        int dir = node->identify(e);
        if(dir == -1) return true;
        return find(node->ch[dir], e);
    }

    // 在以node为根的Treap中删除元素e
    void remove(Node* &node, int e){
        int dir = node->identify(e);
        if(dir == -1){
            if(node->recy > 1) node->recy --;
            else if(node->ch[1] == NULL) node = node->ch[0];
            else if(node->ch[0] == NULL) node = node->ch[1];
            else{
                int rotateDir = node->ch[0]->p > node->ch[1]->p ? 1 : 0;
                rotate(node, rotateDir);
                remove(node->ch[rotateDir], e);
            }
        }
        else remove(node->ch[dir], e);
        if(node != NULL) node->maintain();
    }

    // 在以node为根的Treap只能够查询元素e的排名rank
    int rank(Node *node, int e, int res){
        int dir = node->identify(e);
        if(dir == -1){
            if(node->ch[0] != NULL) res += node->ch[0]->sum;
            return res + 1;
        }
        else if(dir == 0){
            return rank(node->ch[dir], e, res);
        }
        else{
            if(node->ch[0] != NULL) res += node->ch[0]->sum;
            return rank(node->ch[dir], e, res + node->recy);
        }
    }

    // 在以node为根的Treap中寻找排名第k小的元素
    int select(Node *node, int k){
        if(node == NULL || k <= 0 || k > node->sum) return 0;
        int s = node->ch[0] == NULL ? 0 : node->ch[0]->sum;
        if(s < k && k <= s + node->recy) return node->v;
        if(k <= s) return select(node->ch[0], k);
        return select(node->ch[1], k - s - node->recy);
    }

    void destory(Node *node){
        if(node == NULL) return;
        destory(node->ch[0]);
        destory(node->ch[1]);
        delete node;
    }

public:
    Treap(){
        root = NULL;
    }

    ~Treap(){
        destory(root);
    }

    // 向Treap中插入节点e
    void insert(int e){
        insert(root, e);
    }

    // 在Treap中查找是否存在元素e
    bool find(int e){
        return find(root , e);
    }

    // 在Treap中删除元素e
    // 删除前调用find()方法检查是否存在元素e
    void remove(int e){
        //assert(find(e));
        if(!find(e)) return;
        remove(root, e);
    }

    // 查询元素e在Treap中的排名rank
    int rank(int e){
        //assert(find(e));
        if(!find(e)) return -1;
        return rank(root, e, 0);
    }

    // 寻找Treap中第k小的元素
    int select(int k){
        return select(root, k);
    }

    // 寻找Treap中元素e的前驱
    int lower(int e){
        Node *cur = root;
        int ret = -inf;
        while(cur != NULL){
            //cout << cur->v << endl;
            if(cur->v < e && cur->v > ret) ret = cur->v;
            if(e > cur->v) cur = cur->ch[1];
            else cur = cur->ch[0];
        }
        return ret;
    }

    // 寻找Treap中元素e的后继
    int upper(int e){
        Node *cur = root;
        int ret = inf;
        while(cur != NULL){
            //cout << cur->v << endl;
            if(cur->v > e && cur->v < ret) ret = cur->v;
            if(e < cur->v) cur = cur->ch[0];
            else cur = cur->ch[1];
        }
        return ret;
    }
};


#endif //TREAP_TREAP_H
