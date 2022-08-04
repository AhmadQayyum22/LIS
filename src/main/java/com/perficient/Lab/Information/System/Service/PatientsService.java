package com.perficient.Lab.Information.System.Service;

import com.perficient.Lab.Information.System.Entity.Order_Items;
import com.perficient.Lab.Information.System.Entity.Orders;
import com.perficient.Lab.Information.System.Entity.Patients;
import com.perficient.Lab.Information.System.Entity.Physicians;
import com.perficient.Lab.Information.System.Repository.Order_ItemsRepository;
import com.perficient.Lab.Information.System.Repository.OrdersRepository;
import com.perficient.Lab.Information.System.Repository.PatientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PatientsService {

    @Autowired
    private PatientsRepository patRepo;

    @Autowired
    OrdersRepository orderRepo;

    @Autowired
    Order_ItemsRepository orderItemRepo;

//    public void deletePatient(int patientId) throws Exception {
//        Patients deletedpatient = null;
//        Orders order = null;
//        try {
//            deletedpatient = patRepo.findById(patientId).orElse(null);
//            order = orderRepo.findByPatId(patientId);
//            int id = order.getOrderId();
//            if (deletedpatient == null) {
//                throw new Exception("User not Available");
//            }
//                patRepo.deleteById(patientId);
//
//
//
//        }
//        catch (Exception ex) {
//            throw ex;
//        }
//
//
//
//    }

    public List<Patients> getAllPatients(String Keyword) {
        if (Keyword != null){
            List<Patients> pat = patRepo.findBylName(Keyword);
            Collections.reverse(pat);
            return pat;
        }
        List<Patients> patients = (List<Patients>) patRepo.findAll(Sort.by("patId").descending());
        //Collections.reverse(patients);
        return patients;
    }


    public Patients getPatientById(int patientId) {
        return patRepo.findById(patientId).orElse(null);
    }

    public Patients addOrUpdatePatient(Patients patient) {
        return patRepo.save(patient);
    }


}
