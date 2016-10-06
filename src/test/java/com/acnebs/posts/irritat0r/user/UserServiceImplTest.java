package com.acnebs.posts.irritat0r.user;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Test UserServiceImpl.
 * <p/>
 * Created by andreas.czakaj on 06.10.2016
 *
 * @author andreas.czakaj
 */
@RunWith(Enclosed.class)
public class UserServiceImplTest {
    public static class UserServiceImplGlobalTest {
        private UserDaoInMemoryImpl userDao;
        private UserServiceImpl userService;

        @Before
        public void setUp() throws Exception {
            userDao = new UserDaoInMemoryImpl(
                    new User("123"),
                    new User("456")
            );
            userService = new UserServiceImpl(userDao);
        }


        @Test
        public void test_getUserById_ok() throws Exception {
            Optional<User> user = userService.getUserById("123");
            assertEquals("123", user.get().getId());
        }

        @Test
        public void test_getUserById_404() throws Exception {
            Optional<User> user = userService.getUserById("321");
            assertFalse(
                    "It should yield an empty Optional if no such user id is known",
                    user.isPresent());
        }

        @Test
        public void test_forEachUser() throws Exception {
            Map<String, User> users = new HashMap<>();
            userService.forEachUser(user -> users.put(user.getId(), user));

            assertEquals(
                    "It should yield 2 users, i.e. all users",
                    2, users.size());
            assertTrue(
                    "It should contain user with id 123",
                    users.containsKey("123"));
            assertTrue(
                    "It should contain user with id 456",
                    users.containsKey("456"));
        }
    }
}
