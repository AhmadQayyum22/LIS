package com.perficient.Lab.Information.System.Entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orders_id")
    private int orderId;



    private int patId;


    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.PERSIST, targetEntity = Physicians.class)
    @JoinColumn(name = "phy_id", referencedColumnName = "phy_id",nullable = false)
    private Physicians phyId;

    @Column(name = "date_ordered")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;

    public Orders() {
        super();

    }

    public Orders(int orderId, int patId, Physicians phyId, LocalDate orderDate) {
        this.orderId = orderId;
        this.patId = patId;
        this.phyId = phyId;
        this.orderDate = orderDate;
    }

    public Orders(int patId, Physicians phyId, LocalDate orderDate) {
        this.patId = patId;
        this.phyId = phyId;
        this.orderDate = orderDate;
    }



    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getPatId() {
        return this.patId;
    }

    public void setPatId(int patId) {
        this.patId = patId;
    }

    public Physicians getPhyId() {
        return phyId;
    }

    public void setPhyId(Physicians phyId) {
        this.phyId = phyId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "orderId=" + orderId +
                ", patId=" + patId +
                ", phyId=" + phyId +
                ", orderDate=" + orderDate +
                '}';
    }
}
