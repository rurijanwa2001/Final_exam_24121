package exam.webtech.controller;

import exam.webtech.model.Course;
import exam.webtech.model.Enums.Erole;
import exam.webtech.model.Enums.Eskill;
import exam.webtech.model.Student;
import exam.webtech.service.CourseService;
import exam.webtech.service.StudentService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;
    private final StudentService studentService;

    @Autowired
    public CourseController(CourseService courseService, StudentService studentService) {
        this.courseService = courseService;
        this.studentService = studentService;
    }

    // bring course view
    @GetMapping
    public String createCourseForm(Model model, HttpSession session) {
        Course course = new Course();
        List<Eskill> eskillList = List.of(Eskill.values());
        List<Student> instructorList = studentService.getAllInstructors(Erole.INSTRUCTOR);
        model.addAttribute("course", course);
        model.addAttribute("skills", eskillList);
        model.addAttribute("instructorList", instructorList);
        model.addAttribute("session", session);
        return "Course/course";
    }

    // save course
    @PostMapping("/new")
    public String newCourse(@ModelAttribute("course") Course course) {
        try {
            courseService.createCourse(course);
            return "redirect:/course?success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:/course?fail";
        }
    }

    // for getting updating information
    @GetMapping("/{courseId}/edit")
    public String editCourseForm(@PathVariable("courseId") Long courseId, Model model, HttpSession session) {
        try {
            Course course = courseService.getOneCourse(courseId);
            List<Eskill> eskillList = List.of(Eskill.values());
            List<Student> instructorList = studentService.getAllInstructors(Erole.INSTRUCTOR);
            model.addAttribute("course", course);
            model.addAttribute("instructorList", instructorList);
            model.addAttribute("skills", eskillList);
            model.addAttribute("session", session);

            return "Course/course-edit";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:/course/all-course?fail";
        }
    }

    // load all courses
    @GetMapping("/all-course")
    public String allCourses(Model model) {
        List<Course> courseList = courseService.getAllCourses();
        model.addAttribute("courseList", courseList);
        return "Course/all-courses";
    }

    // for getting course detail
    @GetMapping("/{courseId}/content")
    public String courseContent(@PathVariable("courseId") Long courseId, Model model, HttpSession session) {
        try {
            Course course = courseService.getOneCourse(courseId);
            model.addAttribute("course", course);
            model.addAttribute("session", session);
            return "Course/course-content";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:/all-course?fail";
        }
    }

    // update
    @PostMapping("/{courseId}/edit")
    public String editCourse(@PathVariable("courseId") Long courseId, @ModelAttribute("course") Course course) {
        try {
            course.setCourseId(courseId);
            courseService.modifyCourse(course);
            return "redirect:/course/all-course?success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:/course/all-course?fail";
        }
    }

    // delete course
    @GetMapping("/{courseId}/delete")
    public String removeCourse(@PathVariable("courseId") Long courseId) {
        try {
            courseService.removeCourse(courseId);
            return "redirect:/course/all-course?success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:/course/all-course?fail";
        }
    }
}
