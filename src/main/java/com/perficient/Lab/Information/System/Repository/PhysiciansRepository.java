package com.perficient.Lab.Information.System.Repository;

import com.perficient.Lab.Information.System.Entity.Physicians;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysiciansRepository extends JpaRepository<Physicians, Integer> {
    String findByphyId(int id);
}
