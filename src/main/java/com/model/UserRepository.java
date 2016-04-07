package com.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

//@RepositoryRestResource(exported = false) //prevents rest
@Repository
@Qualifier(value = "userRepository")
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
}
