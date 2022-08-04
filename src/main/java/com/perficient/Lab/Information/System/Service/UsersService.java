package com.perficient.Lab.Information.System.Service;

import com.perficient.Lab.Information.System.Entity.Order_Items;
import com.perficient.Lab.Information.System.Entity.Patients;
import com.perficient.Lab.Information.System.Entity.Roles;
import com.perficient.Lab.Information.System.Entity.Users;
import com.perficient.Lab.Information.System.Repository.RolesRepository;
import com.perficient.Lab.Information.System.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RolesRepository RoleRepo;

    public List<Users> getAllUsers(){
        List<Users> users = usersRepository.findAll();
        return users;
    }

    public Users save(Users users){
        Roles role = RoleRepo.getByRoleName("user");
        users.setRoles(role);
        return usersRepository.save(users);
    }

    public Users getById(int id){
        return usersRepository.findById(id).orElse(null);
    }

    public List<Users> findIsapproved(boolean id) {
        List<Users> users = usersRepository.findByisApproved(id);;
        System.out.println(users);
        return users;
    }

    public Users approve(int id){
        Users user = usersRepository.findById(id).orElse(null);
        user.setApproved(true);
        String email = user.getEmail();
        user.setEmail(email);
        user.setConfirmEmail(email);
        String pass = user.getPassword();
        user.setPassword(pass);
        user.setConfirmPassword(pass);
        return usersRepository.save(user);
    }

    public Users admin(int id){
        Roles role = RoleRepo.getByRoleName("Admin");
        Users user = usersRepository.findById(id).orElse(null);
        user.setRoles(role);
        String email = user.getEmail();
        user.setEmail(email);
        user.setConfirmEmail(email);
        String pass = user.getPassword();
        user.setPassword(pass);
        user.setConfirmPassword(pass);
        return usersRepository.save(user);
    }

    public void deleteUser(int userId) throws Exception {
        Users user = usersRepository.findById(userId)
                .orElseThrow(() -> new Exception("User Not Available"));
        usersRepository.deleteById(user.getUserId());
    }


}
