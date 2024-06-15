package io.mohkeita.quizz_app.controller;

import io.mohkeita.quizz_app.dto.UserQuizResultDTO;
import io.mohkeita.quizz_app.model.UserQuizResult;
import io.mohkeita.quizz_app.service.UserQuizResultService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("results")
@RequiredArgsConstructor
@Tag(name = "Results")
public class UserQuizResultController {

    private final UserQuizResultService userQuizResultService;

    @PostMapping
    public ResponseEntity<?> submitQuizResult(@RequestBody UserQuizResultDTO resultDTO) {
        UserQuizResult result = userQuizResultService.submitQuizResult(resultDTO);
        return ResponseEntity.ok(result);
    }
}
