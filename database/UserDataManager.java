package edu.umiami.caneeats.database;

import edu.umiami.caneeats.entityobjects.FoodItem;
import edu.umiami.caneeats.entityobjects.Restaurant;
import edu.umiami.caneeats.entityobjects.User;
import edu.umiami.caneeats.utilities.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class UserDataManager {

    public Set<User> getUsersList(String searchString)
    {
        String sql = "SELECT * FROM CUSTOMER WHERE CUST_NAME LIKE ?";

        Set<User> userSet = new HashSet<User>();

        try {
            Connection conn = DBConnection.getInstance().getDbConnect();
            PreparedStatement getCustomers = conn.prepareStatement(sql);
            getCustomers.setString(1,"%"+searchString+"%");

            ResultSet resultSet = getCustomers.executeQuery();

            while(resultSet.next())
            {
                User r = new User();

                Integer customerID = resultSet.getInt("CUST_ID");
                String customerName = resultSet.getString("CUST_NAME");
                System.out.println(" --> "+customerID+"  "+customerName);

                r.setCustomerID(customerID);
                r.setCustomerName(customerName);
                r.setCustomerPhone(resultSet.getString("CUST_PHONE"));
                r.setCustomerAddress(resultSet.getString("CUST_ADDRESS"));
                userSet.add(r);
            }

        }catch(Exception e)
        {
            System.out.println("Something really bad happened !!!");
            e.printStackTrace();
        }
        return userSet;
    }

    public int addNewUser(User user) {
        String sql = "INSERT INTO CUSTOMER (CUST_NAME, CUST_PHONE, CUST_ADDRESS) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getInstance().getDbConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getCustomerName());
            stmt.setString(2, user.getCustomerPhone());
            stmt.setString(3, user.getCustomerAddress());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error adding user to database", e);
        }
    }

    public int updateUser(User user) {
        String sql = "UPDATE CUSTOMER SET CUST_NAME = ?, CUST_PHONE = ?, CUST_ADDRESS = ? WHERE CUST_ID = ?";

        try (Connection conn = DBConnection.getInstance().getDbConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getCustomerName());
            stmt.setString(2, user.getCustomerPhone());
            stmt.setString(3, user.getCustomerAddress());
            stmt.setInt(4, user.getCustomerID());

            return stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating user in database", e);
        }
    }

    public int deleteUser(int userId) {
        String sql = "DELETE FROM CUSTOMER WHERE CUST_ID = ?";

        try (Connection conn = DBConnection.getInstance().getDbConnect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, userId);

            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected == 0) {
                throw new RuntimeException("No user found with ID: " + userId);
            }

            System.out.println("User deleted successfully.");
            return rowsAffected;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user from database", e);
        }
    }



}

