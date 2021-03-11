//
//  main.cpp
//  Q815
//
//  Created by Aarnav Gupta on 11/03/21.
//

#include <iostream>
#include <unordered_set>
using namespace std;

int main() {
    int n, temp, find;
    unordered_set<int> nums;
    cin >> n;
    nums.insert(0);
    nums.insert(n+1);
    for (int i = 0; i < n; i++) {
        cin >> temp;
        
        find = temp-1;
        while (nums.find(find) == nums.end())
            find--;
        cout << find << " ";
        
        find = temp+1;
        while (nums.find(find) == nums.end())
            find++;
        cout << find << endl;
        
        nums.insert(temp);
    }
}

/*
5
1 5 2 4 3
 */
