package io.mohkeita.quizz_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class OptionDTO {
    @NotBlank
    private String text;

    @NotNull
    private Boolean isCorrect;
}
