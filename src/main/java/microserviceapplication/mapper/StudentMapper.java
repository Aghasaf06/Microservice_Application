package microserviceapplication.mapper;

import microserviceapplication.dto.StudentResponse;
import microserviceapplication.dto.request.CreateStudentRequest;
import microserviceapplication.dto.request.UpdateStudentRequest;
import microserviceapplication.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    Student mapRequestToStudent(CreateStudentRequest request);

    StudentResponse mapEntityToResponse(Student student);

    Student mapUpdateRequestToEntity(@MappingTarget Student student, UpdateStudentRequest request);
}
