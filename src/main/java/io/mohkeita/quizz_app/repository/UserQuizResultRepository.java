package io.mohkeita.quizz_app.repository;

import io.mohkeita.quizz_app.model.UserQuizResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuizResultRepository extends JpaRepository<UserQuizResult, Long> {
}
