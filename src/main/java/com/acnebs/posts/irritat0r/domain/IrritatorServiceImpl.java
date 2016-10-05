package com.acnebs.posts.irritat0r.domain;


import java.util.Optional;

public class IrritatorServiceImpl implements IrritatorService {

    private final Irritat0rSalutation salutation;
    private final Irritat0rMessageFactory factory;
    private final String template;

    public IrritatorServiceImpl(final Irritat0rSalutation salutation,
                                final Irritat0rMessageFactory factory) {
        this.salutation = salutation;
        this.factory = factory;
        this.template = "Hey %s, did you know that %s?";
    }

    @Override
    public String getText(final Context context, final Optional<Person> maybePerson) {
        final String salutation = this.salutation.getSalutation(maybePerson);

        final Irritat0rMessage irritat0rMessage = factory.getMessage(context);
        final String message = irritat0rMessage.getMessage(maybePerson);

        return String.format(template, salutation, message);
    }
}
