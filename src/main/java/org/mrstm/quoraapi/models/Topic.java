package org.mrstm.quoraapi.models;


import jakarta.persistence.*;
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
    private String name;

    @ManyToMany(mappedBy = "topics")
    private List<Question> questions = new ArrayList<>();
}
