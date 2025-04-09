package org.mrstm.quoraapi.repositories;

import org.mrstm.quoraapi.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
