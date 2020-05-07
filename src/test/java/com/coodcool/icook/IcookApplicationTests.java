package com.coodcool.icook;

import com.coodcool.icook.model.PersonalNote;
import com.coodcool.icook.repository.PersonalNoteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class IcookApplicationTests {

    @Autowired
    PersonalNoteRepository personalNoteRepository;

    @Test
    public void savePersonalNote() {

        PersonalNote personalNote = PersonalNote.builder()
                .recipeId("1")
                .content("random content")
                .submissionTime(LocalDateTime.now())
                .build();

        personalNoteRepository.save(personalNote);

        List<PersonalNote> personalNoteList = personalNoteRepository.findAll();
        assertThat(personalNoteList).hasSize(1);
    }

}
