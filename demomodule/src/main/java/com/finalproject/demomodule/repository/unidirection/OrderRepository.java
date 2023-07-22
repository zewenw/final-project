package com.finalproject.demomodule.repository.unidirection;

import com.finalproject.demomodule.entity.unidirection.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("unidirectionalOrderRepository")
public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderTrackingNumber(String orderTrackingNumber);
}
