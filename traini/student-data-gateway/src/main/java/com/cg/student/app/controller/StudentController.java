package com.cg.student.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.student.app.dto.Student;


@RestController
public class StudentController {
	@RequestMapping(value = "/createData", method = RequestMethod.POST)
	@ResponseBody
	public Student createStudentData(@RequestBody Student student) {
		final String uri = "http://localhost:8083/mongo/student/create"; 
	    RestTemplate restTemplate = new RestTemplate();
	    Student result = restTemplate.postForObject( uri, student, Student.class);
	    System.out.println(result);
	    return result;
	}
	
	@RequestMapping(value = "/readData", method = RequestMethod.GET)
	@ResponseBody
	public List<Student> readStudentData() {
		final String uri = "http://localhost:8083/mongo/student/read"; 
	    RestTemplate restTemplate = new RestTemplate();
	    @SuppressWarnings("unchecked")
		List<Student> result = restTemplate.getForObject(uri, List.class);
	    System.out.println(result.size());
	    return result;
	}
	@RequestMapping(value = "/updateData/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public Student updateData(@PathVariable ("id")ObjectId id,@Valid @RequestBody Student student) {
		final String uri = "http://localhost:8083/mongo/student/update/{id}"; 
	    RestTemplate restTemplate = new RestTemplate();
	     restTemplate.put( uri, student, id);
	
	    return student;
	}
	@RequestMapping(value = "/deleteData/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String deleteData(@PathVariable ("id")ObjectId id) {
		final String uri = "http://localhost:8083/mongo/student/delete/{id}"; 
	    RestTemplate restTemplate = new RestTemplate();
	     restTemplate.delete( uri, id);
	
	    return "delete success";
	}
}
