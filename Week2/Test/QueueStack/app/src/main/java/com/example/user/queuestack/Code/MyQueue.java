package com.example.user.queuestack.Code;

import java.util.Stack;

public class MyQueue {
    Stack<Integer> temp = new Stack<Integer>();
    Stack<Integer> value = new Stack<Integer>();

    // Push element x to the back of queue.
    public void Enqueue(int x) {
        if(value.isEmpty()){
            value.push(x);
        }else{
            while(!value.isEmpty()){
                temp.push(value.pop());
            }

            value.push(x);

            while(!temp.isEmpty()){
                value.push(temp.pop());
            }
        }
    }

    // Removes the element from in front of queue.
    public void Dequeue() {
        value.pop();
    }

    // Get the front element.
    public int peek() {
        return value.peek();
    }

    // Return whether the queue is empty.
    public boolean empty() {
        return value.isEmpty();
    }
}
