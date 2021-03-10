//
//  main.cpp
//  Q836
//
//  Created by Aarnav Gupta on 10/03/21.
//

#include <iostream>
#include <unordered_set>
using namespace std;

int main() {
    int n, t; unordered_set<int> hash;
    cin >> n;
    for (int i = 0; i < n; i++) {
        cin >> t;
        hash.insert(t);
    }
    t = 0;
    while (hash.find(t) != hash.end())
        t++;
    cout << t << endl;
}

/*
11
10 1 4 11 5 9 2 4 6 0 1
*/
