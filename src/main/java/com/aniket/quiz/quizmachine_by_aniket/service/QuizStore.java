package com.aniket.quiz.quizmachine_by_aniket.service;
import com.aniket.quiz.quizmachine_by_aniket.model.Question;
import java.util.List;
import java.util.Map;

public class QuizStore{
    private List<Question>questions;
    private Map<Integer, Integer>answers;
    private static QuizStore instance;
    private QuizStore(){}
    public static QuizStore getInstance(){
        if(instance == null){
            instance = new QuizStore();
        }
        return instance;
    }
    public void setQuiz(List<Question>questions, Map<Integer, Integer>answers){
        this.questions = questions;
        this.answers = answers;
    }
    public List<Question>getQuestions(){
        return questions;
    }
    public Map<Integer, Integer>getAnswers(){
        return answers;
    }
    public boolean isQuizLoaded(){
        return questions != null && answers != null;
    }
}