//
//  main.cpp
//  Q814
//
//  Created by Aarnav Gupta on 07/03/21.
//

#include <iostream>
using namespace std;

int main() {
    int n, m, q, r1, c1, r2, c2, h, w, temp;
    while (cin >> n >> m >> q) {
        int rect[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cin >> rect[i][j];
            }
        }
        for (int i = 0; i < q; i++) {
            cin >> r1 >> c1 >> r2 >> c2 >> h >> w;
            for (int i = r1-1, x = r2-1; i < r1+h-1; i++, x++) {
                for (int j = c1-1, y = c2-1; j < c1+w-1; j++, y++) {
                    temp = rect[i][j];
                    rect[i][j] = rect[x][y];
                    rect[x][y] = temp;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                cout << rect[i][j];
                if (j != m-1) {
                    cout << " ";
                }
            }
            cout << endl;
        }
    }
}

/*
4 4 2
1 1 2 2
1 1 2 2
3 3 4 4
3 3 4 4
1 1 3 3 2 2
3 1 1 3 2 2
2 2 1
1 2
3 4
1 1 2 2 1 1
*/
