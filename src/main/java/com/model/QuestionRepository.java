package com.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "questionRepository")
public interface QuestionRepository extends JpaRepository<Question, Long> {

//    List<Question> findByQuestion_text(String question_text);
}
