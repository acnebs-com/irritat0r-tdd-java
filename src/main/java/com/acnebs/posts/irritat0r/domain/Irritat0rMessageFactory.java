package com.acnebs.posts.irritat0r.domain;


import java.security.SecureRandom;
import java.util.*;
import java.util.stream.Collectors;

class Irritat0rMessageFactory {

    private final Collection<Irritat0rMessage> registry;

    private final Random random = new SecureRandom();

    public Irritat0rMessageFactory(final Irritat0rMessage... irritat0rMessages) {
        this(Arrays.stream(irritat0rMessages).collect(Collectors.toSet()));
    }

    public Irritat0rMessageFactory(final Collection<Irritat0rMessage> registry) {
        this.registry = registry;
    }

    public Irritat0rMessage getMessage(final Context context) {
        final List<Irritat0rMessage> msgs = new ArrayList<>();

        for (Irritat0rMessage message : registry) {
            final Set<Context> contexts = message.getContexts()
                    .filter(ctx -> ctx.getClass().equals(context.getClass()))
                    .collect(Collectors.toSet());
            if (contexts.size() > 0) {
                msgs.add(message);
            }
        }

        return msgs.size() > 0 ? msgs.get(random.nextInt(msgs.size())) : new Irritat0rMessageDefaultImpl();
    }
}
