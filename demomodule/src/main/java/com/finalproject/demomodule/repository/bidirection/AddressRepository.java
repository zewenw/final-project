package com.finalproject.demomodule.repository.bidirection;

import com.finalproject.demomodule.entity.bidirection.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("bidirectionalAddressRepository")
public interface AddressRepository extends JpaRepository<Address, Long> {
}
