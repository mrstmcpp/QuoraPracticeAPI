package org.mrstm.quoraapi.repositories;

import org.mrstm.quoraapi.models.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, Integer> {
}
