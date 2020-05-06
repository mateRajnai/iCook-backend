package com.coodcool.icook.mother;

import com.coodcool.icook.model.Address;

public class AddressMother {

    public static Address.AddressBuilder completeWithOutIdAndUser() {
        return Address.builder()
                .zipCode("3200")
                .city("Gyongyos")
                .address("Fo ut 6.")
                .country("Hungary");
    }

    public static Address.AddressBuilder completeWithoutUserAndWithCustom(Long id) {
        return Address.builder()
                .id(id)
                .zipCode("3200")
                .city("Gyongyos")
                .address("Fo ut 6.")
                .country("Hungary");
    }
}
