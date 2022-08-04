package com.perficient.Lab.Information.System;

import com.perficient.Lab.Information.System.Entity.Patients;
import com.perficient.Lab.Information.System.Entity.Physicians;
import com.perficient.Lab.Information.System.Entity.Users;
import com.perficient.Lab.Information.System.Repository.PatientsRepository;
import com.perficient.Lab.Information.System.Service.PatientsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PatientServiceTest {

    @MockBean
    PatientsRepository patRepo;

    @Autowired
    PatientsService patServ;

    Physicians phy;

    LocalDate date;

    @BeforeEach
    public void Before(){
        phy = new Physicians(1, "Abdullah", "2509 North Broadway", "St. Louis", "MO", 63102);
        date = LocalDate.now();
    }

    @Test
    public void TestgetAllPatients(){

        when(patServ.getAllPatients("keyword")).thenReturn(Stream.of(new Patients(1, "Qayyum", "Ahmad", date, "123-45-6789", "727 Leonard ave", "Valley Park", "MO", 63088, date, phy), new Patients(1, "Qayyum", "Saad", date, "123-45-6789", "727 Leonard ave", "Valley Park", "MO", 63088, date, phy))
                .collect(Collectors.toList()));
        assertEquals(2, patServ.getAllPatients("keyword").size());
    }

    @Test
    public void TestgetPatientById(){
        Patients pat = new Patients(1, "Qayyum", "Ahmad", date, "123-45-6789", "727 Leonard ave", "Valley Park", "MO", 63088, date, phy);
        when(patRepo.findById(pat.getPatId())).thenReturn(Optional.of(Optional.of(pat).orElse(null)));
        assertEquals(pat.getPatId(), patServ.getPatientById(pat.getPatId()).getPatId());
    }

    @Test
    public void TestaddOrUpdatePatient(){
        Patients pat = new Patients(1, "Qayyum", "Ahmad", date, "123-45-6789", "727 Leonard ave", "Valley Park", "MO", 63088, date, phy);
        when(patRepo.save(pat)).thenReturn(pat);
        assertEquals(pat, patServ.addOrUpdatePatient(pat));
    }
}
