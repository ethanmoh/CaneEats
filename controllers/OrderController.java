package edu.umiami.caneeats.controllers;

import edu.umiami.caneeats.business.OrderBC;
import edu.umiami.caneeats.entityobjects.Order;
import edu.umiami.caneeats.entityobjects.OrderDetails;

import java.util.List;

public class OrderController {

    private OrderBC orderBC;

    public OrderController()
    {
        this.orderBC = new OrderBC();
    }

    public void placeOrder(Order order, List<OrderDetails> orderDetails){orderBC.placeOrder(order,orderDetails);}
}






