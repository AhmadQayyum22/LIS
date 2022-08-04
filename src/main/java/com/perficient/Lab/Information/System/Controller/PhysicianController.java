package com.perficient.Lab.Information.System.Controller;


import com.perficient.Lab.Information.System.Entity.Patients;
import com.perficient.Lab.Information.System.Entity.Physicians;
import com.perficient.Lab.Information.System.Service.PhysiciansService;
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
public class PhysicianController {

    @Autowired
    private PhysiciansService physiciansService;

    @GetMapping("/allphysician")
    public String allPhysicians(Model model){
        List<Physicians> physicians = physiciansService.getAllPhysicians();
        model.addAttribute("phys", physicians);
        return "physiciansInfo.html";
    }

    @GetMapping("/physician/new")
    public String addNewPhy(Model model, RedirectAttributes ra){
        model.addAttribute("pageTitle", "Add New Physician");
        model.addAttribute("phys", new Physicians());
        return "newphysician.html";
    }
    @PostMapping("/physician/save")
    public String AddOrUpdatePhy(Physicians phy){
        physiciansService.addOrUpdatePhysician(phy);

        return "redirect:/LIS/allphysician";
    }

    @GetMapping("physician/edit/{id}")
    public String getPhyById(@PathVariable("id") int phyId, Model model, RedirectAttributes ra){
        try {
            Physicians phys = physiciansService.getPhysicianById(phyId);
            model.addAttribute("phys", phys);
            model.addAttribute("pageTitle", "Edit User (ID: "+ phyId +" )");
            return "newphysician.html";
        }
        catch (Exception e){
            ra.addFlashAttribute("message", "The Patient has been saved Successfully");

            return "redirect:/LIS/allphysician";
        }

    }

    @GetMapping("/physician/delete/{id}")
    public String deletePhy(@PathVariable("id") int phyId, RedirectAttributes ra){

        try {
           physiciansService.deletePhysician(phyId);

        }
        catch(Exception e){
            ra.addFlashAttribute("message", e.getMessage());

        }
        return "redirect:/LIS/allphysician";


    }

}
