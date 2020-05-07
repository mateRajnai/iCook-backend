package com.coodcool.icook.dao.repository;

import com.coodcool.icook.model.Address;
import com.coodcool.icook.model.FavoriteRecipe;
import com.coodcool.icook.model.User;
import com.coodcool.icook.mother.AddressMother;
import com.coodcool.icook.mother.FavoriteRecipeMother;
import com.coodcool.icook.mother.UserMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

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

    @Test
    public void getAllSimpleTest() {
        Address address1 = AddressMother
                .withoutIdAndUser()
                .build();
        Address address2 = AddressMother
                .withoutIdAndUser()
                .city("Budapest")
                .zipCode("1139")
                .build();

        address1 = addressRepository.save(address1);
        address2 = addressRepository.save(address2);

        List<Address> savedAddresses = addressRepository.findAll();
        assertThat(savedAddresses)
                .hasSize(2)
                .containsExactlyInAnyOrder(address1, address2);
    }

    @Test
    public void saveOneWithSimpleUser() {
        User user = UserMother.withoutAnyRelationsAndId().build();
        Address address = AddressMother
                .withoutIdAndWithCustom(user)
                .build();
        user.setAddress(address);

        addressRepository.save(address);
        List<Address> addresses = addressRepository.findAll();
        assertThat(addresses).hasSize(1);
        assertThat(addresses.get(0).getUser()).isNotNull();
    }
}
