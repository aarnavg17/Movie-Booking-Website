//
//  main.cpp
//  Q814
//
//  Created by Aarnav Gupta on 27/04/21.
//

#include <iostream>
using namespace std;

int main() {
    int row, col, n;
    while (cin >> row >> col >> n) {
        long long int arr[row][col];
        int A[n], B[n], C[n], D[n], E[n], F[n];
        
        for(int i = 0; i < row; i++) for(int j = 0; j < col; j++) cin >> arr[i][j];
        
        for(int i = 0; i < n; i++) {
            cin >> A[i] >> B[i] >> C[i] >> D[i] >> E[i] >> F[i];
        }
        int a, b;
        for (int i = 1; i <= row; i++) {
            for (int j = 1; j <= col; j++) {
                a = i; b = j;
                for (int x = 0; x < n; x++) {
                    if (C[x] > A[x]) {
                        if (i >= A[x] and i < A[x] + E[x]) {
                            a = i + E[x];
                        }
                        else if (i >= C[x] and i < C[x] + E[x]) {
                            a = i - E[x];
                        }
                        else {
                            a = i;
                        }
                    }
                    else if (A[x] > C[x]) {
                        if (i >= A[x] and i < A[x] + E[x]) {
                            a = i - E[x];
                        }
                        else if (i >= C[x] and i < C[x] + E[x]) {
                            a = i + E[x];
                        }
                        else {
                            a = i;
                        }
                    }
                    else {
                        a = i;
                    }
                    if (D[x] > B[x]) {
                        if (j >= B[x] and j < B[x] + F[x]) {
                            b = j + F[x];
                        }
                        else if (j >= D[x] and j < D[x] + F[x]) {
                            b = j - F[x];
                        }
                        else {
                            b = j;
                        }
                    }
                    else if (B[x] > D[x]) {
                        if (j >= B[x] and j < B[x] + F[x]) {
                            b = j - F[x];
                        }
                        else if (j >= D[x] and j < D[x] + F[x]) {
                            b = j + F[x];
                        }
                        else {
                            b = j;
                        }
                    }
                    else {
                        b = j;
                    }
                }
                cout << arr[a-1][b-1] << " ";
            }
            cout << endl;
        }
    }
    return 0;
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
