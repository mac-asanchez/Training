package com.example.user.queuestack.Code;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyQueueTest {

    @Test
    public void enqueue() {
        MyQueue queue = new MyQueue();
        queue.Enqueue(1);
        queue.Enqueue(2);
        queue.Enqueue(3);
        queue.Enqueue(4);

        queue.Dequeue();
        queue.Dequeue();
    }

    @Test
    public void dequeue() {
    }
}