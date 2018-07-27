package com.example.user.spiralprint;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpiralTest {

    @Test
    public void print() {
        StringBuilder sb = new StringBuilder();
        int[][] input = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};

        //region Print Input
        for (int i = 0; i < input[1].length; i++) {
            sb.append("{");
            for (int j = 0; j < input[0].length; j++) {
                sb.append(String.valueOf(input[i][j]));

                if (j + 1 != input[0].length)
                    sb.append(",");
            }
            sb.append("}, \n");
        }

        System.out.println("input: ");
        System.out.println(sb.toString());
        //endregion

        //region Result
        System.out.println("\nSpiral: ");
        System.out.println(Spiral.Print(input));
        //endregion
    }
}