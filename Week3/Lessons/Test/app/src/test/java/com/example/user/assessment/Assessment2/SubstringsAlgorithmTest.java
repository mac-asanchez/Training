package com.example.user.assessment.Assessment2;

import org.junit.Test;

import static org.junit.Assert.*;

public class SubstringsAlgorithmTest {

    @Test
    public void process() {
        String input = "abcd";
        String Result = SubstringsAlgorithm.Process(input);
        System.out.println("Input: " + input);
        System.out.println(Result);
    }
}