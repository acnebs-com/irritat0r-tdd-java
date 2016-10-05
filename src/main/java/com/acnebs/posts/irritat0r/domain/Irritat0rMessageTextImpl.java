package com.acnebs.posts.irritat0r.domain;


import java.util.Optional;
import java.util.stream.Stream;

class Irritat0rMessageTextImpl implements Irritat0rMessage {

    private final String text;

    public Irritat0rMessageTextImpl(final String text) {
        this.text = text;
    }

    @Override
    public String getMessage(final Optional<Person> maybePerson) {
        return text;
    }

    @Override
    public Stream<Context> getContexts() {
        return Stream.of(new ContextAny());
    }
}
