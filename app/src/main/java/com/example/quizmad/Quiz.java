package com.example.quizmad;

public class Quiz {

    private int questionID;
    private boolean answer;

    public Quiz(int questionID, boolean answer) {
        this.questionID = questionID;
        this.answer = answer;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
