package com.model;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "module")
public class Module {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    @CreatedDate
    private DateTime createdDate = new DateTime();

    @LastModifiedDate
    private DateTime updatedDate;

//    @OneToMany(mappedBy = "module", cascade = CascadeType.ALL, targetEntity = Lesson.class)
//    private Set<Lesson> lessons = new HashSet<>();


    protected Module() {

    }

    public Module(String description, String name) {
        this.description = description;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

//    public Set<Lesson> getLessons() {
//        return lessons;
//    }
//
//    public void setLessons(Set<Lesson> lessons) {
//        this.lessons = lessons;
//    }


    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public DateTime getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(DateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public String toString() {
        return "Module{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }
}
