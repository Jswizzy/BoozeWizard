package com.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "scoreRepository")
public interface ScoreRepository extends JpaRepository<Score, Long> {
}
