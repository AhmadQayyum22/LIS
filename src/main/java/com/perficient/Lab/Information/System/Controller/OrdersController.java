package com.perficient.Lab.Information.System.Controller;

import com.perficient.Lab.Information.System.Entity.*;
import com.perficient.Lab.Information.System.Service.*;
import org.springframework.beans.factory.annotation.Autowired;;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/LIS")
public class OrdersController {
    int orderId = 0;

    @Autowired
    private OrdersService ordersService;
    @Autowired
    private PhysiciansService physiciansService;
    @Autowired
    private PatientsService patientsService;
    @Autowired
    private Order_ItemsService itemsService;
    @Autowired
    private Order_ListService orderListService;

    @GetMapping("/orders/patId{id}")
    public String AddOrder(Model model, @PathVariable("id") int patsId, Orders order){
        Patients patient = patientsService.getPatientById(patsId);
        model.addAttribute("order", new Orders(patient.getPatId(), patient.getPhyId(), LocalDate.now()));
        model.addAttribute("pageTitle", "Order List");
        model.addAttribute("patients", patient);
        if (ordersService.check(patsId) == false) {
            ordersService.newOrder(patsId);
            return "redirect:/LIS/orderlist/patId{id}";
        }
        else{
            orderId = ordersService.orderId;
            return"redirect:/LIS/orderlist/patId{id}";
        }
    }
    @GetMapping("/orders/save/patId{id}" )
    public String AddOrUpdate(Orders order, @PathVariable("id") int patId, Model model){
        Patients patients = patientsService.getPatientById(patId);
        model.addAttribute("order", new Orders(patId, patients.getPhyId(), LocalDate.now()));
        model.addAttribute("patients", patients);
        Orders placedOrder = ordersService.addOrUpdateOrder(order);
        orderId = placedOrder.getOrderId();
        String url ="redirect:/LIS/orderlist/patId{id}";
        return url;

    }



}
