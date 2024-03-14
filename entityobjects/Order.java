package edu.umiami.caneeats.entityobjects;

import java.math.BigDecimal;
import java.security.Timestamp;
import java.util.Date;
import java.util.List;

public class Order {
    private int orderID;
    private Date date;
    private double total;
    private int quantity;
    private int customerID;
    private int restaurantID;
    private List<OrderDetails> orderDetails;

    public int getCustomerID(){return customerID;}
    public void setCustomerID(int customerID) {this.customerID = customerID;}

    public int getRestaurantID(){return restaurantID;}
    public void setRestaurantID(int restaurantID) {this.restaurantID = restaurantID;}

    public int getOrderID() {
        return orderID;
    }
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }

    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID='" + orderID + '\'' +
                ", date='" + date + '\'' +
                ", total=" + total +
                ", quantity='" + quantity + '\'' +
                '}';
    }

}
