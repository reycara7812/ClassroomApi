package ClassroomApi.classroomApi.repository;

import ClassroomApi.classroomApi.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {



}
