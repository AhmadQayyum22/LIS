package com.perficient.Lab.Information.System.Controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.perficient.Lab.Information.System.Entity.Patients;
import com.perficient.Lab.Information.System.Entity.Physicians;
import com.perficient.Lab.Information.System.Repository.UsersRepository;
import com.perficient.Lab.Information.System.Service.PatientsService;

import com.perficient.Lab.Information.System.Service.PhysiciansService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/LIS")
public class PatientController {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    private PatientsService patientService;
    @Autowired
    PhysiciansService physiciansService;

    @GetMapping("/allPatient")
    public String viewallPatient(Model model, Authentication authentication, @Param("keyword") String keyword) {
        if(!usersRepository.findByuserName(authentication.getName()).isApproved()) {
            return "redirect:/LIS/login/checkIsApprove";
        }
        else{
                List<Patients> listpatients = patientService.getAllPatients(keyword);

//                List phyName = new ArrayList();
//                for(int i =0; i<listpatients.size(); i++){
//                    int phyId = listpatients.get(i).getPhyId().getPhyId();
//                    Physicians physician = physiciansService.getPhysicianById(phyId);
//                    phyName.add(phyId);
//
//            }
                model.addAttribute("listpatients", listpatients);
//                model.addAttribute("phyName", phyName);
//                System.out.println(phyName);
                model.addAttribute("keyword", keyword);
                return "home";
            }
    }
    @GetMapping("/patient/new")
    public String addPatient(Model model, RedirectAttributes ra){
        List<Physicians> physicians = physiciansService.getAllPhysicians();
        model.addAttribute("physician", physicians);
        model.addAttribute("pageTitle", "Add New User");
        ra.addFlashAttribute("message", "The Patient has been saved Successfully.");
        model.addAttribute("patient", new Patients());
        return "newPatient";
    }
    @PostMapping("/patient/save")
    public String AddOrUpdate(Patients patient){
        patientService.addOrUpdatePatient(patient);

        return "redirect:/LIS/allPatient";
    }

    @GetMapping("patient/edit/{id}")
    public String getPatientById(@PathVariable("id") int patientId, Model model, RedirectAttributes ra){
        List<Physicians> physicians = physiciansService.getAllPhysicians();
        try {
            Patients patient = patientService.getPatientById(patientId);
            model.addAttribute("physician", physicians);
            model.addAttribute("patient", patient);
            model.addAttribute("pageTitle", "Edit User (ID: "+ patientId +" )");
            return "newPatient";
        }
        catch (Exception e){
            ra.addFlashAttribute("message", "The Patient has been saved Successfully");

            return "redirect:/LIS/allPatient";
        }

    }




//    @GetMapping("/patient/delete/{id}")
//    public String deletePatient(@PathVariable("id") int patientId, RedirectAttributes ra){
//
//        try {
//            patientService.deletePatient(patientId);
//
//        }
//        catch(Exception e){
//            ra.addFlashAttribute("message", e.getMessage());
//
//        }
//        return "redirect:/LIS/allPatient";
//
//
//    }
}
