package edu.umiami.caneeats.database;

import edu.umiami.caneeats.entityobjects.Order;
import edu.umiami.caneeats.entityobjects.OrderDetails;
import edu.umiami.caneeats.utilities.DBConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import java.sql.Statement;


public class OrderDataManager {

    public void placeOrder(Order order, List<OrderDetails> orderDetails){

        String sqlNewOrder = "INSERT INTO `CaneEats`.`ORDERS` (`CUST_ID`, `REST_IDS`, " +
                "`DATE`, `TOTAL`) VALUES (?,?,?,?)";

        try (Connection conn = DBConnection.getInstance().getDbConnect();
             PreparedStatement stmt = conn.prepareStatement(sqlNewOrder, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, order.getCustomerID());
            stmt.setInt(2, order.getRestaurantID());
            java.util.Date utilDate = new java.util.Date();
            java.sql.Timestamp timestamp = new java.sql.Timestamp(utilDate.getTime());
            stmt.setTimestamp(3, timestamp);

            stmt.setDouble(4, order.getTotal());

            int affectedRows = stmt.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating order failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setOrderID(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }

            String sqlNewOrderDetail = "INSERT INTO `CaneEats`.`ORDER_DETAILS` (`ORDER_ID`, `ITEM_ID`, `ORDER_QTY`, `NAME_ITEM`) VALUES (?,?,?,?)";

            for (OrderDetails orderDetail : orderDetails) {

                try (PreparedStatement stmtOrderDetail = conn.prepareStatement(sqlNewOrderDetail)) {
                    //System.out.println("Adding to Order Details");
                    stmtOrderDetail.setInt(1, order.getOrderID());
                    stmtOrderDetail.setInt(2, orderDetail.getItemName());
                    stmtOrderDetail.setInt(3, orderDetail.getQuantity());
                    stmtOrderDetail.setString(4,orderDetail.getItemNames());

                    stmtOrderDetail.executeUpdate();

                } catch (SQLException e) {
                    throw new RuntimeException("Error adding order detail to database", e);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error adding order to database", e);
        }
    }
}
