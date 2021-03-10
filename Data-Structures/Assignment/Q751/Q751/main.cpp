//
//  main.cpp
//  Q751
//
//  Created by Aarnav Gupta on 11/03/21.
//

#include <iostream>
#include <string>
#include <stack>

int main() {
    std::string expression;
    while(getline(std::cin, expression)) {
        std::stack<int> exp;
        for (char sym : expression) {
            if (sym == ' ') continue;
            if (sym == '\n' or sym == '\0') break;
            else if (sym >= '0' and sym <= '9') exp.push((int)sym - '0');
            else {
                int a, b;
                a = exp.top();
                exp.pop();
                b = exp.top();
                exp.pop();
                switch(sym) {
                    case '+':
                        exp.push(b+a);
                        break;
                    case '-':
                        exp.push(b-a);
                        break;
                    case '*':
                        exp.push(b*a);
                        break;
                }
            }
        }
        std::cout << exp.top() << std::endl;
    }
}

/*
5 1 2 + 4 * + 3 –
9 8 7 9 3 - * + +
*/
