package com.atlantis.model.Tag;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "entry_details")
public class Tag {
    @Id
    @Column(name = "tagId", nullable = false)
    private String tagId;

    @NonNull private String tagName;
}
