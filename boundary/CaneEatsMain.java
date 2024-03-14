package edu.umiami.caneeats.boundary;

import edu.umiami.caneeats.entityobjects.User;

import java.util.Scanner;

public class CaneEatsMain {
    public static void main(String[] args) {
        System.out.println("Welcome to Cane Eats Application");
        System.out.println();
        System.out.println();
        System.out.println("What do you want to do ?");
        System.out.println("1. Search Restaurants");
        System.out.println("2. Modify Restaurants Info");
        System.out.println("3. Create new customer profile");
        System.out.println("4. Retrieve customer info");
        System.out.println("5. Update customer info");
        System.out.println("6. Delete customer");
        System.out.println("7. Place an order");

        System.out.println("0. Exit");
        Scanner mainInput = new Scanner(System.in);
        int choice = mainInput.nextInt();

        switch (choice) {
            case 1:
                new RestaurantScreen().searchRestaurant();
                break;
            case 2:
                new RestaurantScreen().modifyRestaurant();
                break;
            case 3:
                new UserScreen().addUser();
                break;
            case 4:
                new UserScreen().searchUsers();
                break;
            case 5:
                new UserScreen().updateUser();
                break;
            case 6:
                new UserScreen().deleteUser();
                break;
            case 7:
                new OrderScreen().newOrder();
                break;
            default:
                System.out.println("Thank you!!!");

        }

    }
}
