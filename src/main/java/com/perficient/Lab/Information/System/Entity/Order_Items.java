package com.perficient.Lab.Information.System.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "order_item")
public class Order_Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderitem_id")
    private int orderItemId;

    @Column(name = "order_id")
    private int orderId;

    @Column(name = "order_desc")
    private String orderDesc;


    public Order_Items() {
        super();
    }

    public Order_Items(int orderId, String orderDesc) {
        this.orderId = orderId;
        this.orderDesc = orderDesc;
    }

    public Order_Items(int orderItemId, int orderId, String orderDesc ) {
        this.orderItemId = orderItemId;
        this.orderId = orderId;
        this.orderDesc = orderDesc;

    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderDesc() {
        return orderDesc;
    }

    public void setOrderDesc(String orderDesc) {
        this.orderDesc = orderDesc;
    }


    @Override
    public String toString() {
        return "Order_Items{" +
                "orderItemId=" + orderItemId +
                ", orderId=" + orderId +
                ", orderDesc='" + orderDesc + '\'' +

                '}';
    }
}
