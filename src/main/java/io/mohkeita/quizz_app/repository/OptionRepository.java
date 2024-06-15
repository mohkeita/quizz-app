package io.mohkeita.quizz_app.repository;

import io.mohkeita.quizz_app.model.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option, Long> {
}
