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
public class QuestionDTO {
    @NotBlank
    private String text;

    @NotEmpty
    private List<OptionDTO> options;

    @NotBlank
    private String correctAnswer;
}
