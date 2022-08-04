package com.perficient.Lab.Information.System.Controller;


import com.perficient.Lab.Information.System.Entity.Order_Items;
import com.perficient.Lab.Information.System.Service.Order_ItemsService;
import com.perficient.Lab.Information.System.Service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/LIS")
public class Order_ItemsController {
    int orderId = 0;
    int pid =0;
    @Autowired
    private Order_ItemsService orderItemsService;

    @Autowired
    private OrdersService ordersService;

    @GetMapping("/orderdItems/patId{id}")
    public String orderedOrder(@PathVariable("id") int patId, Model model, Order_Items order_items){
        orderId = ordersService.OrderId(patId);
        pid = patId;
        List<Order_Items> orderItems = orderItemsService.findByOrderId(order_items, orderId);;

        model.addAttribute("orderItems", orderItems);
        return "orderItem";
    }

    @PostMapping("/addorupdateOrderItem")
    public ResponseEntity deleteOrderItem(@RequestBody Order_Items item){
        orderItemsService.addOrUpdateOrderItem(item);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/deleteOrderItem/item{id}")
    public ResponseEntity deleteOrderItem(@PathVariable("id") int orderItemID){
        try{
            orderItemsService.deleteOrderItem(orderItemID);

        }
        catch (Exception ex){
            ex.getMessage();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
