package com.finalproject.demomodule.repository.unidirection;

import com.finalproject.demomodule.entity.unidirection.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("unidirectionalAddressRepository")
public interface AddressRepository extends JpaRepository<Address, Long> {
}
