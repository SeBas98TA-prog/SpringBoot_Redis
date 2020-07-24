package com.redis.redispostman.redisPostman;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CachePut;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/redis/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

	@PostMapping
	public String saveNewPincode(@RequestBody Student student) {
		studentService.save(student);
		return "Añadido a: " + student.getname();
		
	}


	@GetMapping(path = "/{id}")
	public Student fetchStudent(@PathVariable("id") long id) {
		return studentService.findById(id);
	}
	
	
	@DeleteMapping(path = "/{id}")
	public String deleteOldPincode(@PathVariable("id") long id) {
		studentService.delete(id);
		return "Eliminado correctamente";  
	}
	

	@GetMapping
	public  Map<Long, Student> fetchAllStudetns() {
		return studentService.findAllStudents();
	}
	

	@PutMapping(path = "{id}")
	public String updateOStudent(@PathVariable("id") long id,@RequestBody Student student) {
		studentService.update(id, student);
		return "Actualizado correctamente";  
	}
	
}
