package com.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
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
    @ManyToOne
    private Lesson lesson;

    protected Question() {}

    public Question(String question_text, Long answer, String a, String b, String c, String d, Lesson lesson) {
        this.question_text = question_text;
        this.answer = answer;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.lesson = lesson;
    }
}
