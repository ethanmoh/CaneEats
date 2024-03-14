package edu.umiami.caneeats.database;

import edu.umiami.caneeats.entityobjects.FoodItem;
import edu.umiami.caneeats.entityobjects.Menu;
import edu.umiami.caneeats.entityobjects.Restaurant;
import edu.umiami.caneeats.utilities.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class RestaurantDataManager {

    public int addNewRestaurant(Restaurant restaurant)
    {
        return 0;
    }

    public Set<Restaurant> getRestaurantsList(String searchString)
    {
        String sql = "SELECT * FROM RESTAURANT WHERE REST_NAME LIKE ?";

        Set<Restaurant> restSet = new HashSet<Restaurant>();
        
        try {
            Connection conn = DBConnection.getInstance().getDbConnect();
            PreparedStatement getRestaurants = conn.prepareStatement(sql);
            getRestaurants.setString(1,"%"+searchString+"%");

            ResultSet resultSet = getRestaurants.executeQuery();

            while(resultSet.next())
            {
                Restaurant r = new Restaurant();

                Integer restID = resultSet.getInt("REST_ID");
                String restName = resultSet.getString("REST_NAME");
                System.out.println(" --> "+restID+"  "+restName);

                r.setRestaurantID(restID);
                r.setRestaurantName(restName);
                r.setRestaurantLocation(resultSet.getString("REST_ADDR"));
                r.setFromHours(resultSet.getString("REST_OPENS"));
                r.setToHours(resultSet.getString("REST_CLOSES"));

                PreparedStatement getMenu = conn.prepareStatement("SELECT * FROM FOODITEM WHERE REST_ID = ?");
                getMenu.setInt(1,restID);

                ResultSet foodItemsSet = getMenu.executeQuery();

                Set<FoodItem> menuSet = new HashSet<FoodItem>();

                while(foodItemsSet.next())
                {
                    FoodItem foodItem = new FoodItem();
                    foodItem.setItemID(foodItemsSet.getInt("ITEM_ID"));
                    foodItem.setItemName(foodItemsSet.getString("ITEM_NAME"));
                    foodItem.setItemPrice(foodItemsSet.getDouble("ITEM_PRICE"));
                    foodItem.setItemDescription(foodItemsSet.getString("DESCRIPTION"));
                    foodItem.setItemStock(foodItemsSet.getInt("AVAILABLE"));
                    ArrayList<String> allergens = new ArrayList<String>();
                    allergens.add(foodItemsSet.getString("ALLERGENS_LIST"));
                    foodItem.setAllergens(allergens);

                    menuSet.add(foodItem);

                }
                Menu m = new Menu();
                m.setFoodItemsSet(menuSet);
                r.setRestaurantMenu(m);
                restSet.add(r);


            }
        }catch(Exception e)
        {
            System.out.println("Something really bad happened !!!");
            e.printStackTrace();
        }
        return  restSet;
    }


    public void addMenu(Restaurant r) {

        String sqlNewFoodItem = "INSERT INTO `CaneEats`.`FoodItem` (`ITEM_ID`, `REST_ID`, " +
                "`ITEM_NAME`, `ITEM_PRICE`, `ALLERGENS_LIST`, " +
                "`AVAILABLE`, `DESCRIPTION`) VALUES (?,?,?,?,?,?,?)";
        Connection conn;
        try {
            conn = DBConnection.getInstance().getDbConnect();
            PreparedStatement newFood = conn.prepareStatement(sqlNewFoodItem);

            int itemId = 2;
            for(FoodItem f : r.getRestaurantMenu().getFoodItemsSet())
            {
                newFood.setInt(1, itemId++);
                newFood.setInt(2,r.getRestaurantID());
                newFood.setString(3,f.getItemName());
                newFood.setDouble(4,f.getItemPrice());
                newFood.setString(5,"NONE");
                newFood.setInt(6,1);
                newFood.setString(7,f.getItemDescription());

                System.out.println("Record Inserted "+newFood.executeUpdate());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public FoodItem getFoodItem(int itemID) {
        FoodItem foodItem = null;

        // Prepare the SQL statement
        String sql = "SELECT * FROM FoodItem WHERE ITEM_ID = ?";

        Connection conn;
        try {
            conn = DBConnection.getInstance().getDbConnect();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, itemID);
            // Execute the query and get the results
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String itemName = rs.getString("ITEM_NAME");
                double price = rs.getDouble("ITEM_PRICE");
                int stock = rs.getInt("AVAILABLE");
                foodItem = new FoodItem();
                foodItem.setItemName(itemName);
                foodItem.setItemPrice(price);
                foodItem.setItemStock(stock);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return foodItem;
    }

}
