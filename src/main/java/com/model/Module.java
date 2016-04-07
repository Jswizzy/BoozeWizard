package com.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Module {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    @OneToMany(mappedBy = "module", fetch = FetchType.EAGER)
    private List<Lesson> lessons = new ArrayList<>();

    protected Module() {

    }

    public Module( String name, String description) {
        this.description = description;
        this.name = name;
    }

}
