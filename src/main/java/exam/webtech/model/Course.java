package exam.webtech.model;

import exam.webtech.model.Enums.Eskill;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private String courseName;
    private String courseImgUrl;
    private String description;
    private Double cost;
    private Eskill eskill;
    @ManyToOne
    private Student student;
    private String content;
}
