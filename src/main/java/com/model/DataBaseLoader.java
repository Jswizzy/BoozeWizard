package com.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataBaseLoader implements CommandLineRunner{

    private final UserRepository userRepository;
    private final ModuleRepository moduleRepository;
    private final LessonRepository lessonRepository;
    private final QuestionRepository questionRepository;
    private final ScoreRepository scoreRepository;

    @Autowired
    public DataBaseLoader(
            UserRepository userRepository,
            ModuleRepository moduleRepository,
            LessonRepository lessonRepository,
            QuestionRepository questionRepository,
            ScoreRepository scoreRepository
    ) {
        this.userRepository = userRepository;
        this.moduleRepository = moduleRepository;
        this.lessonRepository = lessonRepository;
        this.questionRepository = questionRepository;
        this.scoreRepository = scoreRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        this.userRepository.save(new User("Frodo", "Baggins", "ADMIN"));
        this.userRepository.save(new User("Frodo", "Baggins", "USER"));


    }
}
