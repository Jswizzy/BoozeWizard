package com.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(value = "scoreRepository")
public interface ScoreRepository extends PagingAndSortingRepository<User, Long> {
}
