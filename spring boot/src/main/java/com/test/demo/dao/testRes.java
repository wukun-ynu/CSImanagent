package com.test.demo.dao;

import com.test.demo.entity.test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface testRes extends JpaRepository<test, Long> {
    List<test> findAll();
}
