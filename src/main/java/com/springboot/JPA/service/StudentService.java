package com.springboot.JPA.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.springboot.JPA.entity.Student;
import com.springboot.JPA.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repository;

	//Fetch all data
	public List<Student> getAllStudentData(){
		List<Student> list = repository.findAll();
		return list;
	}

	//Fetch data by ID using DB Query
	public Student getStudentById(int id){
		return repository.findById(id).get();
	}

	//Insert a new data
	public Student addStudentData(Student s) {
		return repository.save(s);
	}

	public void updateUser(Student s) {
		Student sDB = repository.findById(s.getId()).orElseThrow();
		repository.save(s);
	}

	//Delete data from DB
	public void deleteStudent(int id) {
		try {
			repository.deleteById(id);  
		}catch(DataAccessException ex){
			throw new RuntimeException(ex.getMessage());
		}
	}
}
