package com.perficient.Lab.Information.System.Controller;


import com.perficient.Lab.Information.System.Entity.*;
import com.perficient.Lab.Information.System.Service.Order_ListService;
import com.perficient.Lab.Information.System.Service.OrdersService;
import com.perficient.Lab.Information.System.Service.PatientsService;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/LIS")
public class Order_ListController {

    @Autowired
    private Order_ListService orderListService;
    @Autowired
    private PatientsService patientsService;

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private OrdersController ord;

    @GetMapping("/orderlist/patId{id}")
    public String getOrderList(Model model, @PathVariable("id") int patsId){
        int orderId = ord.orderId;
        List<Order_List> orderList = orderListService.getFullOrderList();
        Patients patient = patientsService.getPatientById(patsId);
        Orders order = ordersService.getOrderById(orderId);
        model.addAttribute("orderid", orderId);
//        model.addAttribute("order", new Orders(patsId, patient.getPhyId(), LocalDate.now()));
        //model.addAttribute("item", new Order_Items(orderId, order.get))
        model.addAttribute("orderList", orderList);
        model.addAttribute("pageTitle", "Order List");
        model.addAttribute("patients", patient);
        return "orderlist";
    }

    @GetMapping("/fullorderlist")
    public String fullOrderList(Model model){
        List<Order_List> order = orderListService.getFullOrderList();
        model.addAttribute("order", order);
        return "orderListInfo.html";
    }

    @GetMapping("/orderlist/new")
    public String addNewOrderList(Model model){
        model.addAttribute("pageTitle", "Add New Order");
        model.addAttribute("list", new Order_List());
        return "AddNewOrderList.html";
    }

    @PostMapping("/orderlist/save")
    public String AddOrUpdateOrderList(Order_List list){
        orderListService.addOrUpdateOrderList(list);

        return "redirect:/LIS/fullorderlist";
    }


    @GetMapping("orderlist/edit/{id}")
    public String getOrderListById(@PathVariable("id") int orderId, Model model, RedirectAttributes ra){
        try {
            Order_List list = orderListService.getOrderListById(orderId);
            model.addAttribute("list", list);
            model.addAttribute("pageTitle", "Edit Order (ID: "+ orderId +" )");
            return "AddNewOrderList.html";
        }
        catch (Exception e){
            ra.addFlashAttribute("message", "The Patient has been saved Successfully");

            return "redirect:/LIS/fullorderlist";
        }

    }

    @GetMapping("/orderlist/delete/{id}")
    public String deleteOrderList(@PathVariable("id") int orderId, RedirectAttributes ra, Model model){

        try {
            Order_List list = orderListService.deleteOrderList(orderId);


        }
        catch(Exception e){
            ra.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/LIS/fullorderlist";


    }




}
