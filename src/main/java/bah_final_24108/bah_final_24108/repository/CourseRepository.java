package bah_final_24108.bah_final_24108.repository;

import bah_final_24108.bah_final_24108.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findCourseByCourseId(Long courseId);
}
