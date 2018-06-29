package AppCode;

public class Snake extends Animal {

    public Snake(Constants.AnimalSpecies species, int Energy) {
        super(species, Energy);
    }

    public Snake(Animal a){
        super(a.Species, a.EnergyLevel);
    }

    @Override
    public String MakeSound() {
        super.MakeSound();
        return "Ssss";
    }
}
