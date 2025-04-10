package org.mrstm.quoraapi.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true ,nullable = false)
    @NotBlank(message = "Name cannot be blank.")
    private String name;

    @ManyToMany(mappedBy = "topics")
    private List<Question> questions = new ArrayList<>();
}
