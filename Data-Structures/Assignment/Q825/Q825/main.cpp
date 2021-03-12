//
//  main.cpp
//  Q825
//
//  Created by Aarnav Gupta on 09/03/21.
//

/*
4
1 4 1 2 2 3
3 2 2 3
86401 2 3 4
86402 1 5
*/

/*
3
1 4 4 1 2 2
2 2 2 3
10 1 3
*/

#include <iostream>
#include <queue>
#include <vector>
#include <unordered_map>

using namespace std;

struct Ship {
    long int seconds;
    long int num_passengers;
    vector <long int> nationalities;
};

int main() {
    long int n, temp = 0, num_unique_nationalities = 0;
    queue <Ship> ship_objects;
    Ship s;
    unordered_map<long int, int> unique_nationalities;
    cin >> n;
    for (int t = 0; t < n; t++) {
        s.nationalities.clear();
        cin >> temp;
        s.seconds = temp;
        cin >> temp;
        s.num_passengers = temp;
        
        for (int num = 0; num < s.num_passengers; num++) {
            cin >> temp;
            s.nationalities.push_back(temp);
            if (unique_nationalities.find(temp) == unique_nationalities.end()) {
                unique_nationalities.insert({temp, 1});
                num_unique_nationalities++;
            }
            else if (unique_nationalities[temp] == 0){
                unique_nationalities[temp]++;
                num_unique_nationalities++;
            }
            else
                unique_nationalities[temp]++;
        }
        ship_objects.push(s);
        
        while (ship_objects.back().seconds - ship_objects.front().seconds >= 86400) {
            for (int num = 0; num < ship_objects.front().num_passengers; num++) {
                if (unique_nationalities[ship_objects.front().nationalities[num]] == 1)
                    num_unique_nationalities--;
                unique_nationalities[ship_objects.front().nationalities[num]]--;
            }
            ship_objects.pop();
        }
        cout << num_unique_nationalities << endl;
    }
}
