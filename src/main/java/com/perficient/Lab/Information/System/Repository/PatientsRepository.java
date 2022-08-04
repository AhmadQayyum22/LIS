package com.perficient.Lab.Information.System.Repository;

import com.perficient.Lab.Information.System.Entity.Patients;
import com.perficient.Lab.Information.System.Entity.Physicians;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Integer> {

//    List<Patients> findByOrderBypatIdDesc();
    Patients findBypatId(int id);
    //@Query("SELECT p FROM Patients p WHERE CONCAT(p.patId, p.lName, p.fName, p.DOB, p.SSN, p.address, p.City, p.State, p.zipcode, p.orderdate, p.phyId) LIKE %?1%")
    List<Patients> findBylName(String keyword);


}
