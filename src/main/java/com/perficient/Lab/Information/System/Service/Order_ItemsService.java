package com.perficient.Lab.Information.System.Service;

import com.perficient.Lab.Information.System.Entity.Order_Items;
import com.perficient.Lab.Information.System.Entity.Orders;
import com.perficient.Lab.Information.System.Repository.Order_ItemsRepository;
import com.perficient.Lab.Information.System.Repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Order_ItemsService {

    @Autowired
    private Order_ItemsRepository orderItemsRepo;


    public List<Order_Items> getAllOrderItems() {
        List<Order_Items> orderItems = (List<Order_Items>) orderItemsRepo.findAll();
        return orderItems;
    }
    public Order_Items getOrderItemById(int itemId) {
        return orderItemsRepo.findById(itemId).orElse(null);
    }

    public Order_Items addOrUpdateOrderItem(Order_Items item) {
        return orderItemsRepo.save(item);
    }

    public List<Order_Items> findByOrderId(Order_Items orderItem, int id) {
        List<Order_Items> orderItems = (List<Order_Items>) orderItemsRepo.findAll();
        Order_Items orders = null;
        List<Order_Items> items = new ArrayList<>();
        for (int i = 0; i < orderItems.size(); i++) {
            if (orderItems.get(i).getOrderId() == id) {
                orders = orderItems.get(i);
                items.add(orders);
            }
        }

        return items;
    }

    public Order_Items deleteOrderItem(int itemID) throws Exception{
        Order_Items orderItems = null;
        try{
            orderItems = orderItemsRepo.findById(itemID).orElse(null);
            if (orderItems == null){
                throw new Exception("Item is not Available.");
            }
            else {
                orderItemsRepo.deleteById(itemID);
            }

        }
        catch (Exception ex){
            ex.getMessage();
        }

        return orderItems;

    }


}
