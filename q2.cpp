#include <bits/stdc++.h>
using namespace std;


class MyHashMap {
    public:
        int data[1000001];
        MyHashMap() {
            fill(data, data + 1000000, -1);
        }
        
        void put(int key, int value) {
            data[key] = value;
        }
        
        int get(int key) {
            return data[key];
        }
        
        void remove(int key) {
            data[key]=-1;
        }
    };

    
int main()
{
    MyHashMap obj;
    obj.put(1, 10);
    obj.put(2, 20);
    cout<<obj.get(1)<<endl;
    cout<<obj.get(3)<<endl;
    obj.put(2, 30);
    cout<<obj.get(2)<<endl;
    obj.remove(2);
    cout<<obj.get(2)<<endl;

    return 0;
}