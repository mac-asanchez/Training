package com.example.user.assessment.Assessment4;

import org.junit.Test;

import static org.junit.Assert.*;

public class CopyCheckerTest {

    @Test
    public void check() {
        String input = "catcowcat";
        String valueToFind = "cat";
        int OccurrencesTry = 2;
        boolean result = CopyChecker.Check(input, valueToFind, OccurrencesTry);
        System.out.println("input: (\"" + input + "\", \"" + valueToFind + "\", " + OccurrencesTry + "). Result: " + result);

        input = "catcowcat";
        valueToFind = "cow";
        OccurrencesTry = 2;
        result = CopyChecker.Check(input, valueToFind, OccurrencesTry);
        System.out.println("input: (\"" + input + "\", \"" + valueToFind + "\", " + OccurrencesTry + "). Result: " + result);

        input = "catcowcat";
        valueToFind = "cow";
        OccurrencesTry = 1;
        result = CopyChecker.Check(input, valueToFind, OccurrencesTry);
        System.out.println("input: (\"" + input + "\", \"" + valueToFind + "\", " + OccurrencesTry + "). Result: " + result);
    }
}