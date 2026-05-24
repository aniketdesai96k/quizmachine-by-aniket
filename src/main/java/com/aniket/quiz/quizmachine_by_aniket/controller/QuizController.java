package com.aniket.quiz.quizmachine_by_aniket.controller;
import com.aniket.quiz.quizmachine_by_aniket.model.Question;
import com.aniket.quiz.quizmachine_by_aniket.model.QuestionDTO;
import com.aniket.quiz.quizmachine_by_aniket.model.UserResponse;
import com.aniket.quiz.quizmachine_by_aniket.model.QuizResult;
import com.aniket.quiz.quizmachine_by_aniket.service.QuizService;
import com.aniket.quiz.quizmachine_by_aniket.service.QuizStore;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController{
    private final QuizService quizService;
    public QuizController(QuizService quizService){
        this.quizService = quizService;
    }

    // this is where i get Questions
    @GetMapping("/questions")
    public List<QuestionDTO> getQuestions(){
        List<Question>originalQuestions = quizService.getQuestions();
        List<QuestionDTO>safeQuestions = new ArrayList<>();
        for (Question q : originalQuestions){
            QuestionDTO dto = new QuestionDTO();
            dto.id = q.id;
            dto.questionText = q.questionText;
            dto.options = q.options;
            safeQuestions.add(dto);
        }
        return safeQuestions;
    }

    //this is where i submit the quiz
    //@PostMapping("/submit")
    //public QuizResult submitQuiz(@RequestBody UserResponse userResponse){
      //  return quizService.evaluateQuiz(userResponse);
    //}
    @PostMapping("/submit")
    public QuizResult submitQuiz(@RequestBody UserResponse userResponse){
        System.out.println("User Answers: " + userResponse.answers);
        QuizResult result = quizService.evaluateQuiz(userResponse);
        System.out.println("Score: " + result.score);
        return result;
    }

    //this is where i start the quiz
    @GetMapping("/start")
    public List<QuestionDTO>startQuiz(){
        List<Question>questions = QuizStore.getInstance().getQuestions();
        List<QuestionDTO>dtos = new ArrayList<>();
        for (Question q : questions){
            QuestionDTO dto = new QuestionDTO();
            dto.id = q.id;
            dto.questionText = q.questionText;
            dto.options = q.options;
            dtos.add(dto);
        }
        return dtos;
    }
    @GetMapping("/answer/{id}")
    public int getCorrectAnswer(@PathVariable int id){
        return QuizStore
        .getInstance()
        .getAnswers()
        .get(id);
    }
}