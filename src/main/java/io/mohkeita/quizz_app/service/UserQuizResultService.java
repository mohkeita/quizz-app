package io.mohkeita.quizz_app.service;

import io.mohkeita.quizz_app.dto.UserQuizResultDTO;
import io.mohkeita.quizz_app.model.Quiz;
import io.mohkeita.quizz_app.model.User;
import io.mohkeita.quizz_app.model.UserQuizResult;
import io.mohkeita.quizz_app.repository.QuizRepository;
import io.mohkeita.quizz_app.repository.UserQuizResultRepository;
import io.mohkeita.quizz_app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQuizResultService {

    private final UserQuizResultRepository userQuizResultRepository;
    private final UserRepository userRepository;
    private final QuizRepository quizRepository;

    public UserQuizResult submitQuizResult(UserQuizResultDTO resultDTO) {
        User user = userRepository.findById(resultDTO.getUserId())
                .orElseThrow(() -> new IllegalStateException("User not found."));
        Quiz quiz = quizRepository.findById(resultDTO.getQuizId())
                .orElseThrow(() -> new IllegalStateException("Quiz not found."));
        UserQuizResult result = new UserQuizResult();
        result.setUser(user);
        result.setQuiz(quiz);
        result.setScore(resultDTO.getScore());
        return userQuizResultRepository.save(result);
    }
}
