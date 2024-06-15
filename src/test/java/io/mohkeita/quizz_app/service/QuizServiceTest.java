package io.mohkeita.quizz_app.service;

import io.mohkeita.quizz_app.model.Quiz;
import static org.mockito.Mockito.*;
import io.mohkeita.quizz_app.repository.QuizRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class QuizServiceTest {

    @InjectMocks
    private QuizService quizService;

    @Mock
    private QuizRepository quizRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateQuiz_successful() {
        Quiz quiz = Quiz.builder()
                .title("Sample Quiz")
                .description("This is a sample quiz")
                .build();


        when(quizRepository.save(any(Quiz.class))).thenReturn(quiz);

        Quiz createdQuiz = quizService.createQuiz(quiz);

        assertNotNull(createdQuiz);
        assertEquals("Sample Quiz", createdQuiz.getTitle());
    }

    @Test
    public void testGetAllQuizzes_successful() {
        List<Quiz> quizzes = new ArrayList<>();
        quizzes.add(new Quiz());
        quizzes.add(new Quiz());

        when(quizRepository.findAll()).thenReturn(quizzes);

        List<Quiz> result = quizService.getAllQuizzes();

        assertNotNull(result);
        assertEquals(2, result.size());
    }

    @Test
    public void testGetQuizById_successful() {
        Quiz quiz = new Quiz();
        quiz.setId(1L);

        when(quizRepository.findById(1L)).thenReturn(Optional.of(quiz));

        Quiz result = quizService.getQuiz(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testGetQuizById_notFound() {
        when(quizRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> {
            quizService.getQuiz(1L);
        });

        String expectedMessage = "Quiz not found.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}