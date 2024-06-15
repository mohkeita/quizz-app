package io.mohkeita.quizz_app.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserQuizResultDTO {
    @NotNull
    private Long userId;

    @NotNull
    private Long quizId;

    @NotNull
    private Integer score;
}
