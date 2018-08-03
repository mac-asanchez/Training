package com.example.user.testingapp;

public class Calculation {

    Addition addition;
    Multiplication multiplication;

    public Calculation(Addition addition, Multiplication multiplication) {
        this.addition = addition;
        this.multiplication = multiplication;
    }

    public int add(int a, int b) {
        return a + b;
    }

    public int addTen(int a, int b) {
        return addition.add(a, b) + 10;
    }

    public int multiply(int a, int b) {
        return multiplication.multiply(a, b) * 10;
    }

    public void doSomething() {
        multiplication.doSomething();
    }
}
