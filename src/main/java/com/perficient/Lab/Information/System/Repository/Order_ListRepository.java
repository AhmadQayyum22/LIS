package com.perficient.Lab.Information.System.Repository;

import com.perficient.Lab.Information.System.Entity.Order_List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Order_ListRepository extends JpaRepository<Order_List, Integer> {
}
