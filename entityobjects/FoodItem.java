package edu.umiami.caneeats.entityobjects;

import java.util.List;

public class FoodItem {
    private int id;
    private String itemName;
    private String itemDescription;
    private double itemPrice;
    private List<String> allergens;
    private int Stocks;

    public int getItemID(){return id;}

    public void setItemID(int id){this.id = id;}

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public List<String> getAllergens() {
        return allergens;
    }

    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
    }

    public int getItemStock(){return Stocks;}

    public void setItemStock(int Stocks){ this.Stocks = Stocks;}

    @Override
    public String toString() {
        return "FoodItem{" +
                "ID='" + id + '\'' +
                "itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemPrice=" + itemPrice + '\'' +
                ", stock=" + Stocks +
                '}';
    }

    void printFoodItem()
    {
        System.out.println(this);
        if(allergens !=null && !allergens.isEmpty()) {
            System.out.print("Warning. This food contains: ");
            for (String s : allergens) {
                System.out.println(s + ",");
            }
        }

    }
}
