package com.simplize.springboot.repositoies;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.simplize.springboot.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // select * from student where email = ?
    // @Query("SELECT s from Student s WHERE s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
}
