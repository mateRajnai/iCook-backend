package com.coodcool.icook.mother;

import com.coodcool.icook.model.PersonalNote;
import com.coodcool.icook.model.User;

import java.time.LocalDateTime;
import java.time.Month;

public class PersonalNoteMother {
    public static PersonalNote.PersonalNoteBuilder withoutUserAndId() {
        return PersonalNote.builder()
                .content("Add some more dried tomatoes, it will be more delicious")
                .recipeId("http%3A%2F%2Fwww.edamam.com%2Fontologies%2Fedamam.owl%23recipe_954a236596ef32f8e013cc9dbf52d0cf")
                .submissionTime(LocalDateTime.of(2019, Month.AUGUST, 10, 10,10,10));
    }

    public static PersonalNote.PersonalNoteBuilder withoutUserAndWithCustomId(Long id) {
        return withoutUserAndId()
                .id(id);
    }

    public static PersonalNote.PersonalNoteBuilder withoutIdAndWithCustomUser(User user) {
        return withoutUserAndId()
                .user(user);
    }

    public static PersonalNote.PersonalNoteBuilder completeWithCustomIdAndUser(Long id, User user) {
        return withoutUserAndWithCustomId(id)
                .user(user);
    }

}
