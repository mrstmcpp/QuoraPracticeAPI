package org.mrstm.quoraapi.repositories;

import org.mrstm.quoraapi.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
