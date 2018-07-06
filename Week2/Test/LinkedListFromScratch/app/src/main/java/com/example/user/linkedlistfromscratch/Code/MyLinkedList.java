package com.example.user.linkedlistfromscratch.Code;

import android.util.Log;

public class MyLinkedList {
    public MyNode head;
    public int listCount;
    private String TAG = "MyLinkedList";

    public MyLinkedList() {
        head = new MyNode("");
        listCount = 0;
    }

    public void Show() {
        MyNode current = head;
        while (current.next != null) {
            System.out.println(TAG + ", " + current.data + " -> ");
            current = current.next;
        }
    }

    public boolean add(String d) {
        MyNode end = new MyNode(d);
        MyNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = end;
        listCount++;
        System.out.println(d + " appended to tail!");
        return true;
    }

    public boolean add(String d, int index) {
        MyNode end = new MyNode(d);
        MyNode current = head;
        int jump;

        if (index > listCount || index < 1) {
            System.out.println("Add Failed: index out of bounds of size of linked list!!");
            return false;
        } else {
            jump = 0;
            while (jump < index - 1) {
                current = current.next;
                jump++;
            }
            end.next = current.next;
            current.next = end;
            listCount++;
            System.out.println("Success! " + d + " added at index " + index);
            return true;
        }
    }

    public boolean deleteMyNodeWithData(String d) {
        MyNode current = head;
        while (current.next != null) {
            if (current.next.data == d) {
                current.next = current.next.next;
                listCount--;
                System.out.println("Success! MyNode with data " + d + " deleted.");
                return true;
            }
            current = current.next;
        }
        System.out.println("Delete Failed: No MyNode found with given data!");
        return false;
    }

    public boolean deleteMyNodeAtIndex(int index) {
        MyNode current = head;
        int jump;
        if (index > listCount || index < 1) {
            System.out.println("Delete Failed: index out of bounds of size of linked list!!");
            return false;
        } else {
            jump = 0;
            while (jump < index - 1) {
                current = current.next;
                jump++;
            }
            current.next = current.next.next;
            System.out.println("Success! MyNode at index " + index + " deleted.");
            listCount--;
            return true;
        }
    }

    public static MyLinkedList fromString(String d) {
        char[] Elements = d.toCharArray();
        MyLinkedList result = new MyLinkedList();

        for (int i = 0; i < Elements.length; i++) {
            String val = String.valueOf(Elements[i]);
            result.add(val);
        }

        return result;
    }
}
