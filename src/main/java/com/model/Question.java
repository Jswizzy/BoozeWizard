package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue
    private Long id;

    private String question_text;

    private Long answer;

    private String a;

    private String b;

    private String c;

    private String d;

    private Long lessonId;

    protected Question() {
    }

    public Question(
            String question_text,
            Long answer,
            String a,
            String b,
            String c,
            String d,
            Long lessonId
    ) {
        this.question_text = question_text;
        this.answer = answer;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.lessonId = lessonId;
    }

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion_text() {
        return question_text;
    }

    public void setQuestion_text(String question_text) {
        this.question_text = question_text;
    }

    public Long getAnswer() {
        return answer;
    }

    public void setAnswer(Long answer) {
        this.answer = answer;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question_text='" + question_text + '\'' +
                ", answer=" + answer +
                ", a='" + a + '\'' +
                ", b='" + b + '\'' +
                ", c='" + c + '\'' +
                ", d='" + d + '\'' +
                ", lessonId=" + lessonId +
                '}';
    }
}
