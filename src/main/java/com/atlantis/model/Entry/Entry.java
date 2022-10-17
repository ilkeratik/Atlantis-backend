package com.atlantis.model.Entry;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entries")
public class Entry{
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "entryId", nullable = false)
    private String entryId;
    @NonNull private Date entryCreated;
    @NonNull private Date entryLastUpdated;
    @NonNull private String entryParent;
    @NonNull private String entryType;
    @NonNull private String entryTitle;
    @NonNull private String userType;
    @Column(name = "userId", nullable = false)
    @NonNull private String userId;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="entryDetailsId")
    private EntryDetails entryDetails;

    @PrePersist
    protected void onCreate() {
        entryCreated = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        entryLastUpdated = new Date();
    }

}
