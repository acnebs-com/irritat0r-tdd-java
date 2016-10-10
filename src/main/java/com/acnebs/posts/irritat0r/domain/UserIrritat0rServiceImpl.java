package com.acnebs.posts.irritat0r.domain;
import com.acnebs.posts.irritat0r.user.User;
import com.acnebs.posts.irritat0r.user.UserService;

import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.Date;
import java.util.Optional;

/**
 * Class UserIrritat0rServiceImpl.
 * <p>
 * Created by andreas.czakaj on 06.10.2016
 *
 * @author andreas.czakaj
 */
public class UserIrritat0rServiceImpl implements UserIrritat0rService {
    private final UserService userService;
    private final Irritat0rService irritat0rService;

    public UserIrritat0rServiceImpl(final UserService userService, final Irritat0rService irritat0rService) {
        this.userService = userService;
        this.irritat0rService = irritat0rService;
    }

    @Override
    public String getText(final Optional<String> userId) {
        final Optional<User> maybeUser = userService.getUserById(userId);
        final Optional<Person> maybePerson = adaptUserToPerson(maybeUser);
        return irritat0rService.getText(maybePerson);
    }

    Optional<Person> adaptUserToPerson(final Optional<User> maybeUser) {
        if (maybeUser.isPresent() && maybeUser.get().getId() != null) {
            final User user = maybeUser.get();

            return Optional.of(
                    Person.Builder.of(user.getFirstName())
                            .withBirthday(dateToTemporal(user.getLastLogin()))
                            .build()
            );

        } else {
            return Optional.empty();
        }
    }

    Temporal dateToTemporal(final Date date) {
        if (date == null) {
            return null;
        } else {
            return date.toInstant().atZone(ZoneId.of("GMT"));
        }
    }
}
