package com.acnebs.posts.irritat0r.user;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.net.URL;
import java.time.ZoneId;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

/**
 * Test UserDaoJsonJacksonImpl.
 * <p>
 * Created by andreas.czakaj on 06.10.2016
 *
 * @author andreas.czakaj
 */
@RunWith(Enclosed.class)
public class UserDaoJsonJacksonImplTest {
    public static class GeneralTest {


        @Test
        public void test_load_ok() throws Exception {
            final URL resource = this.getClass().getResource("/users.json");
            UserDaoJsonJacksonImpl userDao = new UserDaoJsonJacksonImpl(resource);

            final List<User> users = new ArrayList<>();
            userDao.loadUsers(user -> true, users::add, error -> fail("It should not fail"));
            assertEquals(1000, users.size());
        }
        @Test
        public void test_load_fail() throws Exception {
            final URL resource = this.getClass().getResource("/no_such_file.json");
            final UserDaoJsonJacksonImpl userDao = new UserDaoJsonJacksonImpl(resource);

            final List<Exception> errors = new ArrayList<>();
            userDao.loadUsers(user -> true, user -> {}, errors::add);

            assertEquals(1, errors.size());
            assertEquals(
                    "It should fail with a RuntimeException because the URL does not exist",
                    "loadUsers: Exception e",
                    errors.get(0).getMessage());
        }

        @Test
        public void test_getUserById_ok_1() throws Exception {
            final URL resource = this.getClass().getResource("/users.json");
            UserDaoJsonJacksonImpl userDao = new UserDaoJsonJacksonImpl(resource);

            User user = userDao.getUserById(Optional.of("7f26a500-f02a-45d5-8890-5d42b331dcdf"));
            assertEquals("Patrick", user.getFirstName());

            final GregorianCalendar cal = new GregorianCalendar(2015, 10, 23);
            cal.setTimeZone(TimeZone.getTimeZone(ZoneId.of("GMT")));
            assertEquals(cal.getTime(), user.getLastLogin());
        }

        @Test
        public void test_getUserById_ok_2() throws Exception {
            final URL resource = this.getClass().getResource("/users.json");
            UserDaoJsonJacksonImpl userDao = new UserDaoJsonJacksonImpl(resource);

            User user = userDao.getUserById(Optional.of("53c00c1e-b767-4a4f-8b5e-de8c8e9377ba"));
            assertEquals("Christine", user.getFirstName());

            final GregorianCalendar cal = new GregorianCalendar(2015, 10, 6);
            cal.setTimeZone(TimeZone.getTimeZone(ZoneId.of("GMT")));
            assertEquals(cal.getTime(), user.getLastLogin());
        }

        @Test
        public void test_getUserById_404() throws Exception {
            final URL resource = this.getClass().getResource("/users.json");
            UserDaoJsonJacksonImpl userDao = new UserDaoJsonJacksonImpl(resource);

            User user = userDao.getUserById(Optional.of("abc"));
            assertNull("It should yield null if no such id", user);
        }

        @Test
        public void test_jsonNodeToUser_error() throws Exception {
            final URL resource = this.getClass().getResource("/users.json");
            UserDaoJsonJacksonImpl userDao = new UserDaoJsonJacksonImpl(resource);

            final List<Exception> errors = new ArrayList<>();
            userDao.jsonNodeToUser(null, null, null, errors::add);

            assertEquals(1, errors.size());
            assertEquals(
                    "It should fail with a RuntimeException because the jsonNode is null",
                    "jsonNodeToUser: Exception e",
                    errors.get(0).getMessage());
        }


        @Test
        public void test_forEachUser_ok() throws Exception {
            final URL resource = this.getClass().getResource("/users.json");
            UserDaoJsonJacksonImpl userDao = new UserDaoJsonJacksonImpl(resource);

            final List<User> users = new ArrayList<>();
            userDao.forEachUser(users::add);
            assertEquals(1000, users.size());
        }
        @Test
        public void test_forEachUser_fail() throws Exception {
            final URL resource = this.getClass().getResource("/no_such_file.json");
            final UserDaoJsonJacksonImpl userDao = new UserDaoJsonJacksonImpl(resource);

            try {
                userDao.forEachUser(user -> {});
                fail("It should throw an Exception");
            } catch (RuntimeException e) {
                assertEquals(
                        "It should fail with a RuntimeException because the URL does not exist",
                        "forEachUser: Exception e",
                        e.getMessage());

            }
        }
    }
}
