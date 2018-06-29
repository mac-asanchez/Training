package AppCode;

public class Food {
    public Constants.FoodType Type;
    public int Quantity;

    public int getQuatity() {
        return Quantity;
    }

    public void setQuatity(int quatity) {
        Quantity = quatity;
    }

    public String getType() {
        String FoodTypeDescription = "";
        if (Type == Constants.FoodType.BUGS){
            FoodTypeDescription = "Bugs";
        }
        else if (Type == Constants.FoodType.FISH){
            FoodTypeDescription = "Fish";
        }
        else if (Type == Constants.FoodType.GRAIN){
            FoodTypeDescription = "Grain";
        }
        else if (Type == Constants.FoodType.MEAT){
            FoodTypeDescription = "Meat";
        }

        return FoodTypeDescription;
    }

    public void setType(Constants.FoodType type) {
        Type = type;
    }
}
