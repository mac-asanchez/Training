package AppCode;

public class Monkey extends Animal {

    public Monkey(Constants.AnimalSpecies species, int Energy) {
        super(species, Energy);
    }

    public Monkey(Animal a){
        super(a.Species, a.EnergyLevel);
    }

    @Override
    public String EatFood(Constants.FoodType type) {
        //return super.EatFood(type);
        EnergyLevel += 2;
        return "";
    }

    @Override
    public String MakeSound() {
        return super.MakeSound();
    }

    public String Play() {
        String Response = "";

        if (EnergyLevel >= 8) {
            Response = "Oooo Oooo Oooo";
            EnergyLevel -= 8;
        } else {
            Response = "Monkey is too tired";
        }

        return Response;
    }
}
