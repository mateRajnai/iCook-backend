package com.coodcool.icook.mother;

import com.coodcool.icook.model.Address;
import com.coodcool.icook.model.User;

public class AddressMother {

    public static Address.AddressBuilder withoutIdAndUser() {
        return Address.builder()
                .zipCode("3200")
                .city("Gyongyos")
                .address("Fo ut 6.")
                .country("Hungary");
    }

    public static Address.AddressBuilder withoutUserAndWithCustom(Long id) {
        return withoutIdAndUser()
                .id(id);
    }

    public static Address.AddressBuilder withoutIdAndWithCustom(User user) {
        return withoutIdAndUser()
                .user(user);
    }

    public static Address.AddressBuilder completeWithCustomIdAndUser(Long id, User user) {
        return withoutUserAndWithCustom(id)
                .user(user);
    }
}
