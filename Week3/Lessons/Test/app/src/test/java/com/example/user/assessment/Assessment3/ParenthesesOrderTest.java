package com.example.user.assessment.Assessment3;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParenthesesOrderTest {

    @Test
    public void check() {
        String input = "({})[]";
        Boolean Result = ParenthesesOrder.Check(input);
        System.out.println("input: " + input + ". Result: " + Result);


        input = "[]{]}[";
        Result = ParenthesesOrder.Check(input);
        System.out.println("input: " + input + ". Result: " + Result);
    }
}