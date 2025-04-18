package org.mrstm.quoraapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseModel {
    @Column(nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answer_id" , nullable = false)
//    @JsonIgnore
    private Answer answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false)
//    @JsonIgnore
    private User user;

    //self-referencing parent comment (for replies)
    //if parent == null => top level comment

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_comment_id")
//    @JsonIgnore
    private Comment parent;

    @OneToMany(mappedBy = "parent" , cascade = CascadeType.ALL)
//    @JsonIgnore
    private List<Comment> replies = new ArrayList<>();

    public void addReplies(Comment replie) {
        replies.add(replie);
        this.replies.add(replie);
    }
}

