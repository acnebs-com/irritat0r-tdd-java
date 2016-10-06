package com.acnebs.posts.irritat0r.user;
import java.util.function.Consumer;

/**
 * Interface UserDao.
 * <p>
 * Created by andreas.czakaj on 06.10.2016
 *
 * @author andreas.czakaj
 */
public interface UserDao {
    User getUserById(String id);
    void forEachUser(Consumer<User> consumer);
}
