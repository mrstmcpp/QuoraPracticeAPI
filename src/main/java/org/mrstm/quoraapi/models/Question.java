package org.mrstm.quoraapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Question extends BaseModel {

    @Column(nullable = false ,unique = true ,length = 100)
    @NotBlank(message = "Title is required")
    private String title;

    private String body;

    @ManyToMany(fetch = FetchType.LAZY)

    @JoinTable(
            name = "question_topics",
            joinColumns = @JoinColumn(name = "question_id"), //the column in the join table that links to question. (this side)
            // joinColumns refers to this columns
            inverseJoinColumns = @JoinColumn(name = "topic_id") // other entity
    )
    @Fetch(FetchMode.SUBSELECT)
    private List<Topic> topics = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "userid" , nullable = false)
    private User user;
}
