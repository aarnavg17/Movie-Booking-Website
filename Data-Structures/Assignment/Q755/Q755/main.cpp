//
//  main.cpp
//  Q745
//
//  Created by Aarnav Gupta on 11/03/21.
//
//
#include <iostream>
#include <stack>
using namespace std;

int main() {
    long long int T, N;
    cin >> T;
    while (T) {
        T--;
        cin >> N;
        long long int heights[N];
        for (int i = 0; i < N; i++)
            cin >> heights[i];
        stack<long long int> height_index;
        long long int max_area = 0, top, area_from_top, i = 0;
        while (i < N) {
            if (height_index.empty() or heights[height_index.top()] <= heights[i])
                height_index.push(i++);
            else {
                top = height_index.top();
                height_index.pop();
                area_from_top = heights[top] * (height_index.empty() ? i : i - height_index.top() - 1);
                if (max_area < area_from_top)
                    max_area = area_from_top;
            }
        }
        while (!height_index.empty()) {
            top = height_index.top();
            height_index.pop();
            area_from_top = heights[top] * (height_index.empty() ? i : i - height_index.top() - 1);
            if (max_area < area_from_top)
                max_area = area_from_top;
        }
        cout << max_area << endl;
    }
}

/*
2
6
3 4 5 2 3 8
7
6 2 5 4 5 1 6
 */
