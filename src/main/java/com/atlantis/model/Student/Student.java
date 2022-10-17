package com.atlantis.model.Student;

import com.atlantis.model.University.Department;
import com.atlantis.model.University.Faculty;
import com.atlantis.model.University.Lesson;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Student implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "idStudent", nullable = false)
    private String idStudent;

    @NonNull private String studentNumber;
    @NonNull private String name;
    @NonNull private String surname;
    @NonNull private Integer grade;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name = "student_enrollments",
            joinColumns = @JoinColumn(name = "lessonId"),
            inverseJoinColumns = @JoinColumn(name = "idStudent")
    )
    @JsonIgnoreProperties("enrolledStudents")
    private Set<Lesson> lessons = new HashSet<>();

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "faculty_id", referencedColumnName = "facultyId")
    private Faculty faculty;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "department_id", referencedColumnName = "departmentId")
    private Department department;

}