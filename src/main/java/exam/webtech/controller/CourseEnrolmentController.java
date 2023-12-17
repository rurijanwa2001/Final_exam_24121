package exam.webtech.controller;

import exam.webtech.model.Course;
import exam.webtech.model.CourseEnrolment;
import exam.webtech.model.Enums.Estatus;
import exam.webtech.model.Student;
import exam.webtech.service.CourseEnrolmentService;
import exam.webtech.service.CourseService;
import exam.webtech.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/course_enrolment")
public class CourseEnrolmentController {
    private final CourseEnrolmentService courseEnrolmentService;
    private final StudentService studentService;
    private final CourseService courseService;

    @Autowired
    public CourseEnrolmentController(CourseEnrolmentService courseEnrolmentService, StudentService studentService, CourseService courseService) {
        this.courseEnrolmentService = courseEnrolmentService;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    // enrol course
    @GetMapping("/{studentId}/enrol/{courseId}")
    public String newCourse(@ModelAttribute("studentId") Long studentId, @ModelAttribute("courseId") Long courseId) {
        try {
            CourseEnrolment courseEnrolment = new CourseEnrolment();
            Course course = courseService.getOneCourse(courseId);
            Student student = studentService.getOneStudent(studentId);

            courseEnrolment.setCourse(course);
            courseEnrolment.setStudent(student);
            courseEnrolment.setEstatus(Estatus.ENROLLED);
            courseEnrolment.setEnrolmentDate(courseEnrolment.getEnrolmentDate());
            courseEnrolmentService.createCourseEnrol(courseEnrolment);
            return "redirect:/course_enrolment/my-enrol?success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:/course/"+courseId+"/content?fail";
        }
    }

    @GetMapping("/my-enrol")
    public String allCourses(Model model, HttpSession session) {
        Long studId = (Long) session.getAttribute("studId");
        Student student = studentService.getOneStudent(studId);
        List<CourseEnrolment> courseEnrolmentList = courseEnrolmentService.getAllCoursesEnrolOfStudent(student);
        model.addAttribute("courseEnrolmentList", courseEnrolmentList);
        return "Course/my-courses";
    }

    // un enrol course
    @GetMapping("/{courseId}/un-enrol")
    public String editCourseForm(@PathVariable("courseId") Long courseId) {
        try {
            CourseEnrolment courseEnrolment = courseEnrolmentService.getOneCourseEnrol(courseId);
            courseEnrolment.setEstatus(Estatus.CANCELLED);
            courseEnrolmentService.modifyCourseEnrol(courseEnrolment);

            return "redirect:/course_enrolment/my-enrol?success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:/course_enrolment/my-enrol?fail";
        }
    }
}
