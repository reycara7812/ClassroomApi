package ClassroomApi.classroomApi.repository;

import ClassroomApi.classroomApi.model.Student; // Assuming Customer is your Student entity
import ClassroomApi.classroomApi.utility.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {




    List<Student> findByGroup(Group group);
}
