package com.simplize.springboot.configs;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.simplize.springboot.modules.student.entities.Student;
import com.simplize.springboot.modules.student.repositoies.StudentRepository;

@Configuration
public class StudentConfig {
    
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {

           studentRepository.saveAll(
            List.of(
                new Student("Nguyễn Tiến Đạt", 23,"dat.nt@gmail.com", LocalDate.of(2000, Month.APRIL, 10)),
                new Student("Nguyễn Thị Hoàn", 20,"hoan.nt@gmail.com", LocalDate.of(2003, Month.JULY, 30)))
           );
        };
    }
}
