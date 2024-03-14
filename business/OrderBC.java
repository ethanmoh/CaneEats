package edu.umiami.caneeats.business;

import edu.umiami.caneeats.database.OrderDataManager;
import edu.umiami.caneeats.entityobjects.Order;
import edu.umiami.caneeats.entityobjects.OrderDetails;

import java.util.List;

public class OrderBC {

    private OrderDataManager rdm;
    public OrderBC() {this.rdm = new OrderDataManager();}

    public void placeOrder(Order order, List<OrderDetails> orderDetails){rdm.placeOrder(order, orderDetails);}
}
