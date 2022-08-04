package com.perficient.Lab.Information.System;

import com.perficient.Lab.Information.System.Entity.Orders;
import com.perficient.Lab.Information.System.Entity.Physicians;
import com.perficient.Lab.Information.System.Repository.Order_ItemsRepository;
import com.perficient.Lab.Information.System.Repository.OrdersRepository;
import com.perficient.Lab.Information.System.Service.OrdersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
}
