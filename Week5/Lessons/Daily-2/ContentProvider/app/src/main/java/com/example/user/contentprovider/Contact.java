package com.example.user.contentprovider;

import java.util.List;

public class Contact {
    String name;
    List<String> numbers;

    public Contact(String name, List<String> numbers) {
        this.name = name;
        this.numbers = numbers;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", numbers=" + numbers +
                '}';
    }

    public String getName() {
        return name;
    }

    public List<String> getNumbers() {
        return numbers;
    }
}
