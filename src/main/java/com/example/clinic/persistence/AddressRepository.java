package com.example.clinic.persistence;

import com.example.clinic.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Address findAddressByStreetAndCityAndState(String street,String city,String state);

}
