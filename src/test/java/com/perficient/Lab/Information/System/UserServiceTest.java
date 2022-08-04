package com.perficient.Lab.Information.System;


import com.perficient.Lab.Information.System.Entity.Roles;
import com.perficient.Lab.Information.System.Entity.Users;
import com.perficient.Lab.Information.System.Repository.RolesRepository;
import com.perficient.Lab.Information.System.Repository.UsersRepository;

import com.perficient.Lab.Information.System.Service.UsersService;
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
public class UserServiceTest {


    @MockBean
    UsersRepository userRepo;
    @Autowired
    UsersService userServ;


    Roles roleInfo;
    @BeforeEach
    public void Before(){
        roleInfo = new Roles(1, "admin");
        System.out.println(roleInfo);
    }

    @Test
    public void testGetAllUsers(){
        when(userRepo.findAll()).thenReturn(Stream
                .of(new Users(1, "Ahmad", "Qayyum", "ahm", "ahmadgrewal22@gmail.com", "123456", true, roleInfo), new Users(2, "Saad", "Qayyum", "ahm", "ahmadgrewal22@gmail.com", "123456", true, roleInfo))
                .collect(Collectors.toList()));
        assertEquals(2, userServ.getAllUsers().size());
    }

    @Test
    public void testgetById(){
        int id=1;
        Users user = new Users(1, "Ahmad", "Qayyum", "ahm", "ahmadgrewal22@gmail.com", "123456", true, roleInfo);
        when(userRepo.findById(id)).thenReturn(Optional.of((Optional.of(user).orElse(null))));
        assertEquals(1, userServ.getById(id).getUserId());
    }

    @Test
    public void testSave(){
        Users user = new Users(1, "Ahmad", "Qayyum", "ahm", "ahmadgrewal22@gmail.com", "123456", true, roleInfo);
        when(userRepo.save(user)).thenReturn(user);
        assertEquals(user, userServ.save(user));
    }

    @Test
    public void testdeleteUser() throws Exception {
        //given
        Users user = new Users(1, "Ahmad", "Qayyum", "ahm", "ahmadgrewal22@gmail.com", "123456", true, roleInfo);
        //when
        doReturn(Optional.of(user)).when(userRepo).findById(user.getUserId());
        userServ.deleteUser(user.getUserId());
        //then
        verify(userRepo, atLeastOnce()).deleteById(user.getUserId());
    }
}
