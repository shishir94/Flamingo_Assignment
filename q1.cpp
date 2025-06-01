#include <iostream>
#include <bits/stdc++.h>

using namespace std;

list<int> dll;  // doubly-linked-list
map<int, pair<list<int>::iterator, int>> mp;   //  key--> {address,value}
int n;

class LRUCache
{
public:
    LRUCache(int capacity)
    {
        n = capacity;
    }

    void makeLRU(int key)
    {
        dll.erase(mp[key].first);
        dll.push_front(key);
        mp[key].first = dll.begin();
    }

    int get(int key)
    {
        if (mp.find(key) == mp.end())
            return -1;

        makeLRU(key);
        return mp[key].second;
    }

    void put(int key, int value)
    {
        if (mp.find(key) != mp.end())
        {
            mp[key].second = value;
            makeLRU(key);
        }
        else
        {
            dll.push_front(key);
            mp[key] = {dll.begin(), value};
            n--;
        }
        if (n < 0)
        {
            int key_to_remove = dll.back();
            mp.erase(key_to_remove);
            dll.pop_back();
            n++;
        }
    }
};

int main()
{
    LRUCache lru(2);
    lru.put(1, 1);
    lru.put(2, 2);
    cout<<lru.get(1)<<endl;
    lru.put(3, 3);
    cout<<lru.get(2)<<endl;
    lru.put(4, 4);
    cout<<lru.get(1)<<endl;
    cout<<lru.get(3)<<endl;
    cout<<lru.get(4)<<endl;

    return 0;
}