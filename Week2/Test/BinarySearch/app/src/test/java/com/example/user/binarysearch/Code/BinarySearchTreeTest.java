package com.example.user.binarysearch.Code;

import org.junit.Test;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    @Test
    public void find() {
        BinarySearchTree b = new BinarySearchTree();
        b.Add(3);
        b.Add(8);
        b.Add(1);
        b.Add(4);
        b.Add(6);
        b.Add(2);
        b.Add(10);
        b.Add(9);
        b.Add(20);
        b.Add(25);
        b.Add(15);
        b.Add(16);
        System.out.println("Original Tree : ");
        b.display(b.root);
        System.out.println("");
        System.out.println("Check whether Node with value 4 exists : " + b.find(4));
        b.display(b.root);
    }
}