package io.mohkeita.quizz_app.repository;

import io.mohkeita.quizz_app.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
