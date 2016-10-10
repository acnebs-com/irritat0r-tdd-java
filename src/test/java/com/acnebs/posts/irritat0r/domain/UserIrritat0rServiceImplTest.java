package com.acnebs.posts.irritat0r.domain;
import com.acnebs.posts.irritat0r.user.User;
import com.acnebs.posts.irritat0r.user.UserDaoInMemoryImpl;
import com.acnebs.posts.irritat0r.user.UserServiceImpl;
import com.acnebs.posts.irritat0r.util.SysdateFixedDateImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.Temporal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Test UserIrritat0rServiceImpl.
 * <p>
 * Created by andreas.czakaj on 06.10.2016
 *
 * @author andreas.czakaj
 */
@RunWith(Enclosed.class)
public class UserIrritat0rServiceImplTest {
    public static class GeneralTest {

        UserDaoInMemoryImpl userDao;
        UserIrritat0rServiceImpl service;

        /** Procedure setUp.
         *
         * JUnit Test Case Set Up
         *
         * @throws Exception
         * author andreas.czakaj, 06.10.2016
         */
        @Before
        public void setUp() throws Exception {
            userDao = new UserDaoInMemoryImpl(
                    new User("123")
                            .setFirstName("Joni")
                            .setLastLogin(createDate(2016, 10, 6, 13, 1, 20)),
                    new User("456")
                            .setFirstName("Jane")
                            .setLastLogin(createDate(2016, 10, 6, 13, 1, 21))
            );

            service = new UserIrritat0rServiceImpl(
                    new UserServiceImpl(userDao),
                    new Irritat0rServiceImpl(
                            new Irritat0rSalutationImpl(),
                            new Irritat0rMessageFactory(
                                    new Irritat0rMessageTextImpl("E = mc^2"),
                                    new Irritat0rMessageEarthMovementImpl(
                                            new SysdateFixedDateImpl(
                                                    LocalDateTime.of(2016, 10, 6, 13, 1, 23)
                                            )
                                    )
                            )
                    )
            );
        }


        @Test
        public void test_getText() throws Exception {
            String actual = service.getText(Optional.of("123"));
            assertEquals("Hey Joni, did you know that E = mc^2?", actual);
        }


        @Test
        public void test_adaptUserToPerson_no_user_id() throws Exception {
            Optional<User> maybeUser = Optional.of(new User(null));
            final Optional<Person> maybePerson = service.adaptUserToPerson(maybeUser);
            assertFalse(
                    "It should yield an empty Optional if user has no ID",
                    maybePerson.isPresent()
            );
        }


        @Test
        public void test_adaptUserToPerson_no_user() throws Exception {
            final Optional<Person> maybePerson = service.adaptUserToPerson(Optional.empty());
            assertFalse(
                    "It should yield an empty Optional if user is empty",
                    maybePerson.isPresent()
            );
        }


        @Test
        public void test_adaptUserToPerson_user_ok() throws Exception {
            Optional<User> maybeUser = Optional.of(userDao.getUserById(Optional.of("123")));
            final Optional<Person> maybePerson = service.adaptUserToPerson(maybeUser);
            Person person = maybePerson.get();
            assertEquals(
                    "It should yield the user's first name as salutation",
                    "Joni", person.getSalutation());

            final Temporal expectedLastLoginTemporal = LocalDateTime
                    .of(2016, 10, 6, 13, 1, 20)
                    .atZone(ZoneId.of("GMT"));
            assertEquals(
                    "It should yield the same date time for lastloginTemporal",
                    expectedLastLoginTemporal, person.getBirthday());
        }


        @Test
        public void test_dateToTemporal_null() throws Exception {
            assertEquals(
                    "If should yield null if input is null, too",
                    null, service.dateToTemporal(null));
        }

        @Test
        public void test_dateToTemporal_non_null() throws Exception {
            final Temporal expectedLastLoginTemporal = LocalDateTime
                    .of(2016, 10, 6, 13, 1, 20)
                    .atZone(ZoneId.of("GMT"));

            final Date date = createDate(2016, 10, 6, 13, 1, 20);
            assertEquals(expectedLastLoginTemporal, service.dateToTemporal(date));
        }
    }

    private static Date createDate(final int year, final int month, final int day, final int hour, final int minute, final int second) {
        Calendar cal =  new GregorianCalendar(year, month - 1, day, hour, minute, second);
        cal.setTimeZone(TimeZone.getTimeZone(ZoneId.of("GMT")));
        return cal.getTime();
    }
}
