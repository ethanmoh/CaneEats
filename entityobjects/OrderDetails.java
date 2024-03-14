package edu.umiami.caneeats.entityobjects;

public class OrderDetails {
    private int orderDetailID;
    private int itemName;
    private int quantity;
    private String itemNames;

    public String getItemNames(){return itemNames;}
    public void setItemNames(String name){this.itemNames = name;}

    public int getOrderDetailID() {
        return orderDetailID;
    }
    public void setOrderDetailID(int orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public int getItemName() {
        return itemName;
    }
    public void setItemName(int itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderDetail{" +
                "orderDetailID='" + orderDetailID + '\'' +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

