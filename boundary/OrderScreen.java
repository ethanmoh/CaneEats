package edu.umiami.caneeats.boundary;

import edu.umiami.caneeats.controllers.OrderController;
import edu.umiami.caneeats.entityobjects.Order;
import edu.umiami.caneeats.entityobjects.FoodItem;
import edu.umiami.caneeats.entityobjects.OrderDetails;
import edu.umiami.caneeats.entityobjects.Restaurant;
import edu.umiami.caneeats.controllers.RestaurantController;

import java.util.*;
import java.util.Set;

public class OrderScreen {
    public RestaurantController restaurantController;

    public OrderController orderController;

    OrderScreen(){
        this.restaurantController = new RestaurantController();
        this.orderController = new OrderController();
    }

    private double calculateOrderTotal(List<OrderDetails> orderItems) {
        double orderTotal = 0;
        for (OrderDetails item : orderItems) {
            // fetch the food item details using the itemId in the orderItem
            FoodItem foodItem = restaurantController.getFoodItem(item.getItemName());

            // calculate the item total by multiplying the quantity and price
            double itemTotal = item.getQuantity() * foodItem.getItemPrice();

            // add the item total to the order total
            orderTotal += itemTotal;
        }
        return orderTotal;
    }

    private FoodItem getItem(int id){
        FoodItem foodItem = restaurantController.getFoodItem(id);
        return foodItem;
    }

    public void newOrder() {
        System.out.println("Welcome to Order Screen");
        System.out.println();

        Scanner restNameScanner = new Scanner(System.in);
        System.out.println("Enter a restaurant name to begin");
        String restName = restNameScanner.nextLine();

        Set<Restaurant> restaurantList = restaurantController.searchRestaurants(restName);
        System.out.println("The list of restaurants that match your query is :");

        if (restaurantList == null || restaurantList.isEmpty()) {
            System.out.println("Sorry no matches found!!");
        } else
            for (Restaurant r : restaurantList) {
                r.printRestaurantFull();
                System.out.println();
            }

        List<OrderDetails> orderItems = new ArrayList<>();
        Scanner itemIdScanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter the ID of the item you want to order (or 0 to finish):");
            int itemId = itemIdScanner.nextInt();

            if (itemId == 0) {
                break;
            }

            System.out.println("Enter the quantity:");
            int quantity = itemIdScanner.nextInt();
            FoodItem items = getItem(itemId);
            if (quantity <= items.getItemStock()) {

                OrderDetails orderItem = new OrderDetails();
                orderItem.setItemName(itemId);
                orderItem.setQuantity(quantity);
                orderItem.setItemNames(items.getItemName());
                orderItems.add(orderItem);
            }
            else{
                System.out.println("Not enough in stock, order less");
            }
        }

        System.out.println("Enter your customer ID");
        Scanner customerIdScanner = new Scanner(System.in);
        int customerId = customerIdScanner.nextInt();

        System.out.println("Enter the restaurant ID you are ordering from");
        Scanner restIdScanner = new Scanner(System.in);
        int restId = restIdScanner.nextInt();

        double orderTotal = calculateOrderTotal(orderItems);

        System.out.println("CaneEats thanks you for your order!");
        for (OrderDetails item : orderItems) {
            System.out.print(item.getQuantity());
            System.out.print("x ");
            System.out.println(item.getItemNames());
        }
        System.out.printf("----The order total is: $%.2f----%n", orderTotal);

        Order order = new Order();
        order.setCustomerID(customerId);
        order.setRestaurantID(restId);
        order.setTotal(orderTotal);
        orderController.placeOrder(order, orderItems);
    }

}
