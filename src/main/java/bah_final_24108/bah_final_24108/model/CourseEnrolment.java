package bah_final_24108.bah_final_24108.model;

import bah_final_24108.bah_final_24108.model.Enums.Estatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class CourseEnrolment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseEnrolId;
    @ManyToOne
    private Student student;
    @ManyToOne
    private Course course;
    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate enrolmentDate;
    private Estatus estatus;
}
