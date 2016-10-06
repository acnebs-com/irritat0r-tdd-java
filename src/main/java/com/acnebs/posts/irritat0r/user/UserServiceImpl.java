package com.acnebs.posts.irritat0r.user;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Class UserServiceImpl.
 * <p/>
 * Created by andreas.czakaj on 06.10.2016
 *
 * @author andreas.czakaj
 */
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> getUserById(final String id) {
        return Optional.ofNullable(userDao.getUserById(id));
    }

    @Override
    public void forEachUser(final Consumer<User> consumer) {
        userDao.forEachUser(consumer);
    }
}
