//
//  main.cpp
//  Q749
//
//  Created by Aarnav Gupta on 10/03/21.
//

#include <iostream>
#include <vector>
using namespace std;

int N, first_node;

void printPostOrder(vector<int> preorder, vector<int> inorder, int n) {
    int node_index;
    vector<int>::const_iterator first, last;
    for (int i = 0; i < n; i++)
        if (inorder[i] == preorder[0])
            node_index = i;
    if (node_index != 0) {
        first = preorder.begin() + 1;
        last = preorder.begin() + node_index + 1;
        vector<int> newpreorder(first, last);
        printPostOrder(newpreorder, inorder, node_index);
    }
    if (node_index != n-1) {
        first = preorder.begin() + 1 + node_index;
        last = preorder.begin() + n + 1;
        vector<int> newpreorder(first, last);
        first = inorder.begin() + 1 + node_index;
        last = inorder.begin() + n + 1;
        vector<int> newinorder(first, last);
        printPostOrder(newpreorder, newinorder, n - node_index - 1);
    }
    cout << preorder[0];
    if (preorder[0] == first_node)
        cout << endl;
    else
        cout << " ";
}

int main(int argc, const char * argv[]) {
    int T, temp;
    vector<int> preorder, inorder;
    cin >> T;
    for (int test = 0; test < T; test++) {
        cin >> N;
        preorder.clear();
        inorder.clear();
        for (int in = 0; in < N; in++) {
            cin >> temp;
            preorder.push_back(temp);
        }
        for (int in = 0; in < N; in++) {
            cin >> temp;
            inorder.push_back(temp);
        }
        first_node = preorder[0];
        printPostOrder(preorder, inorder, N);
    }
}

/*
2
8
1 2 4 7 3 5 6 8
4 7 2 1 5 3 8 6
5
1 2 4 5 3
4 2 5 1 3
*/
