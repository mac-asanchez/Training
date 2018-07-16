package com.example.user.assessment.Assessment2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SubstringsAlgorithm {
    public static String Process(String input){
        StringBuilder Result = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j < input.length() + 1; j++) {
                String item = input.substring(i, j);
                Result.append(item + "\n");
            }
        }

        return Result.toString();
    }

    public static String ProcessWithSkyp(String input){
        List<String> Result = new ArrayList<>();

        for (int i = 0; i < input.length(); i++) {
            for (int j = i + 1; j < input.length() + 1; j++) {
                String item = input.substring(i, j);
                Result.add(item);
                Result.addAll(ItemWithSkips(item, j));
            }
        }

        return "";
    }

    private static Collection<? extends String> ItemWithSkips(String item, int Count) {
        List<String> Result = new ArrayList<>();

        for (int i = 1; i < Count; i++) {
            //String itemSkip = item.
        }

        return Result;
    }
}
