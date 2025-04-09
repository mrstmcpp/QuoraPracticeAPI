package org.mrstm.quoraapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

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

}
