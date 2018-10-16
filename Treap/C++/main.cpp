#include <iostream>
#include "Treap.h"
using namespace std;

int main(){

    ios::sync_with_stdio(false);
    int n;
    cin >> n;
    Treap t = Treap();
    while(n --){
        int opt, x;
        cin >> opt >> x;
        if(opt == 1){
            t.insert(x);
        }
        else if(opt == 2){
            t.remove(x);
        }
        else if(opt == 3){
            cout << t.rank(x) << endl;
        }
        else if(opt == 4){
            cout << t.select(x) << endl;
        }
        else if(opt == 5){
            cout << t.lower(x) << endl;
        }
        else if(opt == 6){
            cout << t.upper(x) << endl;
        }
        else if(opt == 10){
            t.inOrder();
        }
    }
    return 0;
}