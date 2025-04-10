package org.mrstm.quoraapi.repositories;

import org.mrstm.quoraapi.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
    boolean existsByName(String name);
}
