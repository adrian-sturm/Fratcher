package com.asturm.webengineering_2017.fratcher.match;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * A match between two users
 */
@Entity(name = "MATCHES")
public class Match {

    public Match(Long user1, Long user2) {
        this.setUserID1(user1);
        this.setUserID2(user2);
    }

    /**
     * The id of this match
     */
    @Id
    @JsonIgnore
    @GeneratedValue
    private long id;

    /**
     * The id of the first user
     */
    private long userID1;

    /**
     * The id of the second user
     */
    private long userID2;

    /**
     * The date of the creation
     */
    @Basic(optional = false)
    @Column(insertable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;

    @JsonProperty
    public long getId() {
        return id;
    }

    @JsonIgnore
    public void setId(long id) {
        this.id = id;
    }

    public long getUserID1() {
        return userID1;
    }

    public void setUserID1(long userID1) {
        this.userID1 = userID1;
    }

    public long getUserID2() {
        return userID2;
    }

    public void setUserID2(long userID2) {
        this.userID2 = userID2;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

}
