package com.coodcool.icook.dao.repository;

import com.coodcool.icook.model.User;
import com.coodcool.icook.mother.UserMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void saveSimpleOne() {
        User user = UserMother.withoutAnyRelationsAndId().build();
        userRepository.save(user);

        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(1);
    }

    @Test public void saveSimpleOneWithIgnoredCustomIdIfNotExists() {
        User user = UserMother.withoutAnyRelationsAndWithCustomId(12345678L).build();
        userRepository.save(user);

        List<User> users = userRepository.findAll();
        assertThat(users).hasSize(1);
        assertThat(users.get(0).getId()).isNotEqualTo(12345678L);
    }

    @Test public void updateSimpleOneWithCustomIdIfExists() {
        User user = UserMother.withoutAnyRelationsAndId().build();
        userRepository.save(user);

        List<User> users = userRepository.findAll();
        Long userId = users.get(0).getId();

        User updatedUser = UserMother
                .withoutAnyRelationsAndWithCustomId(userId)
                .userName("nameChanger")
                .build();

        userRepository.save(updatedUser);

        List<User> updatedUsers = userRepository.findAll();
        assertThat(updatedUsers).hasSize(1);
        assertThat(updatedUsers.get(0).getId()).isEqualTo(userId);
        assertThat(updatedUsers.get(0).getUserName()).isEqualTo("nameChanger");
    }
}
