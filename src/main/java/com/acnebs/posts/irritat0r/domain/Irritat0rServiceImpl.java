package com.acnebs.posts.irritat0r.domain;


import java.util.Optional;

public class Irritat0rServiceImpl implements Irritat0rService {

    private final Irritat0rSalutation salutation;
    private final Irritat0rMessageFactory factory;
    private final String template;

    public Irritat0rServiceImpl(final Irritat0rSalutation salutation,
                                final Irritat0rMessageFactory factory) {
        this.salutation = salutation;
        this.factory = factory;
        this.template = "Hey %s, did you know that %s?";
    }

    @Override
    public String getText(final Optional<Person> maybePerson) {
        final String salutation = this.salutation.getSalutation(maybePerson);

        final Irritat0rMessage irritat0rMessage = factory.getMessage();
        final String message = irritat0rMessage.getMessage(maybePerson);

        return String.format(template, salutation, message);
    }
}
