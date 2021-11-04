package com.example.simplespringv2.student;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path="api/students")
public class StudentController {
    
    private final StudentService studentService;

    @Autowired //dependency injection
    public StudentController (StudentService studentService) {
        this.studentService = studentService;
    }

//	@GetMapping("/students")
//	public List<Student> getStudents(){
//        return studentService.getStudents();
//	}



	@PostMapping
	public void registerNewStudent(@RequestBody Student student){
		studentService.addNewStudent(student);
	}

	@DeleteMapping(path="{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long id){
		studentService.deleteStudent(id);
	}

	@PutMapping(path = "{studentId}")//does not need queries
	public void updateStudent(@PathVariable("studentId") Long id,
							  @RequestParam(required = false) String name,
							  @RequestParam(required = false) String email
							  ){
		studentService.updateStudent(id, name, email);
	}

	@GetMapping("/students")
	//@CrossOrigin(originPatterns = "*")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<List<Student>>getStudents(){
		return new ResponseEntity<> (studentService.getStudents(), HttpStatus.OK);
	}

	@GetMapping("/students/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Student>getStudentById(@PathVariable("id") Long id){
		return new ResponseEntity<> (studentService.findStudentById(id), HttpStatus.OK);
	}


}
