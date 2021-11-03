package com.example.simplespringv2;
import static org.assertj.core.api.Assertions.assertThat;
import com.example.simplespringv2.student.StudentController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Simplespringv2ApplicationTests {


	/*
	 * Spring interprets the @Autowired annotation, and the controller is injected
	 * before the test methods are run. We use AssertJ (which provides assertThat() and other methods) to express
	 * the test assertions.
	 * */
	@Autowired
	private StudentController controller;


	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}



}
