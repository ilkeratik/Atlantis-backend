package com.atlantis.model.University;

import com.atlantis.model.Student.Student;
import com.atlantis.model.Teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="departments")
public class Department {
    @Id
    @NonNull private String departmentId;
    @NonNull private String departmentName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "department")
    private List<Student> studentList;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "department")
    private List<Teacher> teacherList;

}