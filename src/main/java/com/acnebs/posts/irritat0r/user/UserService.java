package com.acnebs.posts.irritat0r.user;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Interface UserService.
 * <p>
 * Created by andreas.czakaj on 06.10.2016
 *
 * @author andreas.czakaj
 */
public interface UserService {
    Optional<User> getUserById(Optional<String> id);
    void forEachUser(Consumer<User> consumer);
}
