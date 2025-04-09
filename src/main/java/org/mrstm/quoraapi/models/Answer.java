package org.mrstm.quoraapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Answer extends BaseModel {
    @Column(nullable = false)
    private String text;

    @ManyToOne
    @JoinColumn(name = "question_id" , nullable = false)
    private Question question;

    @ManyToOne
    @JoinColumn(name = "user_id" , nullable = false)
    private User user;

    @OneToMany(mappedBy = "answer" , cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

}
