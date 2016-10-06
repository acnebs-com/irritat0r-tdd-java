package com.acnebs.posts.irritat0r.user;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * Class User.
 * <p>
 * Created by andreas.czakaj on 06.10.2016
 *
 * @author andreas.czakaj
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    public User() {
    }

    public User(final String id) {
        this.id = id;
    }

    private String id;

    public String getId() {
        return id;
    }


    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    public User setFirstName(final String firstName) {
        this.firstName = firstName;
        return this;
    }


    private Date lastLogin;

    public Date getLastLogin() {
        return lastLogin;
    }

    public User setLastLogin(final Date lastLogin) {
        this.lastLogin = lastLogin;
        return this;
    }
}
