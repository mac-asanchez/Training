package com.example.user.testingapp;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CalculationTest {
    private static final String TAG = CalculationTest.class.getSimpleName() + "_TAG";
    Calculation calculation;
    int a, b;

    @Mock
    Addition addition;
    @Mock
    Multiplication multiplication;

    @BeforeClass
    public static void setUpOnce() {
        //runs one time
        System.out.println("CalculationTest.setUpOnce");
    }

    @AfterClass
    public static void tearDownOnce() {
        System.out.println("CalculationTest.tearDownOnce");
    }

    @Before
    public void setUp() throws Exception {
        //runs for each method in the class
        System.out.println("CalculationTest.setUp");

        //initialize mocked dependencies
        MockitoAnnotations.initMocks(this);

        //initialize variables
        a = 2;
        b = 3;

        calculation = new Calculation(addition, multiplication);
    }

    @After
    public void tearDown() throws Exception {
        //runs for each method in the class
        System.out.println("CalculationTest.tearDown");
        calculation = null;
    }

    @Test
    public void add() {
        System.out.println("CalculationTest.add");
        assertEquals(5, calculation.add(2, 3));
    }

    @Test
    public void add2() {
        System.out.println("CalculationTest.add2");
//        assertEquals(5, calculation.add(3, 3));
    }

    @Test
    public void addTen() {
        System.out.println("CalculationTest.addTen");
        //mock that return value of the function
        when(addition.add(a, b)).thenReturn(10);

        assertEquals(20, calculation.addTen(a, b));
    }

    @Test
    public void multiply() {
        System.out.println("CalculationTest.multiply");


        //mock that return value of the function
        when(multiplication.multiply(a, b)).thenReturn(10);

        assertEquals(100, calculation.multiply(a, b));
    }

    @Test
    public void doSomethign(){
        System.out.println("CalculationTest.doSomethign");
        calculation.doSomething();
        verify(multiplication).doSomething();
    }
}