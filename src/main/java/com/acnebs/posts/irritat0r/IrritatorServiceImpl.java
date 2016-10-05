package com.acnebs.posts.irritat0r;
import java.util.Optional;

class IrritatorServiceImpl implements IrritatorService {
    private final String template;

    public IrritatorServiceImpl() {
        template = "Hey %s, did you know that it is 8 times more likely to get killed by a pig than by a shark?";
    }

    public String getText(final Optional<Person> maybePerson) {
        return String.format(template, getAddressing(maybePerson));
    }

    String getAddressing(final Optional<Person> maybePerson) {
        final String fallback = "you";
        final Person person = maybePerson.orElse(new Person(fallback));
        final Optional<String> maybeAddressText = Optional.ofNullable(person.getAddressText());
        final String trimmedText = maybeAddressText.orElse(fallback).trim();
        return trimmedText.length() < 1 ? fallback : trimmedText;
    }
}
