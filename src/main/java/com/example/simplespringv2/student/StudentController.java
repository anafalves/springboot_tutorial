package com.example.simplespringv2.student;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
//@RequestMapping(path="api/v1/student")
public class StudentController {
    
    private final StudentService studentService;

    @Autowired //dependency injection
    public StudentController (StudentService studentService) {
        this.studentService = studentService;
    }

	@GetMapping("/home")
	public List helloHome(){
		return List.of("Hello!!", "World");
	}

	@GetMapping("/students")
	public List<Student> getStudents(){
        return studentService.getStudents();        
	}

	@GetMapping
	public String hello(){
		return "Hello!!";
	}

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
}
