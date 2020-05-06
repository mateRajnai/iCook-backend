package com.coodcool.icook.mother;

import com.coodcool.icook.model.PersonalNote;
import com.coodcool.icook.model.User;

import java.time.LocalDateTime;

public class PersonalNoteMother {

    public static User.UserBuilder getDummyUser() {
        return User.builder()
                .userName("John")
                .firstName("Denem")
                .email("john.denem@gmail.com")
                .password("johndenem123");
    }

    public static User.UserBuilder getDummyUser2() {
        return User.builder()
                .userName("Peter")
                .firstName("Denem")
                .email("peter.denem@gmail.com")
                .password("peteredenem123");
    }

    public static PersonalNote.PersonalNoteBuilder completePersonalNote() {

        return PersonalNote.builder()
                .recipeId((long) 1)
                .content("random content")
                .submissionTime(LocalDateTime.now());
    }

}
