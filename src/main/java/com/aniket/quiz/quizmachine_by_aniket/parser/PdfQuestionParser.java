package com.aniket.quiz.quizmachine_by_aniket.parser;
import com.aniket.quiz.quizmachine_by_aniket.model.Question;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfQuestionParser{
    public List<Question>extractQuestions(File file){
        List<Question>questions = new ArrayList<>();
        try{
            PDDocument document = Loader.loadPDF(file);
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            document.close();
            String[]lines = text.split("\\r?\\n");
            Question currentQuestion = null;
            int optionIndex = 0;
            boolean started = false;
            boolean collectingOptions = false;
            String currentOption = "";
            for(String rawLine : lines){
                String line = rawLine.trim();
                if(line.isEmpty())
                    continue;
                if(!started){
                    if(line.matches("^\\d+\\..*")){
                        started = true;
                    }else{
                        continue;
                    }
                }
                if(line.matches("^\\d+\\..*") && collectingOptions){
                    if(!currentOption.isEmpty() && currentQuestion != null && optionIndex < 4){
                        currentQuestion.options[optionIndex++] = currentOption.trim();
                        currentOption = "";
                    }
                    if(currentQuestion != null && optionIndex == 4){
                        questions.add(currentQuestion);
                    }
                    currentQuestion = new Question();
                    currentQuestion.id = questions.size() + 1;
                    currentQuestion.questionText = line;
                    optionIndex = 0;
                    collectingOptions = false;
                    continue;
                }
                if(line.matches("^\\([a-d]\\).*")){
                    if(!currentOption.isEmpty() && currentQuestion != null && optionIndex < 4){
                        currentQuestion.options[optionIndex++] = currentOption.trim();
                    }
                    currentOption = line;
                    collectingOptions = true;
                    continue;
                }
                if(collectingOptions){
                    if(!line.matches("^\\d+\\..*") && !line.matches("^\\([a-d]\\).*")){
                        currentOption += " " + line;
                        continue;
                    }
                }
                if(currentQuestion == null && line.matches("^\\d+\\..*")){
                    currentQuestion = new Question();
                    currentQuestion.id = questions.size() + 1;
                    currentQuestion.questionText = line;
                    continue;
                }
                if(currentQuestion != null && !collectingOptions){
                    currentQuestion.questionText += " " + line;
                }
            }
            if(!currentOption.isEmpty() && currentQuestion != null && optionIndex < 4){
                currentQuestion.options[optionIndex++] = currentOption.trim();
            }
            if(currentQuestion != null && optionIndex == 4){
                questions.add(currentQuestion);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return questions;
    }
}