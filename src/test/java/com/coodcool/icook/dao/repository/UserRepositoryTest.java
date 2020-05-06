package com.coodcool.icook.dao.repository;

import com.coodcool.icook.model.*;
import com.coodcool.icook.mother.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.TableGenerator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Test
    public void saveOneCompleteUser() {
        User user = buildCompleteUser();
        Long userId = userRepository.saveAndFlush(user).getId();

        Optional<User> queriedUser = userRepository.findById(userId);
        assertThat(queriedUser).isNotEmpty();
        assertThat(queriedUser.get().getAddress()).isNotNull();
        assertThat(queriedUser.get().getAddress().getCity()).isEqualTo("Gyongyos");

        assertThat(queriedUser.get().getComments()).hasSize(1);
        assertThat(queriedUser.get().getFavorites()).hasSize(1);
        assertThat(queriedUser.get().getFavorites().iterator().next().getTags()).hasSize(1);
        assertThat(queriedUser.get().getNotes()).hasSize(1);
        assertThat((queriedUser.get().getTags())).hasSize(1);

    }

    private User buildCompleteUser() {
        User user = UserMother.withoutAnyRelationsAndId().build();

        Address address = AddressMother
                .withoutIdAndWithCustom(user)
                .build();
        user.setAddress(address);

        FavoriteRecipe recipe = FavoriteRecipeMother
                .withoutIdAndWithCustomUser(user)
                .build();
        Tag tag = TagMother.withoutIdAndWithCustom(user, recipe).build();
        user.setTags(Set.of(tag));
        recipe.setTags(List.of(tag));
        user.setFavorites(Set.of(recipe));

        PersonalNote note = PersonalNoteMother
                .withoutIdAndWithCustomUser(user)
                .build();
        user.setNotes(Set.of(note));

        Comment comment = CommentMother
                .withoutIdAndWithCustomUser(user)
                .build();
        user.setComments(Set.of(comment));

        return user;
    }
}
