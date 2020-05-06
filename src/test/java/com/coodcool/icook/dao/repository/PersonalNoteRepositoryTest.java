package com.coodcool.icook.dao.repository;

import com.coodcool.icook.model.PersonalNote;
import com.coodcool.icook.model.User;
import com.coodcool.icook.mother.PersonalNoteMother;
import org.assertj.core.util.Lists;
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
                .recipeId((long) 1)
                .content("random content")
                .submissionTime(LocalDateTime.now())
                .build();

        personalNoteRepository.save(personalNote);

        List<PersonalNote> personalNoteList = personalNoteRepository.findAll();
        assertThat(personalNoteList).hasSize(1);
    }

    @Test
    public void getPersonalNotesByUserId() {

        PersonalNote personalNote1 = PersonalNoteMother.completePersonalNote().build();
        PersonalNote personalNote2 = PersonalNoteMother.completePersonalNote().build();
        PersonalNote personalNote3 = PersonalNoteMother.completePersonalNote().build();

        User user = PersonalNoteMother.getDummyUser().note(personalNote1).note(personalNote2).build();
        User user2 = PersonalNoteMother.getDummyUser2().note(personalNote3).build();

        personalNote1.setUser(user);
        personalNote2.setUser(user);
        personalNote3.setUser(user2);

        userRepository.saveAndFlush(user);
        userRepository.saveAndFlush(user2);

        List<PersonalNote> personalNotes = personalNoteRepository
                .getPersonalNotesByUser_Id(user.getId());

        assertThat(personalNotes).hasSize(2);
    }
}
