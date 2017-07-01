package com.asturm.webengineering_2017.fratcher.match;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {

    @Query("SELECT m FROM MATCHES m WHERE m.userID1 = :userId OR m.userID2 = :userId")
    List<Match> findByUser(@Param("userId") Long userId);
}
