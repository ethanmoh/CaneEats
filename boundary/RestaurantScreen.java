package edu.umiami.caneeats.boundary;

import edu.umiami.caneeats.controllers.RestaurantController;
import edu.umiami.caneeats.entityobjects.FoodItem;
import edu.umiami.caneeats.entityobjects.Menu;
import edu.umiami.caneeats.entityobjects.Restaurant;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class RestaurantScreen {

    public RestaurantController restaurantController;

    RestaurantScreen() {
        this.restaurantController = new RestaurantController();
    }

    public void searchRestaurant() {
        Scanner restNameScanner = new Scanner(System.in);
        System.out.println("Search Restaurant Screen");
        System.out.print("What is the name of the restaurant : ");
        String restName = restNameScanner.nextLine();


        Set<Restaurant> restaurantList = restaurantController.searchRestaurants(restName);

        System.out.println("The list of restaurants that match your query is :");

        if (restaurantList == null || restaurantList.isEmpty()) {
            System.out.println("Sorry no matches found!!");
        } else
            for (Restaurant r : restaurantList)
                r.printRestaurantFull();

    }

    public void searchFoodItem() {
    }

    public void modifyRestaurant() {
        System.out.println("Welcome to Restaurant Modify Screen");
        System.out.println();
        //Display options
        System.out.println("1. Edit Restaurant Info");
        System.out.println("2. Add Restaurant Menu");//  --- For today's class you implement this.
        System.out.println("3. Delete Restaurant");
        System.out.println("4. View Restaurant Info with Menu");

        Scanner choiceScanner = new Scanner(System.in);
        int choice = choiceScanner.nextInt();

        switch(choice)
        {
            case 2: //Adding New Mnu
                System.out.println("To add a new menu to a restaurant you need to select one first");
                System.out.print("What is the name of the restaurant : ");
                Scanner restNameScanner = new Scanner(System.in);
                String restName = restNameScanner.nextLine();
                Set<Restaurant> restaurantList = restaurantController.searchRestaurants(restName);

                for(Restaurant r: restaurantList)
                {
                    r.printRestaurantFull();

                    System.out.println("Do you want to add a menu item to this restaurant ? Press 1 for yes, 0 for done. ");
                    Scanner modifyScanner = new Scanner(System.in);
                    int choiceModify = modifyScanner.nextInt();
                    if(choiceModify == 1)
                    {
                        Menu menu = new Menu();
                        Set<FoodItem> foodItemHashSet = new HashSet<FoodItem>();

                        while(true) {
                            FoodItem foodItem = new FoodItemScreen().inputNewFoodItem();
                            foodItemHashSet.add(foodItem);

                            System.out.println("Do you want to add more items to this menu ? Press 1 for yes. ");
                            int choiceMoreItems = modifyScanner.nextInt();

                            if(choiceMoreItems != 1)
                                break;
                            else
                                continue;

                        }

                        menu.setFoodItemsSet(foodItemHashSet);
                        System.out.println("The new items added are :");
                        menu.printMenu();
                        r.setRestaurantMenu(menu);
                        restaurantController.addMenu(r);

                    }
                    else if (choiceModify == 0)
                        break;
                }


                break;

            default:
                System.out.println("TBD");
        }

    }
}
