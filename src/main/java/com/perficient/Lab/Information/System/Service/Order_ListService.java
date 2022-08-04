package com.perficient.Lab.Information.System.Service;

import com.perficient.Lab.Information.System.Entity.Order_List;
import com.perficient.Lab.Information.System.Repository.Order_ListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Order_ListService {

    @Autowired
    private Order_ListRepository orderListRepo;
    public List<Order_List> getFullOrderList() {
        List<Order_List> orderList = (List<Order_List>) orderListRepo.findAll();
        return orderList;
    }

    public Order_List getOrderListById(int listId) {
        return orderListRepo.findById(listId).orElse(null);
    }

    public Order_List addOrUpdateOrderList(Order_List list) {
        return orderListRepo.save(list);

    }

    public Order_List deleteOrderList(int listId) throws Exception {
        Order_List orderList = null;
        try {
            orderList = orderListRepo.findById(listId).orElse(null);
            if(orderList == null) {
                throw new Exception("Order not Available");
            }
            else {
                orderListRepo.deleteById(listId);
            }
        }
        catch (Exception ex) {
            throw ex;
        }

        return orderList;

    }


}
