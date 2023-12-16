package bah_final_24108.bah_final_24108.controller;

import bah_final_24108.bah_final_24108.model.Enums.Erole;
import bah_final_24108.bah_final_24108.model.LoginBean;
import bah_final_24108.bah_final_24108.model.Student;
import bah_final_24108.bah_final_24108.service.EmailService;
import bah_final_24108.bah_final_24108.service.StudentService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;
    private final EmailService emailService;

    @Autowired
    public StudentController(StudentService studentService, EmailService emailService) {
        this.studentService = studentService;
        this.emailService = emailService;
    }

    // bring student view
    @GetMapping
    public String createStudentForm(Model model, HttpSession session) {
        Student student = new Student();
        model.addAttribute("student", student);
        model.addAttribute("session", session);
        return "Student/student";
    }

    // save student
    @PostMapping("/new")
    public String newStudent(@ModelAttribute("student") Student student) {
        try {
            student.setStudentRole(Erole.STUDENT);
            studentService.createStudent(student);
            // Extract the full name from the HeavenCoffeeUser object
            String fullNames = student.getFullName();

            // Send the email using the EmailService
            sendEmail(student.getEmail(), fullNames);
            return "redirect:/student/login?success";
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return "redirect:/student?fail";
        }
    }

    private void sendEmail(String toEmail, String fullName) {
        try {
            String subject = "HeavenCoffee Registration Confirmation";
            String body = String.format("<html><body>" +
                    "<p>Dear %s,</p>" +
                    "<p>Welcome to Heaven Coffee Restaurant! Thank you for registering. " +
                    "Your role is crucial in managing and overseeing our operations.</p>" +
                    "<p>As a member, you have access to advanced functionalities and responsibilities. " +
                    "If you have any questions or need assistance, please don't hesitate to contact our management team.</p>" +
                    "<p>We appreciate your commitment to excellence and look forward to working together to make Heaven Coffee a great place for both our customers and our team.</p>" +
                    "<p>Best regards,<br/>" +
                    "Management Team<br/>" +
                    "Heaven Coffee Restaurant</p>" +
                    "</body></html>", fullName);
            // Use the EmailService to send the email
            emailService.sendEmail(toEmail, subject, body);
        } catch (MessagingException ex) {
            // Log the exception or handle it appropriately
            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, "Failed to send verification email", ex);
        }
    }
    // login user
    @GetMapping("/login")
    public String loginPage(Model model, HttpSession session) {
        LoginBean loginBean = new LoginBean();
        model.addAttribute("loginBean", loginBean);
        model.addAttribute("session", session);
        return "login";
    }

    // login existing user
    @PostMapping("/login/login_user")
    public String loginUser(@ModelAttribute("loginBean") LoginBean loginBean, HttpServletRequest request) {
        Student existingEmail = studentService.getOneStudentByEmail(loginBean.getEmail());

        if (existingEmail != null && existingEmail.getStudentId() != null) {
            String password = existingEmail.getPassword();

            /* store the role to be accessed around the web application pages then redirect to home */
            if (password.equals(loginBean.getPassword())) {
                String role = String.valueOf(existingEmail.getStudentRole());
                Long studId = existingEmail.getStudentId();
                // -- store the role
                HttpSession session = request.getSession();
                session.setAttribute("role", role);
                session.setAttribute("studId", studId);
                // redirect to home page
                return "redirect:/?loginsuccess";
            } else {
                return "redirect:/student/login?fail";
            }
        } else {
            return "redirect:/student/login?fail";
        }
    }

    // logout user
    @GetMapping("/logout")
    public String logoutUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("role");
        session.invalidate();
        return "redirect:/student/login";
    }
}
