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
            ModuleRepository moduleRepository,
            LessonRepository lessonRepository,
            QuestionRepository questionRepository,
            UserRepository userRepository,
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

        Module ftn = this.moduleRepository.save(
                new Module("Navy Skills 4 Life", "From Seaman to Skaters mate first class in 90 days"));

        Module win = this.moduleRepository.save(
                new Module("Two and a half Men", "Winning!"));

        Lesson fire = this.lessonRepository.save(
                new Lesson("Starting a fire", "We didn't start the fire", "but it keeps on burning", ftn));

        Lesson hope = this.lessonRepository.save(
                new Lesson("Hoping", "We didn't start the fire", "na na nah", ftn));

        Question question1 = this.questionRepository.save(
                new Question("How Now Brown Cow", 1L, "one", "two", "three", "four", fire));

    }
}
