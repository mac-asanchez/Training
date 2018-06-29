package AppCode;

public class Animal {

    public Animal(Constants.AnimalSpecies species, int Energy) {
        Species = species;
        EnergyLevel = Energy;
    }

    public Constants.AnimalSpecies Species;
    public int EnergyLevel;

    public String getSpecies() {
        String SpeciesDescription = "";
        if (Species == Constants.AnimalSpecies.MONKEY){
            SpeciesDescription = "Monkey";
        }
        else if (Species == Constants.AnimalSpecies.TIGER){
            SpeciesDescription = "Tiger";
        }
        else if (Species == Constants.AnimalSpecies.SNAKE){
            SpeciesDescription = "Snake";
        }

        return SpeciesDescription;
    }

    public void setSpecies(Constants.AnimalSpecies species) {
        Species = species;
    }

    public int getEnergyLevel() {
        return EnergyLevel;
    }

    public void setEnergyLevel(int energyLevel) {
        EnergyLevel = energyLevel;
    }

    public String MakeSound() {
        EnergyLevel -= 3;
        return "";
    }

    public String EatFood(Constants.FoodType type) {
        EnergyLevel += 5;
        return "Finished Eating";
    }

    public void Sleep() {
        EnergyLevel += 10;
    }

    public int CheckExistence(){
        int Result = Jungle.CheckExistence(Species);

        return  Result;
    }
}
