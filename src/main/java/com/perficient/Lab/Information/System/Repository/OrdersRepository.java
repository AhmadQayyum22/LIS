package com.perficient.Lab.Information.System.Repository;

import com.perficient.Lab.Information.System.Entity.Orders;
import com.perficient.Lab.Information.System.Entity.Patients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {


    Orders findByPatId(int id);

}
