//
//  main.cpp
//  Q826
//
//  Created by Aarnav Gupta on 10/03/21.
//

#include <iostream>
#include <queue>
#include <unordered_set>
using std::cin;
using std::unordered_set;
using std::queue;
using std::cout;
using std::endl;

int main() {
    int max_size, num, temp, present_len = 0, answer = 0, temp2;
    cin >> max_size >> num;
    unordered_set<int> hash;
    queue<int> q;
    for (int t = 0; t < num; t++) {
        cin >> temp;
        if (hash.find(temp) == hash.end()) {
            if (present_len == max_size) {
                temp2 = q.front();
                q.pop();
                present_len--;
                hash.erase(temp2);
            }
            hash.insert(temp);
            q.push(temp);
            present_len++;
            answer++;
        }
    }
    cout << answer << endl;
}

/*
3 7
1 2 1 5 4 4 1
 */
