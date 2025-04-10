package org.mrstm.quoraapi.repositories;

import org.mrstm.quoraapi.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuestionRepository extends JpaRepository<Question, Integer> {

}
