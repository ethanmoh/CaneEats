package edu.umiami.caneeats.entityobjects;

public class Restaurant {
    private String restaurantName;
    private String restaurantLocation;
    private int restaurantID;
    private String fromHours;
    private String toHours;
    private Menu restaurantMenu;

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantLocation() {
        return restaurantLocation;
    }

    public void setRestaurantLocation(String restaurantLocation) {
        this.restaurantLocation = restaurantLocation;
    }

    public int getRestaurantID() {
        return restaurantID;
    }

    public void setRestaurantID(int restaurantID) {
        this.restaurantID = restaurantID;
    }

    public String getFromHours() {
        return fromHours;
    }

    public void setFromHours(String fromHours) {
        this.fromHours = fromHours;
    }

    public String getToHours() {
        return toHours;
    }

    public void setToHours(String toHours) {
        this.toHours = toHours;
    }

    public Menu getRestaurantMenu() {
        return restaurantMenu;
    }

    public void setRestaurantMenu(Menu restaurantMenu) {
        this.restaurantMenu = restaurantMenu;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "restaurantName='" + restaurantName + '\'' +
                ", restaurantLocation='" + restaurantLocation + '\'' +
                ", restaurantID=" + restaurantID +
                ", fromHours='" + fromHours + '\'' +
                ", toHours='" + toHours + '\'' +
                '}';
    }

    public void printRestaurantFull()
    {
        System.out.println(this);
        System.out.println();
        System.out.println("Restaurant Menu: ");
        restaurantMenu.printMenu();
    }
}
