package com.coodcool.icook;

import com.coodcool.icook.model.PersonalNote;
import com.coodcool.icook.repository.PersonalNoteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class IcookApplicationTests {

    @Autowired
    PersonalNoteRepository personalNoteRepository;

    @Test
    public void savePersonalNote() {

        PersonalNote personalNote = PersonalNote.builder()
                .recipeId((long) 1)
                .content("random content")
                .submissionTime(LocalDateTime.now())
                .build();

        personalNoteRepository.save(personalNote);

        List<PersonalNote> personalNoteList = personalNoteRepository.findAll();
        assertThat(personalNoteList).hasSize(1);
    }

}
