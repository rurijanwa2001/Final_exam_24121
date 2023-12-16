package bah_final_24108.bah_final_24108.repository;
import bah_final_24108.bah_final_24108.model.CourseEnrolment;
import bah_final_24108.bah_final_24108.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseEnrolmentRepository extends JpaRepository<CourseEnrolment, Long> {
    CourseEnrolment findCourseEnrolmentByCourseEnrolId(Long courseEnrolId);
    List<CourseEnrolment> findCourseEnrolmentByStudent(Student student);
}
