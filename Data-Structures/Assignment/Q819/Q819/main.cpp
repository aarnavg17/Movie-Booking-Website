//
//  main.cpp
//  Q819
//
//  Created by Aarnav Gupta on 11/03/21.
//

#include <iostream>
using namespace std;

class Node {
public:
    int data;
    Node* next;
    Node* prv;
    Node() {
        prv = nullptr;
        next = nullptr;
        data = 0;
    }
    Node (int data, Node* next, Node* prv) {
        this -> data = data;
        this -> next = next;
        this -> prv = prv;
    }
};

class CircularLinkedList {

public:
    Node* head;
    int len;
    CircularLinkedList() {
        head = nullptr;
        len = 0;
    }

    void append(int data) {
        if (head != nullptr) {
            Node* newNode = new Node(data, head, head -> prv);
            newNode -> prv -> next = newNode;
            head -> prv = newNode;
        }
        else {
            head = new Node(data, head, head);
            head -> next = head;
            head -> prv = head;
        }
        len++;
    }

    int length() {
        return len;
    }

    void deleting(int pos) {
        Node* x = head;
        if (pos == 0) {
            head -> prv -> next = head -> next;
            head -> next -> prv = head -> prv;
            head = head -> next;
        }
        else {
            for (int i = 0; i < pos; i++)
                x = x -> next;
            x -> prv -> next = x -> next;
            x -> next -> prv = x -> prv;
        }
        delete(x);
        len--;
    }

    void delete_data(int d) {
        Node* x = head; int pos = 0;
        while (x -> data != d) {
            x = x -> next;
            pos++;
        }
        deleting(pos);
    }
};

int main(int argc, const char * argv[]) {
    int n, m, presentation_error = 0;
    cin >> n >> m;
    CircularLinkedList nums;
    
    for (int i = 1; i <= n; i++)
        nums.append(i);
    Node* x = nums.head -> prv;
    
    while (nums.length() != 0) {
        for (int i = 0; i < m; i++)
            x = x -> next;
        
        cout << x -> data;
        
        presentation_error++;
        if (presentation_error != n)
            cout << " ";
        else
            cout << endl;
        
        nums.delete_data(x -> data);
    }
}
