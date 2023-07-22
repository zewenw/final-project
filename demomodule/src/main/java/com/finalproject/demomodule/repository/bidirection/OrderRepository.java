package com.finalproject.demomodule.repository.bidirection;

import com.finalproject.demomodule.entity.unidirection.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("bidirectionalOrderRepository")
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderTrackingNumber(String orderTrackingNumber);
}
