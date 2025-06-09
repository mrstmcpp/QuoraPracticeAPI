package org.mrstm.quoraapi.repositories;

import org.mrstm.quoraapi.models.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {


}
