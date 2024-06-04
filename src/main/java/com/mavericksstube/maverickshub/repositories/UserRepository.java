package com.mavericksstube.maverickshub.repositories;

import com.mavericksstube.maverickshub.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
