package org.mrstm.quoraapi.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

    @ManyToMany(mappedBy = "topics" , fetch = FetchType.LAZY)
    @Fetch(FetchMode.SUBSELECT)
    @JsonIgnore // to break the infinite loop while sending request.
    //i.e. while sending api request due to many-many relationship
    //one topic nd question get inserted in each other which lead to infinite
    // recursion calls.... using jsonignre it can be broken
    private List<Question> questions = new ArrayList<>();

}
