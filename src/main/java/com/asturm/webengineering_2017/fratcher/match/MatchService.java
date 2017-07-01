package com.asturm.webengineering_2017.fratcher.match;

import com.asturm.webengineering_2017.fratcher.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private MatchRepository matchRepository;

    public List<Match> getMatchesForUser(Long userId) {
        return matchRepository.findByUser(userId);
    }

    public void addMatch(Match match) {
        LOG.info("Adding a new match for user {} and user {}", match.getUserID1(), match.getUserID2());
        matchRepository.save(match);
    }

}
