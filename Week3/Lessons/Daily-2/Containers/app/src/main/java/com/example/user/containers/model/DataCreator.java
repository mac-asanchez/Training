package com.example.user.containers.model;

import java.util.ArrayList;
import java.util.List;

public class DataCreator {

    public static List<String> getSimpleStrings(int Items){
        List<String> strings = new ArrayList<>();
        for (int i = 1; i <= Items; i++) {
            strings.add("String " + String.valueOf(i));
        }

        return strings;
    }

    public static List<Book> getBookList(int Items) {
        List<Book> bookList = new ArrayList<>();

        for (int i = 1; i <= Items; i++) {
            bookList.add(new Book("BOOK-" + i, "Book " + i + " Chronicles", "John Doe"));
        }

        return  bookList;
    }
}
