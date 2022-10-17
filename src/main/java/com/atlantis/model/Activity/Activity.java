package com.atlantis.model.Activity;

import com.atlantis.model.University.Lesson;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "activities")
public class Activity {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String activityId;
    @NonNull private String activityName;

    @ManyToOne
    @JoinColumn(name="activityLesson", nullable=false)
    @NonNull private Lesson activityLesson;

    private Date created;
    private Date lastUpdated;

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        lastUpdated = new Date();
    }
}
