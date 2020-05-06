package com.coodcool.icook.dao.repository;

import com.coodcool.icook.model.Address;
import com.coodcool.icook.mother.AddressMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class AddressRepositoryTest {

    @Autowired
    private AddressRepository addressRepository;

    @Test
    public void saveOneSimple() {
        Address address = AddressMother.withoutIdAndUser().build();
        addressRepository.save(address);
        assertThat(addressRepository.findAll()).hasSize(1);
    }
}
