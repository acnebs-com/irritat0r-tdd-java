package com.acnebs.posts.irritat0r.domain;


import com.acnebs.posts.irritat0r.util.Sysdate;
import com.acnebs.posts.irritat0r.util.SysdateRealImpl;

import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.Optional;

public class Irritat0rMessageEarthMovementImpl implements Irritat0rMessage {

    private static final int KM_PER_SECOND = 30;

    private final Sysdate sysdate;

    private final String template;
    private final String templateIfNoBirthday;

    Irritat0rMessageEarthMovementImpl(final Sysdate sysdate) {
        this.sysdate = sysdate;
        template = "earth has moved %d km since the day you were born";
        templateIfNoBirthday = "you're moving at a speed of %d km per second around the sun";
    }

    public Irritat0rMessageEarthMovementImpl() {
        this(new SysdateRealImpl());
    }

    @Override
    public String getMessage(final Optional<Person> maybePerson) {
        final Person person = maybePerson.orElse(Person.Builder.of("").build());

        if (person.getBirthday() == null) {
            return String.format(
                    templateIfNoBirthday,
                    KM_PER_SECOND
            );
        } else {
            return String.format(
                    template,
                    getKmsSinceDate(person.getBirthday(), sysdate.now())
            );
        }

    }

    long getKmsSinceDate(final Temporal date, final Temporal now) {
        long seconds = date.until(now, ChronoUnit.SECONDS);
        return seconds * KM_PER_SECOND;
    }
}
