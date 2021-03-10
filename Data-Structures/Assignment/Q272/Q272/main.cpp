//
//  main.cpp
//  Q272
//
//  Created by Aarnav Gupta on 01/02/21.
//

#include <iostream>
#include <stack>
#include <cmath>
#include <stdlib.h>

using namespace std;

struct Point {
    long long int x, y;
};

Point p0;

int if_turn_left(Point p1, Point p2, Point p3) {
    long long int cross_prod = ((p2.x - p1.x)*(p3.y-p1.y))-((p2.y-p1.y)*(p3.x-p1.x));
    if (cross_prod == 0) return 0; //Travelled Stright
    else if (cross_prod > 0) return 1; //Took a right turn - not what we want
    else return 2; //Took a left turn - What we are looking for.
}

int distance(Point p1, Point p2) {
    return pow((p2.x - p1.x),2) + pow((p2.y-p1.y),2);
}

int compare(const void *vp1, const void *vp2) {
    Point *p1 = (Point *) vp1;
    Point *p2 = (Point *) vp2;
 
    long long int orientation = if_turn_left(p0, *p1, *p2);
    if (orientation == 0){
        if (distance(p0, *p2) >= distance(p0, *p1)) return -1;
        return 1;
    }
    if (orientation == 2) return -1;
    return 1;
}

void swap(Point &p1, Point &p2) {
    Point temp = p1;
    p1 = p2;
    p2 = temp;
}

Point secondFromTop(stack<Point> s) {
    Point top_temp = s.top();
    s.pop();
    Point rtn = s.top();
    s.push(top_temp);
    return rtn;
}

bool compare_points(Point p1, Point p2) {
    if (p1.x < p2.x || (p1.x == p2.x && p1.y < p2.y)) return true;
    return false;
}

stack<Point> sort_answer(stack<Point> unordered) {
    int size = (int)unordered.size();
    stack<Point> final_ans;
    stack<Point> temperory;
    stack<Point> temp2;
    stack<Point> temp3;
    stack<Point> almost_final;
    Point low = unordered.top();
    Point tmp;
    for (int i = 0; i < size; i++) {
        tmp = unordered.top();
        if (tmp.x < low.x) {
            low = tmp;
        }
        else if (tmp.x == low.x && tmp.y < low.y) {
            low = tmp;
        }
        temperory.push(tmp);
        unordered.pop();
    }
    tmp = temperory.top();
    while(tmp.x != low.x || tmp.y != low.y) {
        temp2.push(tmp);
        temperory.pop();
        tmp = temperory.top();
    }
    temperory.pop();
    almost_final.push(tmp);
    while(!temp2.empty()) {
        tmp = temp2.top();
        temp2.pop();
        almost_final.push(tmp);
    }
    while(!temperory.empty()) {
        tmp = temperory.top();
        temp3.push(tmp);
        temperory.pop();
    }
    while(!temp3.empty()) {
        tmp = temp3.top();
        almost_final.push(tmp);
        temp3.pop();
    }
    while(!almost_final.empty()) {
        final_ans.push(almost_final.top());
        almost_final.pop();
    }
    return final_ans;
}

void ConvexHull(Point points[], long long int n) {
    long long int left_most = points[0].x;
    long long int minimum = 0;
    for (int i = 1; i < n; i++) {
        if ((points[i].x < left_most) || (points[i].x == left_most && points[i].y < points[minimum].y)) {
            left_most = points[i].x;
            minimum = i;
        }
    }
    
    swap(points[0], points[minimum]);
    
    p0 = points[0];
    qsort(&points[1], n-1, sizeof(Point), compare);
    
    long long int M = 1;
    for (int i = 1; i < n; i++) {
        while (i < n-1 && if_turn_left(p0, points[i], points[i+1]) == 0) i++;
        points[M] = points[i];
        M++;
    }
    if (M < 3) return;
    
    stack<Point> answer;
    answer.push(points[0]);
    answer.push(points[1]);
    answer.push(points[2]);
    
    for (int i = 3; i < M; i++) {
        while (if_turn_left(secondFromTop(answer), answer.top(), points[i]) != 2) answer.pop();
        answer.push(points[i]);
    }

    stack<Point> final_ans = sort_answer(answer);
    //stack<Point> final_ans = answer; //Just for test
    cout << final_ans.size() << endl;
    while(!final_ans.empty()) {
        Point T = final_ans.top();
        cout << T.x << " " << T.y << endl;
        final_ans.pop();
    }
}

int main() {
    long long int T;
    cin >> T;
    long long int N;
    for (int t = 0; t < T; t++) {
        cin >> N;
        Point points[N];
        for (int i = 0; i < N; i++) cin >> points[i].x >> points[i].y;
        ConvexHull(points, N);
    }
    //cout << endl;
    return 0;
}
