package com.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Lesson {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private String lessonvid;
    @ManyToOne
    private Module module;
    @OneToMany(mappedBy = "lesson", fetch = FetchType.EAGER)
    private List<Question> questions = new ArrayList<>();

    protected Lesson() {
    }

    public Lesson(String name, String description, String lessonvid, Module module) {
        this.name = name;
        this.description = description;
        this.lessonvid = lessonvid;
        this.module = module;
    }
}
