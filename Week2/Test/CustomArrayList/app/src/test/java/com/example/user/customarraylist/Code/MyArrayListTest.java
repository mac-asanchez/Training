package com.example.user.customarraylist.Code;

import org.junit.Test;

import static org.junit.Assert.*;

public class MyArrayListTest {

    @Test
    public void add() {
        MyArrayList<String> myArrayList = new MyArrayList<String>(20);

        for (int i = 0; i < myArrayList.size() + 1; i++) {
            String element = "My Element: " + String.valueOf(i);
            myArrayList.add(element);
        }
    }
}