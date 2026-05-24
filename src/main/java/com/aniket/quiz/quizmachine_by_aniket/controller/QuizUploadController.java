package com.aniket.quiz.quizmachine_by_aniket.controller;
import com.aniket.quiz.quizmachine_by_aniket.model.Question;
import com.aniket.quiz.quizmachine_by_aniket.parser.PdfAnswerParser;
import com.aniket.quiz.quizmachine_by_aniket.parser.PdfQuestionParser;
import com.aniket.quiz.quizmachine_by_aniket.service.QuizStore;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/quiz/upload")
public class QuizUploadController{
    private final PdfQuestionParser questionParser = new PdfQuestionParser();
    private final PdfAnswerParser answerParser = new PdfAnswerParser();

    @PostMapping
    public String uploadCombined(@RequestParam("file") MultipartFile file){
        try{
            File tempFile = File.createTempFile("quiz_", ".pdf");
            file.transferTo(tempFile);
            List<Question>questions = questionParser.extractQuestions(tempFile);
            Map<Integer, Integer>answers = answerParser.parseAnswers(tempFile);
            QuizStore.getInstance().setQuiz(questions, answers);
            System.out.println("Combined Upload");
            System.out.println("Questions Loaded: " + questions.size());
            System.out.println("Answers Loaded: " + answers);
            return "Combined PDF uploaded successfully";
        }catch(Exception e){
            e.printStackTrace();
            return "Upload failed: " + e.getMessage();
        }
    }

    @PostMapping("/questions")
    public String uploadQuestions(
            @RequestParam("file") MultipartFile file){
        try{
            File tempFile = File.createTempFile("questions_", ".pdf");
            file.transferTo(tempFile);
            List<Question>questions = questionParser.extractQuestions(tempFile);
            QuizStore store = QuizStore.getInstance();
            Map<Integer, Integer>existingAnswers = store.getAnswers();
            if(existingAnswers == null){
                existingAnswers = new HashMap<>();
            }
            store.setQuiz(questions, existingAnswers);
            System.out.println("Questions Upload");
            System.out.println("Questions Loaded: " + questions.size());
            return "Questions uploaded successfully";
        }catch(Exception e){
            e.printStackTrace();
            return "Upload failed:"+ e.getMessage();
        }
    }

    @PostMapping("/answers")
    public String uploadAnswers(
            @RequestParam("file") MultipartFile file){
        try{
            File tempFile = File.createTempFile("answers_", ".pdf");
            file.transferTo(tempFile);
            Map<Integer, Integer>answers = answerParser.parseAnswers(tempFile);
            QuizStore store = QuizStore.getInstance();
            List<Question>existingQuestions = store.getQuestions();
            store.setQuiz(existingQuestions, answers);
            System.out.println("Answers Upload");
            System.out.println("Answers Loaded:"+answers);
            return "Answers uploaded successfully";
        }catch(Exception e){
            e.printStackTrace();
            return "Upload failed:"+ e.getMessage();
        }
    }
    @GetMapping("/debug")
    public QuizStore debugQuiz(){
        return QuizStore.getInstance();
    }
}