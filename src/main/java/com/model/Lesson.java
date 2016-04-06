package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "lesson")
public class Lesson {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private Long number;

    private String lessonvid;

//    @ManyToOne(targetEntity = Module.class)
//    @JoinColumn(name = "module_id")
    private Long moduleId;

    protected Lesson() {
    }

    public Lesson(
            String name,
            String description,
            Long number,
            String lessonvid,
            Long moduleId
    ) {
        this.name = name;
        this.description = description;
        this.number = number;
        this.lessonvid = lessonvid;
        this.moduleId = moduleId;
    }

    public Long getModuleId() {
        return moduleId;
    }

    public void setModuleId(Long moduleId) {
        this.moduleId = moduleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getLessonvid() {
        return lessonvid;
    }

    public void setLessonvid(String lessonvid) {
        this.lessonvid = lessonvid;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", number=" + number +
                ", lessonvid='" + lessonvid + '\'' +
                ", moduleId=" + moduleId +
                '}';
    }
}
