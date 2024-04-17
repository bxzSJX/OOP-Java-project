package com.example.oopandroidapi;

public class Question {
    private String questionText;
    private boolean answerTrue;
    private Boolean userAnswer;

    public Question(String questionText, boolean answerTrue){
        this.questionText = questionText;
        this.answerTrue = answerTrue;
        this.userAnswer = null;
    }
    public void setUserAnswer(Boolean userAnswer) {
        this.userAnswer = userAnswer;
    }
    public String getQuestionText(){

        return questionText;
    }
    public Boolean getUserAnswer() {
        return userAnswer;
    }

    public boolean checkAnswer() {
        return userAnswer != null && userAnswer.equals(answerTrue);
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

}

