package com.example.user.assessment.Assessment1;

import org.junit.Test;

public class MySortTest {

    @Test
    public void doSelectionSort() {
        int[] MyArray = {2,8,9,3,4,3,2,6,6,2,4,9,8};
        int[] MyResult = MySort.doSelectionSort(MyArray);
        for(int item: MyResult) {
            System.out.print(item);
            System.out.print(", ");
        }
    }
}