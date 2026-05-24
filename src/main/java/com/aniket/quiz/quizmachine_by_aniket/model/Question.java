package com.aniket.quiz.quizmachine_by_aniket.model;
public class Question{
    public String questionText;
    public String[] options = new String[4];
    
    // this will be set using answer parser OR service
    public int correctIndex;
    public int id;
}