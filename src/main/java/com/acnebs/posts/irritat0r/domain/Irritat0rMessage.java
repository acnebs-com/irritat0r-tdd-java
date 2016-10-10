package com.acnebs.posts.irritat0r.domain;


import java.util.Optional;

/**
 * Interface Irritat0rMessage.
 * <p>
 * #COPYRIGHTSTUFF#
 * <p>
 * Created by acj on 05.10.16
 *
 * @author acj
 */

public interface Irritat0rMessage {
    String getMessage(Optional<Person> maybePerson);
}
