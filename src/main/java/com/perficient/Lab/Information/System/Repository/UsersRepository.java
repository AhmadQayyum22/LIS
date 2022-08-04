package com.perficient.Lab.Information.System.Repository;

import com.perficient.Lab.Information.System.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    Users findByuserName(String userName);
    List<Users> findByisApproved(boolean value);
}
