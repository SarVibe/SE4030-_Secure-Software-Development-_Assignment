package com.faq;

public class Faq {
    private int faq_id;
    private String question;
    private String answer;

    
  /*  Faq(int faq_id , String question )
    {   this.faq_id = faq_id;
    	this.question = question;
    } */
    public int getId() {
        return faq_id;
    }

    public void setId(int faq_id) {
        this.faq_id = faq_id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
    
   
}
