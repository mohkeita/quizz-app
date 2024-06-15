package io.mohkeita.quizz_app.controller;

import io.mohkeita.quizz_app.dto.QuizDTO;
import io.mohkeita.quizz_app.model.Option;
import io.mohkeita.quizz_app.model.Question;
import io.mohkeita.quizz_app.model.Quiz;
import io.mohkeita.quizz_app.service.QuizService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("quizzes")
@RequiredArgsConstructor
@Tag(name = "Quiz")
public class QuizController {

    private final QuizService quizService;

    @PostMapping
    public ResponseEntity<?> createQuiz(@RequestBody QuizDTO quizDTO) {
        Quiz quiz = new Quiz();
        quiz.setTitle(quizDTO.getTitle());
        quiz.setDescription(quizDTO.getDescription());
        quiz.setQuestions(quizDTO.getQuestions().stream().map(q -> {
            Question question = new Question();
            question.setText(q.getText());
            question.setCorrectAnswer(q.getCorrectAnswer());
            question.setOptions(q.getOptions().stream().map(o -> {
                Option option = new Option();
                option.setText(o.getText());
                option.setIsCorrect(o.getIsCorrect());
                return option;
            }).collect(Collectors.toList()));
            return question;
        }).collect(Collectors.toList()));
        Quiz createdQuiz = quizService.createQuiz(quiz);
        return ResponseEntity.ok(createdQuiz);
    }

    @GetMapping
    public ResponseEntity<?> getAllQuizzes() {
        List<Quiz> quizzes = quizService.getAllQuizzes();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getQuiz(@PathVariable Long id) {
        Quiz quiz = quizService.getQuiz(id);
        return ResponseEntity.ok(quiz);
    }
}
