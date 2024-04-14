package com.example.oopandroidapi;

public class Question {
    private String questionText;
    private boolean answerTrue;

    public Question(String questionText, boolean answerTrue){
        this.questionText = questionText;
        this.answerTrue = answerTrue;
    }
    public String getQuestionText(){
        return questionText;
    }
    public void setQuestionText(String questionText){
        this.questionText = questionText;
    }
    public boolean isAnswerTrue(){
        return answerTrue;
    }
    public void setAnswerTrue(boolean answerTrue){
        this.answerTrue = answerTrue;
    }
    public boolean checkAnswer(boolean userAnswer){
        return userAnswer == answerTrue;
    }
}

