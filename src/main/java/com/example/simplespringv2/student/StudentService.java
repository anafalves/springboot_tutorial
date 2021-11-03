package com.example.simplespringv2.student;


import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class StudentService { //spring bean
    
	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository){
		this.studentRepository = studentRepository;
	}

    public List<Student> getStudents(){
        return studentRepository.findAll();
	}


    public void addNewStudent(Student student) {
		confirmEmail(student);
		studentRepository.save(student);
    }

	public void deleteStudent(Long id) {
		boolean exists = studentRepository.existsById(id);
		if(!exists) {
			throw new IllegalStateException(
					"student with id " + id + "does not exist"
			);
		}
		studentRepository.deleteById(id);
	}

	@Transactional//does not need queries => Entity manages itself
	public void updateStudent(Long id, String name, String email) {
		Student student = studentRepository.findById(id).orElseThrow(
				() -> new IllegalStateException(
						"student with id " + id + "does not exist"
				)
		);

		if (email != null && name.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			confirmEmail(student);
			student.setEmail(email);
		}

		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
	}

	private void confirmEmail(Student student){
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

		if(studentByEmail.isPresent()) {
			throw new IllegalStateException("email taken");
		}
	}
}
