//
//  main.cpp
//  Q3 - Midterm
//
//  Created by Aarnav Gupta on 13/03/21.
//

#include <iostream>
#include <stack>
#include <vector>
using namespace std;

int n, m;

int maxValue(vector<int> row) {
    stack<int> answer;
    int stack_top, max_area = 0, curr_area = 0;
    int i = 0;
    while (i < m) {
        if (answer.empty() or row[answer.top()] <= row[i]) {
            answer.push(i);
            i++;
        }
        else {
            stack_top = row[answer.top()];
            answer.pop();
            curr_area = stack_top * i;
            
            if (not answer.empty())
                curr_area = stack_top * (i - answer.top() - 1);
            if (curr_area > max_area)
                max_area = curr_area;
        }
    }
    while (not answer.empty()) {
        stack_top = row[answer.top()];
        answer.pop();
        curr_area = stack_top * i;
        
        if (not answer.empty())
            curr_area = stack_top * (i - answer.top() - 1);
        if (curr_area > max_area)
            max_area = curr_area;
    }
    
    return max_area;
}

int maxSubRect(vector<vector<int>> rect) {
    int answer = maxValue(rect[0]);
    for (int x = 1; x < n; x++) {
        for (int y = 0; y < m; y++) {
            if (rect[x][y])
                rect[x][y] += rect[x-1][y];
        }
        int temp = maxValue(rect[x]);
        if (temp > answer)
            answer = temp;
    }
    return answer;
}

int main() {
    int t, temp;
    vector<int> temp_vector;
    cin >> t;
    while(t) {
        t--;
        cin >> n >> m;
        vector<vector<int>> binary_rect;
        for (int x = 0; x < n; x++) {
            temp_vector.clear();
            for (int y = 0; y < m; y++) {
                cin >> temp;
                temp_vector.push_back(temp);
            }
            binary_rect.push_back(temp_vector);
        }
        cout << maxSubRect(binary_rect) << endl;
    }
}

