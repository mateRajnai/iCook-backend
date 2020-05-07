package com.coodcool.icook.dao.repository;

import com.coodcool.icook.model.FavoriteRecipe;
import com.coodcool.icook.model.Tag;
import com.coodcool.icook.model.User;
import com.coodcool.icook.mother.FavoriteRecipeMother;
import com.coodcool.icook.mother.TagMother;
import com.coodcool.icook.mother.UserMother;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FavoriteRecipeRepository favoriteRecipeRepository;

    @Test
    public void saveOneCompleteTag() {
        User user = UserMother.withoutAnyRelationsAndId().build();
        FavoriteRecipe recipe = FavoriteRecipeMother.withoutIdAndWithCustomUser(user).build();
        Tag tag = TagMother.withoutIdAndWithCustom(user, recipe).build();
        user.setTags(Set.of(tag));
        user.setFavorites(Set.of(recipe));
        userRepository.save(user);
        List<Tag> tags = tagRepository.findAll();

        assertThat(tags).hasSize(1).containsExactly(tag);

        Tag addedTag = tagRepository.getOne(tag.getId());
        assertEquals(TagMother.getTag(), addedTag.getTag());
        assertThat(addedTag.getUser()).isNotNull();
        assertThat(addedTag.getTaggedFavorites()).isNotEmpty();

    }



    @Test
    public void findAllTagByUserId() {
        // methodba kiszervezni
        User user = UserMother.withoutAnyRelationsAndId().build();
        User user2 = UserMother.withoutAnyRelationsAndId().build();
        Tag tag = TagMother.withoutIdAndWithCustom(user, null).build();
        Tag tag2 = TagMother.withoutIdAndWithCustom(user2, null).tag("lunch").build();

        user.setTags(Set.of(tag));
        user2.setTags(Set.of(tag2));

        userRepository.saveAll(Lists.newArrayList(user, user2));
        List<Tag> tags = tagRepository.findAllByUserId(user.getId());

        assertThat(tags).hasSize(1).containsExactly(tag);
    }



}