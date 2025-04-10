package org.mrstm.quoraapi.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question extends BaseModel {

    @Column(nullable = false)
    @NotBlank(message = "Title is required")
    private String title;

    private String body;

    @ManyToMany
    @JoinTable(
            name = "question_topics",
            joinColumns = @JoinColumn(name = "question_id"), //the column in the join table that links to question. (this side)
            // joinColumns refers to this columns
            inverseJoinColumns = @JoinColumn(name = "topic_id") // other entity
    )
    private List<Topic> topics = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid" , nullable = false)
    private User user;
}
