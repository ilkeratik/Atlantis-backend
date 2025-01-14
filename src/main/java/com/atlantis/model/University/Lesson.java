package com.atlantis.model.University;

import com.atlantis.model.Activity.Activity;
import com.atlantis.model.Student.Student;
import com.atlantis.model.Teacher.Teacher;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor

@Table(name = "lessons")
@Entity

//@JsonDeserialize(using = LessonDeserializer.class)
public class Lesson {
    @Id
    @NonNull private String lessonId;
    @NonNull private String lessonName;
    private Date year;
    private String term;

    @OneToMany(mappedBy = "activityId")
    private Set<Activity> activities = new HashSet<>();


//    @JsonIgnoreProperties("lessons")
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Student.class)
    @JoinTable(
            name = "student_enrollments",
            joinColumns = @JoinColumn(name = "idStudent"),
            inverseJoinColumns = @JoinColumn(name = "lessonId")
    )
    private Set<Student> enrolledStudents = new HashSet<>();


    @JsonIgnoreProperties("lessons")
    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "lessons", targetEntity = Teacher.class)
    private Set<Teacher> enrolledTeachers = new HashSet<>();

    public Lesson(@NonNull String lessonId, @NonNull String lessonName, Date year, String term) {
        this.lessonId = lessonId;
        this.lessonName = lessonName;
        this.year = year;
        this.term = term;
    }

    public void enrollStudent(Student student){
        enrolledStudents.add(student);
    }

    public void unEnrollStudent(Student student){
        enrolledStudents.remove(student);
    }

    public void enrollTeacher(Teacher teacher){
        enrolledTeachers.add(teacher);
    }

    public void unEnrollTeacher(Teacher teacher){
        enrolledTeachers.remove(teacher);
    }

}