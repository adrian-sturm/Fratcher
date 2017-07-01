package com.asturm.webengineering_2017.fratcher.like;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

/**
 * A like from one user towards another
 */
@Entity(name = "LIKE_")
public class Like {

    /**
     * The id of this like
     */
    @Id
    @JsonIgnore
    @GeneratedValue
    private long id;

    /**
     * The id of the user that likes another
     */
    private long liker;

    /**
     * The id of the user that got liked
     */
    private long liked;

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

    public long getLiker() {
        return liker;
    }

    public void setLiker(long liker) {
        this.liker = liker;
    }

    public long getLiked() {
        return liked;
    }

    public void setLiked(long liked) {
        this.liked = liked;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }


}
