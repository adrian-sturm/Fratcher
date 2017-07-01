package com.asturm.webengineering_2017.fratcher.like;

import com.asturm.webengineering_2017.fratcher.match.Match;
import com.asturm.webengineering_2017.fratcher.match.MatchService;
import com.asturm.webengineering_2017.fratcher.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikeService {
    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    LikeRepository likeRepository;

    @Autowired
    MatchService matchService;

    public void addLike(Like like) {
        likeRepository.save(like);
        if (checkForMatch(like)) {
            // add a new match
            LOG.info("Found a new match!");
            matchService.addMatch(new Match(like.getLiker(), like.getLiked()));
        }
    }

    public List<Like> getLikesForUser(Long userId) {
        return likeRepository.findByUser(userId);
    }

    /**
     * Check if the newly added like provides a match between the users
     * @param like the newly added like
     * @return true if a new match is produced,
     *          false otherwise
     */
    private boolean checkForMatch(Like like) {
        // check if the like is mutual
        return likes(like.getLiked(), like.getLiker());
    }

    /**
     * Checks if user1 likes user 2
     * @param user1 the first user
     * @param user2 the second user
     * @return true if user1 likes user 2,
     *          false otherwise
     */
    private boolean likes(Long user1, Long user2) {
        List<Like> likes = likeRepository.findByUser(user1);
        if (likes == null || likes.isEmpty()) {
            return false;
        }
        for (Like l : likes) {
            if (l.getLiked() == user2) {
                return true;
            }
        }
        return false;
    }
}
