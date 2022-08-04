package com.perficient.Lab.Information.System.Service;

import com.perficient.Lab.Information.System.Entity.Patients;
import com.perficient.Lab.Information.System.Entity.Physicians;
import com.perficient.Lab.Information.System.Repository.PhysiciansRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhysiciansService {


    @Autowired
    private PhysiciansRepository phyRepo;



    public List<Physicians> getAllPhysicians() {
        List<Physicians> physicians = (List<Physicians>)phyRepo.findAll();
        return (List<Physicians>)phyRepo.findAll();
    }

    public Physicians getPhysicianById(int physicianId) {
        return phyRepo.findById(physicianId).orElse(null);
    }

    public Physicians addOrUpdatePhysician(Physicians physician) {
        return phyRepo.save(physician);
    }

    public Physicians deletePhysician(int physicianID) throws Exception {

            Physicians deletedPhysician = phyRepo.findById(physicianID).orElseThrow(() ->new Exception("Physician Not Found"));
            phyRepo.deleteById(physicianID);
            return deletedPhysician;

    }

}

