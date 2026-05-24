package com.aniket.quiz.quizmachine_by_aniket.service;
import org.springframework.stereotype.Service;
import com.aniket.quiz.quizmachine_by_aniket.model.*;
import java.util.List;
import java.util.Map;
import java .util.*;

@Service
public class QuizService{
    private final QuizStore store = QuizStore.getInstance();
    public List<Question> getQuestions(){
        if(!store.isQuizLoaded()){
            //this is to throw a new Runtime Exception("Quiz not loaded. Upload PDF first.");
            return new ArrayList<>();
        }
        return store.getQuestions();
    }
    public QuizResult evaluateQuiz(UserResponse userResponse){
        if(!store.isQuizLoaded()){
            //again this is to throw a new Runtime Exception("Quiz not loaded. Upload PDF first.");
            return new QuizResult(0,0);
        }
        List<Question>questions = store.getQuestions();
        Map<Integer, Integer> answers = store.getAnswers();
        int score = 0;

        for(int i = 0; i < questions.size(); i++){
            int correct = answers.getOrDefault(i + 1, -1);
            if(userResponse.answers != null && userResponse.answers.containsKey(i+1)){
                int userAnswer = userResponse.answers.get(i+1);
                if(userAnswer == correct){
                    score++;
                }
            }
        }
        return new QuizResult(score, questions.size());
    }
}