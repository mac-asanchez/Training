package com.example.user.spiralprint;


import static com.example.user.spiralprint.Spiral.Direction.Down;
import static com.example.user.spiralprint.Spiral.Direction.Left;
import static com.example.user.spiralprint.Spiral.Direction.Right;
import static com.example.user.spiralprint.Spiral.Direction.Up;

public class Spiral {
    public static String Print(int[][] array) {
        //region Limits
        int horizontalStart = 0;
        int verticalStart = 0;
        int horizontalEnd = array[0].length;
        int verticalEnd = array[2].length;
        //endregion

        //region variables
        Direction direction = Right;
        boolean end = false;
        StringBuilder sb = new StringBuilder();
        int y = 0;
        int x = 0;
        int count = 0;
        int arrayLength = array[0].length * array[1].length;
        //endregion

        //region get Spiral
        while (!end) {
            count++;
            String value = String.valueOf(array[y][x]);
            sb.append(value + ",");

            //region Check end
            if (count == arrayLength) {
                end = true;
                break;
            }
            //endregion

            //region Step
            switch (direction) {
                case Right:
                    x++;
                    break;
                case Left:
                    x--;
                    break;
                case Up:
                    y--;
                    break;
                case Down:
                    y++;
                    break;
            }
            //endregion

            //region Check direction
            if (x == horizontalEnd) {
                direction = Down;
                verticalStart = y;
                x--;
                y++;
            } else if (y == verticalEnd) {
                direction = Left;
                horizontalEnd = x;
                y--;
                x--;
            } else if (x == (horizontalStart - 1) && y > verticalStart) {
                direction = Up;
                verticalEnd = y;
                x++;
                y--;
            } else if (y == verticalStart && x == horizontalStart) {
                direction = Right;
                y++;
                x++;
                horizontalStart = x;
            }
            //endregion
        }
        //endregion

        return sb.toString();
    }


    public enum Direction {
        Right, Left, Up, Down
    }
}
