package edu.umiami.caneeats.entityobjects;

import java.util.Set;

public class Menu {
    Set<FoodItem> foodItemsSet;

    public Set<FoodItem> getFoodItemsSet() {
        return foodItemsSet;
    }

    public void setFoodItemsSet(Set<FoodItem> foodItemsSet) {
        this.foodItemsSet = foodItemsSet;
    }

    public void printMenu()
    {
        for(FoodItem fi : foodItemsSet){
          fi.printFoodItem();
            System.out.println();
        }
    }

}
