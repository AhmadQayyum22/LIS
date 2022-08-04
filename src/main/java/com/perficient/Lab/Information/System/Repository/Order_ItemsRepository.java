package com.perficient.Lab.Information.System.Repository;

import com.perficient.Lab.Information.System.Entity.Order_Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface Order_ItemsRepository extends JpaRepository<Order_Items, Integer> {
    List<Order_Items> findByOrderId(int id);
}
