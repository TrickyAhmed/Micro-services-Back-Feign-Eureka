package com.omicrone.controller;

import com.omicrone.request.CreateAddressRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.omicrone.repository.StudentRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.omicrone.request.CreateStudentRequest;
import com.omicrone.response.StudentResponse;
import com.omicrone.service.StudentService;
import com.omicrone.feignclients.AddressFeignClient;
import com.omicrone.response.AddressResponse;
import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")  // Applies to all endpoints
@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    
    @Autowired
    AddressFeignClient addressFeignClient;

    // Create a new student
    @PostMapping("/create")
    public StudentResponse createStudent(@RequestBody CreateStudentRequest createStudentRequest) {
        return studentService.createStudent(createStudentRequest);
    }

    // Get all addresses
    @GetMapping("/getAllAddresses")
    public ResponseEntity<List<AddressResponse>> getAllAddresses() {
        List<AddressResponse> addresses = addressFeignClient.getAll();  // Call the Feign client method
        return ResponseEntity.ok(addresses);
    }

    // Get student by ID
    @GetMapping("/getById/{id}")
    public StudentResponse getById(@PathVariable long id) {
        return studentService.getById(id);
    }

    // Get all students
    @GetMapping("/AllStudents")
    public ResponseEntity<List<StudentResponse>> getAllStudents() {
        List<StudentResponse> studentList = studentService.getAllStudents();
        return ResponseEntity.ok(studentList);
    }

    // Get address by ID
    @GetMapping("/address/{id}")
    public AddressResponse getAddressById(@PathVariable long id) {
        return addressFeignClient.getById(id);
    }

    // Create an address
    @PostMapping("/address/create")
    public AddressResponse createAddress(@RequestBody CreateAddressRequest request) {
        return addressFeignClient.createAddress(request);
    }

    // Update address
    @PutMapping("/address/update/{id}")
  public AddressResponse updateAddress(@PathVariable long id, @RequestBody CreateAddressRequest request) {
    return addressFeignClient.updateAddress(id, request);
}

    // Delete address
    @DeleteMapping("/address/delete/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable long id) {
        addressFeignClient.deleteAddress(id);  // Call the Feign client method for delete
        return ResponseEntity.ok("Address deleted successfully");
    }

    // Update student details
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable long id,
                                                         @RequestBody CreateStudentRequest updateStudentRequest) {
        StudentResponse updatedStudent = studentService.updateStudent(id, updateStudentRequest);
        return ResponseEntity.ok(updatedStudent);
    }

    // Delete student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable long id) {
        // Call the delete method from the service
        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }
}
