package exam.webtech.repository;

import exam.webtech.model.Enums.Erole;
import exam.webtech.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByStudentId(Long studentId);
    Student findStudentByEmail(String studentEmail);
    List<Student> findStudentByStudentRole(Erole erole);
}
