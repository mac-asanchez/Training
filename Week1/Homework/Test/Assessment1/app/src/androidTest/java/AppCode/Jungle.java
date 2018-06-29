package AppCode;

import android.util.Log;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Jungle {
    private static final String TAG = "Jungle";
    public static List<Food> CurrentFood;
    public static List<Animal> Animals;

    public static List<Food> getCurrentFood() {
        return CurrentFood;
    }

    public static void setCurrentFood(List<Food> currentFood) {
        CurrentFood = currentFood;
    }

    public static List<Animal> getAnimals() {
        return Animals;
    }

    public static void setAnimals(List<Animal> animals) {
        Animals = animals;
    }

    public static int CheckExistence(Constants.AnimalSpecies Species) {
        int Result = 0;

        for (Animal a : Animals) {
            if (a.Species == Species) {
                Result++;
            }
        }

        return Result;
    }

    public static List<String> SoundOff() {
        List<String> EnergyReport = new ArrayList<String>();
        for (Animal a : Animals) {
            a.MakeSound();
            String Report = "Animal: " + a.getSpecies() + ". Energy Level: " + a.getEnergyLevel();
            EnergyReport.add(Report);
        }

        return  EnergyReport;
    }

    public static void RandomActivity(){
        Random r = new Random();
        int min = 1;
        int max = 3;
        int RandomInt = 0;

        for (Animal a : Animals) {
            if (a.Species == Constants.AnimalSpecies.MONKEY){
                max = 4;
            }

            RandomInt = r.nextInt(max - min + 1) + min;

            switch (RandomInt){
                case 1:
                    a.MakeSound();
                    break;

                case 2:
                    a.EatFood(Constants.FoodType.MEAT);
                    break;

                case 3:
                    a.Sleep();
                    break;
                case 4:
                    Monkey m = (Monkey)a;
                    m.Play();
                    break;
            }
        }
    }

    @Test
    public void TestJungle() {
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal(Constants.AnimalSpecies.TIGER, 20));
        animals.add(new Animal(Constants.AnimalSpecies.MONKEY, 20));
        animals.add(new Animal(Constants.AnimalSpecies.SNAKE, 20));

        Tiger t = new Tiger(animals.get(0));
        String Result = t.EatFood(Constants.FoodType.GRAIN);
        Log.d(TAG, "TestJungle: " + Result);
    }
}
