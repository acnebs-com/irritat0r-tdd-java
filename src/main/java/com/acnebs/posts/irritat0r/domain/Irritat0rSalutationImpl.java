package com.acnebs.posts.irritat0r.domain;
import java.util.Optional;

public class Irritat0rSalutationImpl implements Irritat0rSalutation {
    /*private final String template;

    public IrritatorSalutationImpl() {
        template = "Hey %s, did you know that it is 8 times more likely to get killed by a pig than by a shark?";
    }

    public String getMessage(final Optional<Person> maybePerson) {
        return String.format(template, getSalutation(maybePerson));
    } */

    @Override
    public String getSalutation(final Optional<Person> maybePerson) {
        final String fallback = "you";
        final Person person = maybePerson.orElse(new Person(fallback));
        final Optional<String> maybeAddressText = Optional.ofNullable(person.getSalutation());
        final String trimmedText = maybeAddressText.orElse(fallback).trim();

        return trimmedText.length() < 1 ? fallback : trimmedText;
    }
}
