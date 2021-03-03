//
//  main.cpp
//  Q835
//
//  Created by Aarnav Gupta on 23/02/21.
//

#include <iostream>
using namespace std;

long long int cir_eq(long long int a, long long int b, long long int x, long long int y){
    return ((a-x)*(a-x))+((b-y)*(b-y));
}

struct circle {
    long long int x;
    long long int y;
    long long int r;
};

int main() {
    long long int n, q;
    cin >> n;
    circle cir[n];
    for (long long int i = 0; i < n; i++) {
        cin >> cir[i].x >> cir[i].y >> cir[i].r;
    }
    cin >> q;
    long long int a[q], b[q], c[q], d[q];
    for (long long int i = 0; i < q; i++) {
        cin >> a[i] >> b[i] >> c[i] >> d[i];
    }
    long long int cnt = 0;
    for (long long int i = 0; i < q; i++) {
        for (long long int j = 0; j < n; j++) {
            if((cir_eq(a[i], b[i], cir[j].x, cir[j].y) < (cir[j].r*cir[j].r) && cir_eq(c[i], d[i], cir[j].x, cir[j].y) > (cir[j].r*cir[j].r)) || (cir_eq(a[i], b[i], cir[j].x, cir[j].y) > (cir[j].r*cir[j].r) && cir_eq(c[i], d[i], cir[j].x, cir[j].y) < (cir[j].r*cir[j].r))) {
                cnt++;
            }
        }
        cout << cnt << endl;
        cnt = 0;
    }
}
