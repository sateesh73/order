package com.io.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.io.order.entity.OrderDetails;

@Repository
public interface OrderRepo extends JpaRepository<OrderDetails, Long>{

}
