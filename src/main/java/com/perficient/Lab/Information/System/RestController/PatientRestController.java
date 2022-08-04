package com.perficient.Lab.Information.System.RestController;

import com.perficient.Lab.Information.System.Entity.Patients;
import com.perficient.Lab.Information.System.Repository.PatientsRepository;
import com.perficient.Lab.Information.System.Service.PatientsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/LIS")
public class PatientRestController {

    @Autowired
    PatientsRepository patRepo;

    @Autowired
    PatientsService patServ;

    @GetMapping("/allpatients")
    public List<Patients> getAllPatients(){
        return patRepo.findAll(Sort.by("patId").descending());
    }

    @GetMapping("/patient/{id}")
    public Patients getPatientById(@PathVariable("id") int id){
        return patServ.getPatientById(id);
    }

    //post mapping
    @PostMapping("/patient/save")
    public Patients saveNewPatient(@RequestBody Patients pat){
        return patServ.addOrUpdatePatient(pat);
    }

//    @DeleteMapping("/patient/delete/{id}")
//    public void deletePatient(@PathVariable("id") int id){
//        try {
//             patServ.deletePatient(id);
//        }
//        catch (Exception e) {
//            e.getMessage();
//        }
//        }


    }

