package bah_final_24108.bah_final_24108.repository;

import bah_final_24108.bah_final_24108.model.Enums.Erole;
import bah_final_24108.bah_final_24108.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findStudentByStudentId(Long studentId);
    Student findStudentByEmail(String studentEmail);
    List<Student> findStudentByStudentRole(Erole erole);
}
