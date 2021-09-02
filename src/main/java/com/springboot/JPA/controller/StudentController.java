package com.springboot.JPA.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.JPA.entity.Student;
import com.springboot.JPA.service.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired  
	private StudentService service;  

	//Fetch all data
	@RequestMapping(method=RequestMethod.GET, value="/student-info")  
	public List<Student> fetchAllStudents()  
	{  
		return service.getAllStudentData();  
	} 

	//Fetch data by id 
	@RequestMapping(method=RequestMethod.GET,value="/student-info/{id}")  
	public Student getStudentById(@PathVariable("id") int id)  
	{  		
		Student obj = service.getStudentById(id);
		return obj;
	} 

	//Insert data to table
	@RequestMapping(value = "/addStudentData",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public String addStudentData(@RequestBody Student s) {
		try {
		service.addStudentData(s);
		return "Data added successfully";
		}catch(NoSuchElementException ex){
			System.out.println(ex.getMessage());
			return "Can't add data !! Something went wrong !";
		}
	}

	// Update user record
	@RequestMapping(value = "/updateStudentData",method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE)
	public String updateUser(@RequestBody Student s) {  
		try {
			service.updateUser(s);
			return "Data updated successfully";
		}catch(NoSuchElementException ex){
			System.out.println(ex.getMessage());
			return "Can't update data !! Something went wrong !";
		}
	}

	//Delete a data
	@RequestMapping(method=RequestMethod.DELETE,value="/student-info-delete/{id}")  
	public String deleteStudent(@PathVariable("id") int id)  
	{  
		try {
		      service.deleteStudent(id);
		      return "Data deleted successfully !!";
		    }catch(RuntimeException ex){
		      System.out.println(ex.getMessage());
		      return "Can't delete data !! Something went wrong !";
		    }
	} 

}
