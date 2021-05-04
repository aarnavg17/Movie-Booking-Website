//
//  main.cpp
//  Q821
//
//  Created by Aarnav Gupta on 29/04/21.
//

#include <algorithm>
#include <iostream>
#include <set>
#include <vector>
using namespace std;

const long long int inf=1000000000000000000;

long long int gcd(long long int a, long long int b)
{
    if (b == 0)
        return a;
    return gcd(b, a % b);
      
}
long long int power(long long int x, unsigned long long int y, long long int p)
{
    long long int res = 1;
    x = x % p;
    if (x == 0)
        return 0;
    while (y > 0) {
        if (y & 1)
            res = (res*x) % p;
        y = y >> 1;
        x = (x*x) % p;
    }
    return res;
}
long long int _pow(long long int a, long long int b)
{
    if(not b)
        return 1;
    long long int temp = _pow(a, b / 2);
    temp = (temp * temp);
    if(b % 2)
        return (a * temp);
    return temp;
}
long long int modInverse(long long int a,long long int m)
{
    return power(a, m - 2, m);
}
long long int cl(long long int a,long long int x)
{
    if(a % x == 0)
        return a / x;
    else
        return a / x + 1;
}
vector<pair<long long int,long long int>> g[100001];
vector <long long int> vis(100001);
long long int ans = -inf;
long long int dfs(long long int v)
{
    vis[v] = 1;
    multiset<long long int> s;
    for(long long int i = 0; i < g[v].size(); i++)
    {
        if(vis[g[v][i].first] == 1)
            continue;
        long long int x = g[v][i].second + dfs(g[v][i].first);
        s.insert(x);
    }
    if(s.size() == 0)
        return 0;
    if(s.size() == 1) {
        auto it = s.end();
        it--;
        return *it;
    }
    auto it = s.end();
    it--;
    auto it2 = it;
    it2--;
    long long int x = *it + *it2;
    ans = max(ans, x);
    return *it;
}
int main() {
    long long int n, i;
    cin >> n;
    for(i = 1; i <= n; i++) {
        long long int u, v, w;
        cin >> u >> v >> w;
        g[v].push_back(make_pair(u,w));
        g[u].push_back(make_pair(v,w));
    }
    ans = max(ans, dfs(1));
    cout << ans << endl;
}


/*
6
1 2 1
1 3 2
2 4 3
4 5 1
3 6 2
 */
