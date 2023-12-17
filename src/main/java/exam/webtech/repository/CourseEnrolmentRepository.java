package exam.webtech.repository;
import exam.webtech.model.CourseEnrolment;
import exam.webtech.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseEnrolmentRepository extends JpaRepository<CourseEnrolment, Long> {
    CourseEnrolment findCourseEnrolmentByCourseEnrolId(Long courseEnrolId);
    List<CourseEnrolment> findCourseEnrolmentByStudent(Student student);
}
