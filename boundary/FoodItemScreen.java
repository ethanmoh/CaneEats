package edu.umiami.caneeats.boundary;

import edu.umiami.caneeats.entityobjects.FoodItem;

import java.util.Scanner;

public class FoodItemScreen
{
    FoodItem inputNewFoodItem()
    {
        FoodItem foodItem = new FoodItem();
        Scanner foodItemScanner = new Scanner(System.in);
        System.out.println("Input the name of Food Item :");
        foodItem.setItemName(foodItemScanner.nextLine());
        System.out.println("Input the description of Food Item :");
        foodItem.setItemDescription(foodItemScanner.nextLine());
        System.out.println("Input the price of Food Item :");
        foodItem.setItemPrice(foodItemScanner.nextDouble());
       return foodItem;
    }
}
