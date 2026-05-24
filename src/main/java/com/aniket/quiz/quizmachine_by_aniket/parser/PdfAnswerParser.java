package com.aniket.quiz.quizmachine_by_aniket.parser;

import java.io.File;
import java.util.*;
import java.util.regex.*;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfAnswerParser {

    public Map<Integer, Integer> parseAnswers(File file) {

        Map<Integer, Integer> answerMap = new HashMap<>();

        try {

            PDDocument document = Loader.loadPDF(file);

            PDFTextStripper stripper = new PDFTextStripper();

            String text = stripper.getText(document);

            document.close();

            System.out.println("ANSWER PDF TEXT");

            System.out.println(text);

            String[] lines = text.split("\\r?\\n");

            Pattern pattern1 = Pattern.compile(
                    "^Q?\\s*(\\d+)\\s*[\\.\\-:]\\s*([A-D])$",
                    Pattern.CASE_INSENSITIVE
            );

            Pattern pattern2 = Pattern.compile(
                    "^(\\d+)\\.\\s*Answer:\\s*([A-D])\\b",
                    Pattern.CASE_INSENSITIVE
            );

            for (String line : lines) {

                line = line.trim();

                Matcher matcher1 = pattern1.matcher(line);

                Matcher matcher2 = pattern2.matcher(line);

                Matcher matcher = null;

                if (matcher1.find()) {

                    matcher = matcher1;

                } else if (matcher2.find()) {

                    matcher = matcher2;
                }

                if (matcher != null) {

                    int questionNumber =
                            Integer.parseInt(matcher.group(1));

                    char optionChar =
                            Character.toUpperCase(
                                    matcher.group(2).charAt(0)
                            );

                    int index = optionChar - 'A';

                    answerMap.put(questionNumber, index);

                    System.out.println(
                            "Parsed Answer -> Q"
                                    + questionNumber
                                    + " = "
                                    + index
                    );
                }
            }

            System.out.println("Final Answer Map: " + answerMap);

        } catch (Exception e) {

            e.printStackTrace();
        }

        return answerMap;
    }
}