package com.asturm.webengineering_2017.fratcher.user;

import com.asturm.webengineering_2017.fratcher.like.Like;
import com.asturm.webengineering_2017.fratcher.like.LikeService;
import com.asturm.webengineering_2017.fratcher.match.MatchService;
import org.h2.jdbc.JdbcSQLException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    LikeService likeService;

    @Autowired
    MatchService matchService;

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public Iterable<User> getLikedUsers() {
        List<User> likedUsers = new ArrayList<>();
        List<Like> likes = likeService.getLikesForUser(getCurrentUser().getId());
        for (Like l : likes) {
            likedUsers.add(userRepository.findOne(l.getLiked()));
        }
        return likedUsers;
    }

    /**
     * Set a user for the current request.
     *
     * @param id    user id
     * @param email user email
     */
    public void setCurrentUser(Long id, String email) {
        LOG.debug("Setting user context. id={}, user={}", id, email);
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        UsernamePasswordAuthenticationToken secAuth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(secAuth);
    }

    /**
     * Retrieve the currently active user or null, if no user is logged in.
     *
     * @return the current user.
     */
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * Retrieve a User by a specific email address and password.
     *
     * @param email the email address of the requested user
     * @return the retrieved user or null if no user with the specified credentials was found
     */
    public User getUser(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Stores a new user entity in the database
     *
     * @param email the unique email of the new user
     * @param password the password of the new user
     */
    public void addNewUser(String email, String password) throws ConstraintViolationException, JdbcSQLException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        LOG.info("Adding a new user: {}", user);
        userRepository.save(user);
    }
}
