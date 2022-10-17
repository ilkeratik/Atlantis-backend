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
@Table(name="faculties")
public class Faculty {
    @Id
    @NonNull private String facultyId;
    @NonNull private String facultyName;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "faculty")
    private List<Student> studentList;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "faculty")
    private List<Teacher> teacherList;
}