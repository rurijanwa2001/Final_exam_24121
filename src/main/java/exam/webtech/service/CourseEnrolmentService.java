package exam.webtech.service;

import exam.webtech.model.CourseEnrolment;
import exam.webtech.model.Student;
import exam.webtech.repository.CourseEnrolmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseEnrolmentService {
    private final CourseEnrolmentRepository courseEnrolmentRepository;

    @Autowired
    public CourseEnrolmentService(CourseEnrolmentRepository courseEnrolmentRepository) {
        this.courseEnrolmentRepository = courseEnrolmentRepository;
    }

    // new course
    public void createCourseEnrol(CourseEnrolment courseEnrolment) {
        courseEnrolmentRepository.save(courseEnrolment);
    }
    // find all courses
    public List<CourseEnrolment> getAllCoursesEnrol() {
        return courseEnrolmentRepository.findAll();
    }
    public List<CourseEnrolment> getAllCoursesEnrolOfStudent(Student student) {
        return courseEnrolmentRepository.findCourseEnrolmentByStudent(student);
    }
    // find one course
    public CourseEnrolment getOneCourseEnrol(Long courseEnrolId) {
        return courseEnrolmentRepository.findCourseEnrolmentByCourseEnrolId(courseEnrolId);
    }
    // update course
    public void modifyCourseEnrol(CourseEnrolment courseEnrolment) {
        courseEnrolmentRepository.save(courseEnrolment);
    }
    // delete course
    public void removeCourseEnrol(Long courseEnrolId) {
        courseEnrolmentRepository.deleteById(courseEnrolId);
    }
}
