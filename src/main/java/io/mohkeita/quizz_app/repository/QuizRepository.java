package io.mohkeita.quizz_app.repository;

import io.mohkeita.quizz_app.model.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
