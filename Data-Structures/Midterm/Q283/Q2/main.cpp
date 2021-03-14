//
//  main.cpp
//  Q2 - Midterm
//
//  Created by Aarnav Gupta on 13/03/21.
//

#include <iostream>
#include <string>
#include <vector>
using namespace std;

string rev_string(string x) {
    string s = "";
    int len = x.length();
    for (int i = len - 1; i >= 0; i--)
        s += x[i];
    return s;
}

int main() {
    vector<string> all_input;
    string temp;
    while(true) {
        getline(cin, temp);
        all_input.push_back(temp);
        if (temp == "0+0=0")
            break;
    }
    for (string turing_eq: all_input) {
        string A = "", B = "", C = "";
        int in_plus = 0;
        for (int i = 0; i < turing_eq.length(); i++) {
            if (turing_eq[i] < '0' or turing_eq[i] > '9') {
                in_plus = i;
                break;
            }
            A += turing_eq[i];
        }
        for (int i = in_plus+1; i < turing_eq.length(); i++) {
            if (turing_eq[i] < '0' or turing_eq[i] > '9') {
                in_plus = i;
                break;
            }
            B += turing_eq[i];
        }
        for (int i = in_plus+1; i < turing_eq.length(); i++)
            C += turing_eq[i];
        string a = rev_string(A);
        string b = rev_string(B);
        string c = rev_string(C);
        int rev_a = stoi(a);
        int rev_b = stoi(b);
        int rev_c = stoi(c);
        if (rev_a + rev_b == rev_c)
            cout << "True\n";
        else
            cout << "False\n";
    }
}

/*
73+42=16
5+8=13
10+20=30
0001000+000200=00030
1234+5=1239
1+0=0
7000+8000=51
0+0=0
 */

//#include <iostream>
//#include <string>
//#include <vector>
//using namespace std;
//
//int reverse(int num) {
//    int remainder, reversedNum = 0;
//    while (num != 0) {
//        remainder = num%10;
//        reversedNum = (reversedNum*10) + remainder;
//        num /= 10;
//    }
//    return reversedNum;
//}
//
//int main() {
//    vector<string> all_input;
//    string temp;
//    while(true) {
//        temp = "";
//        getline(cin, temp);
//        all_input.push_back(temp);
//        if (temp == "0+0=0")
//            break;
//    }
//    for (string turing_eq: all_input) {
//        string A = "", B = "", C = "";
//        int in_plus = 0;
//        for (int i = 0; i < turing_eq.length(); i++) {
//            if (turing_eq[i] < '0' or turing_eq[i] > '9') {
//                in_plus = i;
//                break;
//            }
//            A += turing_eq[i];
//        }
//        for (int i = in_plus+1; i < turing_eq.length(); i++) {
//            if (turing_eq[i] < '0' or turing_eq[i] > '9') {
//                in_plus = i;
//                break;
//            }
//            B += turing_eq[i];
//        }
//        for (int i = in_plus+1; i < turing_eq.length(); i++) {
//            C += turing_eq[i];
//        }
//        reverse(A.begin(), A.end());
//        reverse(B.begin(), B.end());
//        reverse(C.begin(), C.end());
//        int a = stoi(A);
//        int b = stoi(B);
//        int c = stoi(C);
//        if (a + b == c)
//            cout << "True\n";
//        else
//            cout << "False\n";
//    }
//}
