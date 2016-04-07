package com.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Data
@Entity
public class Score {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    @ManyToOne
    private Lesson lesson;
    @ManyToOne
    private Module module;

    public Score() {}

    public Score(User user, Lesson lesson, Module module) {
        this.user = user;
        this.lesson = lesson;
        this.module = module;
    }
}
