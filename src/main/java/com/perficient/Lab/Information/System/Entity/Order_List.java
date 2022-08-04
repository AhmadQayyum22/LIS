package com.perficient.Lab.Information.System.Entity;

import javax.persistence.*;

@Entity
@Table(name = "order_list")
public class Order_List {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderlist_id")
    private int orderListId;

    @Column(name = "test_code")
    private int testCode;

    @Column(name = "description")
    private String desCription;

    public Order_List() {
        super();
    }

    public Order_List(int orderListId, int testCode, String desCription) {
        this.orderListId = orderListId;
        this.testCode = testCode;
        this.desCription = desCription;
    }

    public int getOrderListId() {
        return orderListId;
    }

    public void setOrderListId(int orderListId) {
        this.orderListId = orderListId;
    }

    public int getTestCode() {
        return testCode;
    }

    public void setTestCode(int testCode) {
        this.testCode = testCode;
    }

    public String getDesCription() {
        return desCription;
    }

    public void setDesCription(String desCription) {
        this.desCription = desCription;
    }

    @Override
    public String toString() {
        return "Order_List{" +
                "orderListId=" + orderListId +
                ", testCode=" + testCode +
                ", desCription='" + desCription + '\'' +
                '}';
    }


}
