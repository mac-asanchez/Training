package com.example.user.infectedroom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.Arrays;

import Code.Room;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Infection";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Room[][] verticalTrue = VerticalTrue();
        Room[][] horizontalTrue = HorizontalTrue();
        Room[][] noInfection = NoInfection();
        Room[][] ExampleInfection = Example();

        Log.d(TAG, "Example: " + isOutbreak(ExampleInfection));
        Log.d(TAG, "verticalTrue: " + isOutbreak(verticalTrue));
        Log.d(TAG, "horizontalTrue: " + isOutbreak(horizontalTrue));
        Log.d(TAG, "noInfection: " + isOutbreak(noInfection));
    }

    public static boolean isOutbreak(Room[][] floor) {
        if (floor == null || floor.length <= 0 || floor[0].length == 0)
            return false;

        boolean Result = false;
        int ConectedCount = 0;

        //region Print
        for (int i = 0; i < floor.length; i++) {
            String Print = "|";
            for (int j = 0; j < floor[i].length; j++) {
                if (floor[i][j].isInfected){
                    Print+= "X";
                }
                else{
                    Print+= "_";
                }
                Print+= "|";
            }

            Log.d(TAG, Print);
        }
        //endregion

        //region Process
        for (int row = 0; row < floor.length; row++) {
            for (int column = 0; column < floor[row].length; column++) {
                //region Validates end of cycle
                if(ConectedCount >= 5){
                    return true;
                }
                //endregion

                //region If is not infected
                if (!floor[row][column].isInfected) {
                    ConectedCount = 0;
                    continue;
                }
                //endregion

                ConectedCount++;

                //region Horizontal Next
                if (column + 1 < floor[row].length) {
                    if (floor[row][column + 1].isInfected) {
                        continue;
                    }
                }
                //endregion

                //region Vertical Next
                if (row + 1 < floor.length) {
                    if (floor[row + 1][column].isInfected) {
                        ConectedCount++;
                        row++;
                        column--;
                    }
                }
                //endregion
            }
        }
        //endregion

        if (ConectedCount >= 5) {
            Result = true;
        }

        return Result;
    }

    public Room[][] VerticalTrue() {
        return new Room[][]{
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(true), new Room(false), new Room(true), new
                        Room(true), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(true), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(true), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(true), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(true), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)}
        };
    }

    public Room[][] HorizontalTrue() {
        return new Room[][]{
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(true), new Room(true), new Room(true), new
                        Room(true), new Room(true), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)}
        };
    }

    private Room[][] NoInfection() {
        return new Room[][]{
                {new Room(true), new Room(false), new Room(true), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(true), new Room(false), new Room(true), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(true), new Room(false), new Room(true), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(true), new Room(false), new Room(true), new
                        Room(false), new Room(true), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(true), new Room(false), new Room(false), new
                        Room(true), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(true), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(true), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false), new Room(false), new Room(false), new Room(false), new
                        Room(false)}
        };
    }

    private Room[][] Example() {
        return new Room[][]{
                {new Room(true), new Room(true), new Room(true), new Room(false), new Room(false),
                        new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)},
                {new Room(false), new Room(false), new Room(true), new Room(true), new Room(false),
                        new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false),
                        new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false),
                        new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false),
                        new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false),
                        new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false),
                        new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false),
                        new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false),
                        new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)},
                {new Room(false), new Room(false), new Room(false), new Room(false), new Room(false),
                        new Room(false), new Room(false), new Room(false), new Room(false), new Room(false)}
        };
    }
}
