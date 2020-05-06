package com.coodcool.icook.dao.repository;

import com.coodcool.icook.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
