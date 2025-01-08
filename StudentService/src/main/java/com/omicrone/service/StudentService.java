package com.omicrone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import com.omicrone.repository.StudentRepository;
import com.omicrone.entity.Student;
import com.omicrone.exceptions.StudentNotFoundException;
import com.omicrone.feignclients.AddressFeignClient;
import com.omicrone.request.CreateAddressRequest;
import com.omicrone.request.CreateStudentRequest;
import com.omicrone.response.AddressResponse;
import com.omicrone.response.StudentResponse;
@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	WebClient webClient;

	@Autowired
	AddressFeignClient addressFeignClient;

	public List<StudentResponse> getAllStudents() {
		List<StudentResponse> studentResponses = new ArrayList<>();
		for(Student s : studentRepository.findAll())
		{
			StudentResponse studentResponse = new StudentResponse(s);
			studentResponse.setAddressResponse(addressFeignClient.getById(s.getAddressId()));
			studentResponses.add(studentResponse);
		}
		return studentResponses;
	}

public StudentResponse getById(long id) {
		Optional<Student> studentOpt = studentRepository.findById(id);
		if (studentOpt.isPresent()) {
			Student student = studentRepository.findById(id).get();
			StudentResponse studentResponse = new StudentResponse(student);
			studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));
			return studentResponse;
		} else
			throw new StudentNotFoundException("Etudiant non existant !!!");
	}

	public StudentResponse updateStudent(long id, CreateStudentRequest updateStudentRequest) {
		// Check if the student exists
		if (!studentRepository.existsById(id)) {
			throw new RuntimeException("Student not found with ID " + id);
		}

		// Get the existing student
		Student student = studentRepository.findById(id).orElseThrow(() ->
				new RuntimeException("Student not found with ID " + id)
		);

		// Update student fields
		student.setFirstName(updateStudentRequest.getFirstName());
		student.setLastName(updateStudentRequest.getLastName());
		student.setEmail(updateStudentRequest.getEmail());

		// Update the student's addressId field
		if (updateStudentRequest.getAddressId() != null) {
			student.setAddressId(updateStudentRequest.getAddressId());  // Just set the addressId
		}

		// Save the updated student to the database
		student = studentRepository.save(student);

		// Convert to response DTO
		return new StudentResponse(student);
	}

	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		student.setAddressId(createStudentRequest.getAddressId());
		student = studentRepository.save(student);

		StudentResponse studentResponse = new StudentResponse(student);

		return studentResponse;
	}

	public void deleteStudent(long id) {
		// Check if the student exists
		if (!studentRepository.existsById(id)) {
			throw new StudentNotFoundException("Student not found with ID " + id);
		}

		// Get the existing student
		Student student = studentRepository.findById(id).orElseThrow(() ->
				new StudentNotFoundException("Student not found with ID " + id)
		);

		// Delete the student from the database
		studentRepository.deleteById(id);
	}
}
