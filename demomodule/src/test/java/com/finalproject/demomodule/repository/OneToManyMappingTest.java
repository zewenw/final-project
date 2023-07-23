package com.finalproject.demomodule.repository;

import com.finalproject.demomodule.entity.unidirection.Address;
import com.finalproject.demomodule.entity.unidirection.Order;
import com.finalproject.demomodule.entity.unidirection.OrderItem;
import com.finalproject.demomodule.repository.unidirection.OrderRepository;
import com.finalproject.demomodule.repository.unidirection.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class OneToManyMappingTest {


    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // save order along with also save it's order items
    @Test
    void saveOrderMethod(){
        Order order = new Order();
        order.setOrderTrackingNumber("100ABC");
        order.setStatus("In progress");

        // create order item 1
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(productRepository.findById(5L).get());
        orderItem1.setQuantity(2);
        orderItem1.setPrice(orderItem1.getProduct().getPrice().multiply(new BigDecimal(2)));
        orderItem1.setImageUrl("image1.png");
        order.getOrderItems().add(orderItem1);

        // create order item 2
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProduct(productRepository.findById(6L).get());
        orderItem2.setQuantity(3);
        orderItem2.setPrice(orderItem2.getProduct().getPrice().multiply(new BigDecimal(3)));
        orderItem2.setImageUrl("image2.png");
        order.getOrderItems().add(orderItem2);

        order.setTotalPrice(order.getTotalAmount());
        order.setTotalQuantity(2);

        Address address = new Address();
        address.setCity("Pune");
        address.setStreet("Kothrud");
        address.setState("Maharashtra");
        address.setCountry("India");
        address.setZipCode("411047");

        order.setBillingAddress(address);

        orderRepository.save(order);
    }

    @Test
    void fetchOrderMethod(){
        Order order = orderRepository.findById(2L).get();
        System.out.println(order.getStatus());
        System.out.println(order);
        for (OrderItem item: order.getOrderItems()){
            System.out.println(item.getProduct().getName());
        }
    }

    @Test
    void deleteOrderMethod(){
        orderRepository.deleteById(2L);
    }
}
