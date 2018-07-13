package com.example.user.assessment.Assessment4;

public class CopyChecker {
    public static Boolean Check(String input, String valueToFind, int OccurrencesTry) {
        int Occurrences = 0;
        for (int i = 0; i < input.length() - valueToFind.length() + 1; i++) {
            String item = input.substring(i, valueToFind.length() + i);
            if (item.equals(valueToFind)) {
                Occurrences++;
            }
        }

        return (Occurrences == OccurrencesTry);
    }
}
