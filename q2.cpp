#include <bits/stdc++.h>
using namespace std;


vector<int>dp;
class MyHashMap
{
public:
    MyHashMap()
    {
    }
    void put(int key, int value)
    {
        dp[key] = value;
    }
    int get(int key)
    {
        return dp[key];
    }
    void remove(int key)
    {

        dp[key] = -1;
    }
};

int main()
{
    dp.resize(1e6+1,-1);
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