package com.coodcool.icook.dao.repository;

import com.coodcool.icook.model.PersonalNote;
import com.coodcool.icook.model.User;
import com.coodcool.icook.mother.PersonalNoteMother;
import com.coodcool.icook.mother.UserMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class PersonalNoteRepositoryTest {

    @Autowired
    PersonalNoteRepository personalNoteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

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

    @Test
    public void getPersonalNotesByUserId() {

        PersonalNote personalNote1 = PersonalNoteMother.withoutUserAndId().build();
        PersonalNote personalNote2 = PersonalNoteMother.withoutUserAndId().content("random content").build();
        PersonalNote personalNote3 = PersonalNoteMother.withoutUserAndId().build();

        User user = UserMother.withoutAnyRelationsAndId().note(personalNote1).note(personalNote2).build();
        User user2 = UserMother.withoutAnyRelationsAndId().note(personalNote3).build();

        personalNote1.setUser(user);
        personalNote2.setUser(user);
        personalNote3.setUser(user2);

        userRepository.save(user);
        userRepository.save(user2);

        List<PersonalNote> personalNotes = personalNoteRepository
                .getAllByUser(user);

        assertThat(personalNotes).hasSize(2);
    }
}
