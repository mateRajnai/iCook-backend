package com.coodcool.icook.dao.repository;

import com.coodcool.icook.model.Address;
import com.coodcool.icook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findAddressByUser(User user);
}
