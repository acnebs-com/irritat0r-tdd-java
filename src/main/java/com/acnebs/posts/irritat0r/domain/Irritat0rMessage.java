package com.acnebs.posts.irritat0r.domain;


import java.util.Optional;
import java.util.stream.Stream;

/**
 * Interface Irritat0rMessage.
 * <p>
 * #COPYRIGHTSTUFF#
 * <p>
 * Created by acj on 05.10.16
 *
 * @author acj
 */

interface Irritat0rMessage {
    String getMessage(Optional<Person> maybePerson);
    Stream<Context> getContexts();
}
