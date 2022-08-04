package com.perficient.Lab.Information.System.Service;

import com.perficient.Lab.Information.System.Entity.Orders;
import com.perficient.Lab.Information.System.Entity.Patients;
import com.perficient.Lab.Information.System.Entity.Physicians;
import com.perficient.Lab.Information.System.Repository.OrdersRepository;
import com.perficient.Lab.Information.System.Repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class OrdersService {
    public int orderId = 0;

    @Autowired
    private OrdersRepository oredrRepo;

    @Autowired
    private PatientsRepository patRepo;

    public List<Orders> getAllOrders() {
        List<Orders> orders = (List<Orders>) oredrRepo.findAll();
        return orders;
    }

    public Orders getOrderById(int orderId) {
        return oredrRepo.findById(orderId).orElse(null);
    }

    public Orders addOrUpdateOrder(Orders order) {
        return oredrRepo.save(order);
    }
    public boolean check(int id) {
        Orders order = oredrRepo.findByPatId(id);
        if (oredrRepo.findByPatId(id) != null){
            orderId = order.getOrderId();
            return true;
        }
        else {
            return false;
        }
    }
    public int OrderId(int id) {
        Orders order = oredrRepo.findByPatId(id);
        orderId = order.getOrderId();
        return orderId;
    }



    public Orders deleteOrder(int orderId) throws Exception {
        Orders deletedOrders = null;
        try{
            deletedOrders = oredrRepo.findById(orderId).orElse(null);
            if (deletedOrders ==null){
                throw new Exception("Order not Available");
            }
            else {
                oredrRepo.deleteById(orderId);
            }

        }
        catch (Exception ex){
            ex.getMessage();
        }

        return deletedOrders;
    }
    public Orders newOrder(int id){
        Patients pat = patRepo.findBypatId(id);
        int patId = pat.getPatId();
        Physicians phyId = pat.getPhyId();
        Orders orders = new Orders(patId, phyId, LocalDate.now());
        orders.setPatId(patId);
        orders.setPhyId(phyId);
        orders.setOrderDate(LocalDate.now());
        return oredrRepo.save(orders);
    }
}
