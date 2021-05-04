//
//  main.cpp
//  Q822
//
//  Created by Aarnav Gupta on 28/04/21.
//

#include<iostream>
#include<set>
#include<vector>
#include<algorithm>

using namespace std;

vector<long long int> g[100001], vis(100001), w(100001);
long long int ans = 1000000000000000000;
long long int num;

void dfs(long long int v, long long int dis) {
    vis[v] = 1;
    for(long long int i=0;i<g[v].size();i++) {
        if(vis[g[v][i]] == 1) continue;
        dfs(g[v][i], dis + 1);
    }
    num += dis * w[v];
}

int main() {
    long long int n, i;
    cin >> n;
    for(i = 1; i <= n; i++) {
        int u, v;
        cin >> w[i] >> u >> v;
        if(v != 0) {
            g[v].push_back(i);
            g[i].push_back(v);
        }
        if(u != 0) {
            g[u].push_back(i);
            g[i].push_back(u);
        }
    }
    for(i = 1; i <= n; i++) {
        for(long long int j = 1; j <= n; j++) {
            vis[j] = 0;
        }
        num = 0;
        dfs(i, 0);
        ans = min(ans, num);
    }
    cout << ans << endl;
}

/*
5
13 2 3
4 0 0
12 4 5
20 0 0
40 0 0
*/
