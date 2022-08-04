package com.perficient.Lab.Information.System;

import com.perficient.Lab.Information.System.Entity.Orders;
import com.perficient.Lab.Information.System.Entity.Physicians;
import com.perficient.Lab.Information.System.Repository.Order_ItemsRepository;
import com.perficient.Lab.Information.System.Repository.OrdersRepository;
import com.perficient.Lab.Information.System.Service.OrdersService;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class OrdersServiceTest {

    @MockBean
    OrdersRepository orderRepo;

    @Autowired
    OrdersService orderServ;

    Physicians phy;
    LocalDate date;

    @BeforeEach
    public void Before(){
        phy = new Physicians(1, "Abdullah", "2509 North Broadway", "St. Louis", "MO", 63102);
        date = LocalDate.now();
    }

    @Test
    public void TestgetAllOrders(){
        when(orderRepo.findAll()).thenReturn(Stream.of(new Orders(1, 1, phy, date), new Orders(2, 1, phy, date)).collect(Collectors.toList()));
        assertEquals(2, orderServ.getAllOrders().size());
    }

    @Test
    public void TestgetOrderById(){
        Orders order = new Orders(1,1,phy, date);
        when(orderRepo.findById(order.getOrderId())).thenReturn(Optional.of(order));
        assertEquals(order.getOrderId(), orderServ.getOrderById(order.getOrderId()).getOrderId());
    }

    @Test
    public void TestaddOrUpdateOrder(){
        Orders order = new Orders(1,1,phy, date);
        when(orderRepo.save(order)).thenReturn(order);
        assertEquals(order, orderServ.addOrUpdateOrder(order));
    }

    @Test
    public void TestCheckTrue(){
        Orders order = new Orders(1,1,phy, date);
        when(orderRepo.findByPatId(order.getOrderId())).thenReturn(order);
        assertTrue(orderServ.check(order.getOrderId()));
    }

    @Test
    public void TestCheckFalse(){
        Orders order = new Orders(1,1,phy, date);
        when(orderRepo.findByPatId(order.getOrderId())).thenReturn(null);
        assertFalse(orderServ.check(order.getOrderId()));
    }
}
