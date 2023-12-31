package microserviceapplication.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import microserviceapplication.dto.StudentResponse;
import microserviceapplication.dto.request.CreateStudentRequest;
import microserviceapplication.dto.request.UpdateStudentRequest;
import microserviceapplication.exception.StudentNotFindException;
import microserviceapplication.mapper.StudentMapper;
import microserviceapplication.repository.StudentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public void createStudent(CreateStudentRequest request) {
        studentRepository.save(studentMapper.mapRequestToStudent(request));
    }

    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::mapEntityToResponse)
                .collect(Collectors.toList());
    }

    public StudentNotFindException studentNotFindException(Long id) {
        throw new StudentNotFindException("Student not found by id " + id + "!!!");
    }

    public StudentResponse getStudent(Long id) {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> studentNotFindException(id));

        return studentMapper.mapEntityToResponse(student);
    }

    public void updateStudent(Long id, UpdateStudentRequest request) {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> studentNotFindException(id));

        student = studentMapper.mapUpdateRequestToEntity(student, request);
        studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        var student = studentRepository.findById(id)
                .orElseThrow(() -> studentNotFindException(id));

        studentRepository.deleteById(student.getId());
    }
}
