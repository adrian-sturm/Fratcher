package com.asturm.webengineering_2017.fratcher.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "all")
    public Iterable<User> getUsers() {
        return userService.getAll();
    }

    /**
     * Return all users that the current user has liked
     * @return a list of liked users
     */
    @RequestMapping(value = "liked")
    public Iterable<User> getLikedUsers() {
        return userService.getLikedUsers();
    }

}
