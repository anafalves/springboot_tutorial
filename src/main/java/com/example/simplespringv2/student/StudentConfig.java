package com.example.simplespringv2.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args-> {

            Student student0 = new Student ("mariam", "mj@gmail.com", LocalDate.of(2000, Month.APRIL,28) );
            Student student1 = new Student ("ana", "ana@gmail.com",  LocalDate.of(1995, Month.APRIL,28) );
            
            

            
            repository.saveAll(
                List.of(student0, student1)
            );
        };
    }
}
