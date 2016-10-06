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
class UserIrritat0rServiceImpl {
    private final UserService userService;
    private final IrritatorService irritatorService;

    public UserIrritat0rServiceImpl(final UserService userService, final IrritatorService irritatorService) {
        this.userService = userService;
        this.irritatorService = irritatorService;
    }

    public String getText(final Context context, final String userId) {
        final Optional<User> maybeUser = userService.getUserById(userId);
        final Optional<Person> maybePerson = adaptUserToPerson(maybeUser);
        return irritatorService.getText(context, maybePerson);
    }

    Optional<Person> adaptUserToPerson(final Optional<User> maybeUser) {
        if (maybeUser.isPresent() && maybeUser.get().getId() != null) {
            final User user = maybeUser.get();

            return Optional.of(
                    Person.Builder.of(user.getFirstName())
                            .withLastLoginTemporal(dateToTemporal(user.getLastLogin()))
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
