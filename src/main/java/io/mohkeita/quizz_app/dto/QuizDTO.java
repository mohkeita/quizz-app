package io.mohkeita.quizz_app.dto;

import io.mohkeita.quizz_app.model.Category;
import io.mohkeita.quizz_app.model.DifficultyLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class QuizDTO {
    @NotBlank
    private String title;

    @NotBlank
    private String description;

    @NotNull
    private Category category;

    @NotNull
    private DifficultyLevel difficultyLevel;

    @NotEmpty
    private List<QuestionDTO> questions;
}
