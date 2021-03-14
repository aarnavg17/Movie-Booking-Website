//
//  main.cpp
//  Q1 - Midterm
//
//  Created by Aarnav Gupta on 13/03/21.
//

#include <iostream>
#include <vector>

using namespace std;

int main(){
    int t;
    cin >> t;
    while(t) {
        t--;
        int n, m, max_sum = 0, curr_sum = 0, cnt, all_index[100000] = {0};
        cin >> n >> m;
        vector<int> m_index;
        
        for (int i = 0; i < n; i++) {
            cin >> all_index[i];
            if (all_index[i] == m)
                m_index.push_back(i);
        }
        
        for (int i = 0; i < m_index.size(); i++) {
            curr_sum = 0;
            if (m_index[i]) {
                if (all_index[m_index[i] - 1] <= m) {
                    cnt = 0;
                    while (all_index[cnt + m_index[i]] > m) {
                        curr_sum += all_index[m_index[i] + cnt];
                        cnt++;
                    }
                }
                else {
                    cnt = 1;
                    while (all_index[m_index[i] - cnt] > m and m_index[i] - cnt >= 0) {
                        curr_sum += all_index[m_index[i] - cnt];
                        cnt++;
                    }
                    curr_sum += m;
                    cnt = 1;
                    while (all_index[m_index[i] + cnt] > m and m_index[i] + cnt < n) {
                        curr_sum += all_index[m_index[i] + cnt];
                        cnt++;
                    }
                }
            }
            else {
                curr_sum += m;
                cnt = 1;
                while (all_index[m_index[i] + cnt] > m and m_index[i] + cnt < n) {
                    curr_sum += all_index[m_index[i] + cnt];
                    cnt++;
                }
            }
            if (curr_sum > max_sum)
                max_sum = curr_sum;
        }
        cout << max_sum << endl;
    }
}
