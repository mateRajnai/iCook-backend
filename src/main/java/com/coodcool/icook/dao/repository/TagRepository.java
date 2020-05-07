package com.coodcool.icook.dao.repository;

import com.coodcool.icook.model.FavoriteRecipe;
import com.coodcool.icook.model.Tag;
import com.coodcool.icook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {



    @Query("SELECT tag FROM Tag tag WHERE tag.user.id = :userId")
    List<Tag> findAllByUserId(@Param("userId") Long userId);



}
