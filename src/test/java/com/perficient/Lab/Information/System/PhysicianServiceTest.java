package com.perficient.Lab.Information.System;

import com.perficient.Lab.Information.System.Entity.Physicians;
import com.perficient.Lab.Information.System.Repository.PhysiciansRepository;
import com.perficient.Lab.Information.System.Service.PhysiciansService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PhysicianServiceTest {

    @MockBean
    PhysiciansRepository phyRepo;

    @Autowired
    PhysiciansService phyServ;

    Physicians phy;

    @BeforeEach
    public void Before(){
        phy = new Physicians(1, "Abdullah", "2509 North Broadway", "St. Louis", "MO", 63102);
    }

    @Test
    public void TestgetAllPhysicians(){
       when(phyRepo.findAll()).thenReturn(Stream.of(new Physicians(1, "Abdullah", "2509 North Broadway", "St. Louis", "MO", 63102),
               new Physicians(2, "Zeeshan", "2509 North Broadway", "St. Louis", "MO", 63102))
               .collect(Collectors.toList()));
       assertEquals(2, phyServ.getAllPhysicians().size());
    }

    @Test
    public void TestgetPhysicianById(){
        when(phyRepo.findById(phy.getPhyId())).thenReturn(Optional.of(Optional.of(phy).orElse(null)));
        assertEquals(phy, phyServ.getPhysicianById(phy.getPhyId()));
    }

    @Test
    public void TestaddOrUpdatePhysician(){
        when(phyRepo.save(phy)).thenReturn(phy);
        assertEquals(phy, phyServ.addOrUpdatePhysician(phy));
    }

    @Test
    public void TestdeletePhysician() throws Exception{
        when(phyRepo.findById(phy.getPhyId())).thenReturn(Optional.of(phy));
        phyServ.deletePhysician(phy.getPhyId());
        verify(phyRepo, atLeastOnce()).deleteById(phy.getPhyId());

    }
}
