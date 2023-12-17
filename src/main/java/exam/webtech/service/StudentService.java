package exam.webtech.service;

import exam.webtech.model.Enums.Erole;
import exam.webtech.model.Student;
import exam.webtech.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // new student
    public void createStudent(Student student) {
        studentRepository.save(student);
    }
    // find all students
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    // find all instructors
    public List<Student> getAllInstructors(Erole role) {
        return studentRepository.findStudentByStudentRole(role);
    }
    // find one student
    public Student getOneStudent(Long studentId) {
        return studentRepository.findStudentByStudentId(studentId);
    }
    public Student getOneStudentByEmail(String studentEmail) {
        return studentRepository.findStudentByEmail(studentEmail);
    }
}
