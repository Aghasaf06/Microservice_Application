package microserviceapplication.controller;

import lombok.RequiredArgsConstructor;
import microserviceapplication.dto.StudentResponse;
import microserviceapplication.dto.request.CreateStudentRequest;
import microserviceapplication.dto.request.UpdateStudentRequest;
import microserviceapplication.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public void createStudent(@RequestBody CreateStudentRequest request) {
        studentService.createStudent(request);
    }

    @GetMapping
    public List<StudentResponse> getAllStudent() {
        return studentService.getAllStudents();
    }

    @GetMapping(path = "/{id}")
    public StudentResponse getStudent(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @PutMapping(path = "/{id}")
    public void updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequest request) {
        studentService.updateStudent(id, request);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

}
