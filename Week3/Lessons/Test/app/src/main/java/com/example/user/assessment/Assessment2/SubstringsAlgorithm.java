package com.example.user.assessment.Assessment2;

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
}
