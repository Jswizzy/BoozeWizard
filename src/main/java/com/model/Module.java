package com.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Module {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;

    protected Module() {

    }

    public Module(String description, String name) {
        this.description = description;
        this.name = name;
    }

}
