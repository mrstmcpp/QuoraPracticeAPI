package org.mrstm.quoraapi.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "likes", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "entity_type", "entity_id"})
})
public class Like extends BaseModel {
    @ManyToOne(optional = false)
    private User user;

    @Enumerated(EnumType.STRING)
    private LikeEntity type;

    private int entityId;
}
