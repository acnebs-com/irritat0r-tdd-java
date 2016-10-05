package com.acnebs.posts.irritat0r.domain;
import java.util.Optional;

public interface Irritat0rSalutation {
    String getSalutation(Optional<Person> maybePerson);
}
