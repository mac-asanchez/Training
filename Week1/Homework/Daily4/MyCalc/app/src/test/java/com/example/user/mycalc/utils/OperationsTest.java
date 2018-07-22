package com.example.user.mycalc.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class OperationsTest {

    @Test
    public void eval() {
        String input = "1+1";
        double result = Operations.eval(input);
        System.out.println("input: " + input + " = " + result);
    }
}