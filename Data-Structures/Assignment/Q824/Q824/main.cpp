//
//  main.cpp
//  Q824
//
//  Created by Aarnav Gupta on 11/03/21.
//

#include <iostream>
#include <unordered_set>
#include <unordered_map>
using namespace std;

int main() {
    long int n, C, temp, cnt;
    unordered_map<long int, long int> hash;
    unordered_set<long int> unique;
    
    while (cin >> n >> C) {
        cnt = 0;
        hash.clear();
        unique.clear();
        
        for (long int i = 0; i < n; i++) {
            cin >> temp;
            if (hash.find(temp) == hash.end())
                hash.insert({temp, 1});
            else
                hash[temp]++;
            unique.insert(temp);
        }
        
        for (long int x: unique) {
            temp = x + C;
            if (hash.find(temp) != hash.end())
                cnt += (hash[temp]*hash[x]);
        }
        
        cout << cnt << endl;
    }
}

/*
4 1
1 1 2 3
2 0
1 1
5 0
1 1 1 2 3
6 0
1 1 1 2 2 3
4 1
1 1 2 2
*/
