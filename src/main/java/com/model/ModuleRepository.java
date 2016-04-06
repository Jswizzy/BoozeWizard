package com.model;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Qualifier(value = "moduleRepository")
public interface ModuleRepository extends JpaRepository<Module, Long> {

    List<Module> findByNameStartsWithIgnoreCase(String name);
}