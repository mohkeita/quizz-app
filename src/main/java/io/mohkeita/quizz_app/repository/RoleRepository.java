package io.mohkeita.quizz_app.repository;

import io.mohkeita.quizz_app.model.Role;
import io.mohkeita.quizz_app.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
