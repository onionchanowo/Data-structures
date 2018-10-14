//
// Created by 杨宗霖 on 2018/10/13.
//

#ifndef SPLAYTREE_SPLAYTREE_H
#define SPLAYTREE_SPLAYTREE_H

#include <iostream>
#include <cassert>
using namespace std;

const int MAXN = 100005;
const int INF = 0x3f3f3f3f;

class SplayTree{

#define root node[0].ch[1]
private:
    struct Node{
        int v, father; // 节点中的元素，父亲节点的index
        int ch[2]; // 0表示左孩子，1表示有孩子
        int sum; // 自己 + 自己下级的节点数，根节点sum = 1
        int recy; // 自己的重复次数
    }node[MAXN];
    int size, nodes; // size --> SplayTree中的元素个数
    // nodes --> SplayTree中的节点个数

    //维护第index个节点的sum
    void upDate(int index){
        int lc = node[index].ch[0];
        int rc = node[index].ch[1];
        node[index].sum = node[lc].sum + node[rc].sum + node[index].recy;
    }

    // 获取父子关系，identify(index) --> node[index]是node[index].father的左子树 or 右子树
    int identify(int index){
        return node[node[index].father].ch[0] == index ? 0 : 1;
    }

    // 建立父子关系，connect(u, v，dir) --> u是v的dir孩子
    // 并不断开另外的边
    void connect(int u, int v, int dir){
        node[u].father = v;
        node[v].ch[dir] = u;
    }

    // 旋转节点
    // 依靠异或特性自动判断旋转方向 ：）
    void rotate(int x){
        // x是y的yson孩子
        int yson = identify(x);
        // y是其父亲节点的rootson孩子
        int y = node[x].father;
        int rootson = identify(y);
        int yfather = node[y].father;
        // rotate
        connect(node[x].ch[yson^1], y, yson);
        connect(y, x, yson^1);
        connect(x, yfather, rootson);
        // 维护节点信息
        upDate(y);
        upDate(x);
    }

    // Splay ：）
    // 将u旋转到v所在的位置
    void Splay(int u, int v){
        int anc = node[v].father;
        while(node[u].father != anc){
            int fa = node[u].father;
            // u为目标位置的左（右）孩子时，只需要旋转一次
            if(node[fa].father == anc) rotate(u);
                // u与父亲节点同侧
            else if(identify(u) == identify(fa)){
                rotate(fa);
                rotate(u);
            }
            else{ // u与父亲节点不同侧
                rotate(u);
                rotate(u);
            }
        }
    }

    // 找到合适位置后，添加节点
    void add(int value, int father){
        nodes ++;
        node[nodes].v = value;
        node[nodes].father = father;
        node[nodes].recy = node[nodes].sum = 1;
    }

    // 摧毁节点
    void destroy(int index){
        node[index].v = node[index].ch[0] = node[index].ch[1] =
        node[index].father = node[index].sum = node[index].recy = 0;
        //if(index == nodes) nodes --;
    }

    // 插入节点
    // 返回插入节点的index
    int insert(int value){
        size ++;
        if(root == 0){
            add(value, 0);
            root = nodes;
            return nodes;
        }
        else{
            int cur = root;
            while(true){
                node[cur].sum ++; // 只要经过该节点，该节点sum增加
                if(value == node[cur].v){
                    node[cur].recy ++;
                    return cur;
                }
                int next = value < node[cur].v ? 0 : 1;
                if(!node[cur].ch[next]){
                    add(value, cur);
                    node[cur].ch[next] = nodes;
                    return nodes;
                }
                cur = node[cur].ch[next];
            }
        }
    }

public:
    SplayTree(){
        size = nodes = 0;
    }
    // 查找value在SplayTree中的节点编号
    // 若查找失败，返回0
    int find(int value){
        int cur = root;
        while(true){
            if(value == node[cur].v){
                Splay(cur, root);
                return cur;
            }
            int next = value < node[cur].v ? 0 : 1;
            if(!node[cur].ch[next]) return 0;
            cur = node[cur].ch[next];
        }
    }

    // 向SplayTree中添加元素value
    void push(int value){
        int adder = insert(value);
        Splay(adder, root);
    }

    // 从SplayTree中删除元素value
    void pop(int value){
        int tot = find(value);
        if(!tot) return;
        //assert(!tot);
        size --;
        if(node[tot].recy > 1){
            node[tot].recy --;
            node[tot].sum --;
            return;
        }
        if(!node[tot].ch[0]){
            root = node[tot].ch[1];
            node[root].father = 0;
            destroy(tot);
        }
        else if(!node[tot].ch[1]){
            root = node[tot].ch[0];
            node[root].father = 0;
            destroy(tot);
        }
        else{
            int left = node[tot].ch[0];
            int right = node[tot].ch[1];
            while(node[left].ch[1]) left = node[left].ch[1];
            Splay(left, node[tot].ch[0]);
            connect(right, left, 1);
            connect(left, 0, 1);
            upDate(left);
            root = node[tot].ch[0];
            node[root].father = 0;
            destroy(tot);
        }
    }

    // 获取元素的rank
    int rank(int value){
        int cur = root;
        int res = 0;
        while(true){
            if(!cur) return 0;
            if(node[cur].v == value){
                res += node[node[cur].ch[0]].sum + 1;
                break;
            }
            else if(value < node[cur].v){
                cur = node[cur].ch[0];
            }
            else{
                res += node[cur].recy + node[node[cur].ch[0]].sum;
                cur = node[cur].ch[1];
            }
        }
        if(cur) Splay(cur, root);
        return res;
    }

    // 获取排名为n的元素
    int select(int n){

        //cout << "n:" << n << " " << "size:" << size << endl;
        if(n > size) return -1;
        int cur = root;
        while(true){
            int temp = node[cur].sum - node[node[cur].ch[1]].sum;
            if(n > node[node[cur].ch[0]].sum && n <= temp) break;
            else if(n <= node[node[cur].ch[0]].sum){
                cur = node[cur].ch[0];
            }
            else{
                n -= temp;
                cur = node[cur].ch[1];
            }
        }
        Splay(cur, root);
        //if(node[cur].v == 254822) cout << "select: " << n << endl;
        return node[cur].v;
    }

    // 查找元素value的前驱
    int lower(int value){
        int cur = root;
        int res = -INF;
        while(cur){
            if(node[cur].v < value && node[cur].v > res) res = node[cur].v;
            if(node[cur].v < value) cur = node[cur].ch[1];
            else cur = node[cur].ch[0];
        }
        return res;
    }

    // 查找元素value的后继
    int upper(int value){
        int cur = root;
        int res = INF;
        while(cur){
            if(node[cur].v > value && node[cur].v < res) res = node[cur].v;
            if(node[cur].v > value) cur = node[cur].ch[0];
            else cur = node[cur].ch[1];
        }
        return res;
    }

};


#undef root

#endif //SPLAYTREE_SPLAYTREE_H
