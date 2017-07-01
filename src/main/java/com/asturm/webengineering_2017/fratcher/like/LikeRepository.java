package com.asturm.webengineering_2017.fratcher.like;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LikeRepository extends CrudRepository<Like, Long> {

    @Query("SELECT l FROM LIKE_ l WHERE l.liker = :userId")
    List<Like> findByUser(@Param("userId") Long userId);

}
