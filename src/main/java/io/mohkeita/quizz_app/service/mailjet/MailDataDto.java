package io.mohkeita.quizz_app.service.mailjet;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class MailDataDto {

    @NotBlank(message = "email shouldn't be blank")
    private String email;

    @NotBlank(message = "name shouldn't be blank")
    private String name;
}
