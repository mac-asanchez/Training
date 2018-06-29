package AppCode;

public class Tiger extends Animal {

    public Tiger(Constants.AnimalSpecies species, int Energy) {
        super(species, Energy);
    }

    public Tiger(Animal a){
        super(a.Species, a.EnergyLevel);
    }

    @Override
    public String MakeSound() {
        return "ROAR";
    }

    @Override
    public void Sleep() {
        //super(Energy);
        EnergyLevel += 5;
    }

    @Override
    public String EatFood(Constants.FoodType type) {
        if (type == Constants.FoodType.GRAIN) {
            return "A tiger can't eat grains!";
        }
        return super.EatFood(type);
    }
}
