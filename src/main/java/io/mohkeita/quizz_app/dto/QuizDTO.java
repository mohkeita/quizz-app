package io.mohkeita.quizz_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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

    @NotEmpty
    private List<QuestionDTO> questions;
}
