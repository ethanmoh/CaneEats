package edu.umiami.caneeats.controllers;

import edu.umiami.caneeats.business.RestaurantBC;
import edu.umiami.caneeats.entityobjects.FoodItem;
import edu.umiami.caneeats.entityobjects.Restaurant;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;

public class RestaurantController {

    private RestaurantBC restaurantBC;

    public RestaurantController()
    {
        this.restaurantBC = new RestaurantBC();
    }

    public Set<Restaurant> searchRestaurants(String restName) {
        if(restName!= null && !restName.isBlank())
        {
            return restaurantBC.searchRestaurants(restName);
        }
        else
        {
            return null ;
        }
    }

    public void addMenu(Restaurant r) {
        restaurantBC.addMenu(r);
    }

    public FoodItem getFoodItem(int id){return restaurantBC.getFoodItem(id);}
}
