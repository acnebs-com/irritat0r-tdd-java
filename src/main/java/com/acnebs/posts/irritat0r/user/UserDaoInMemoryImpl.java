package com.acnebs.posts.irritat0r.user;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.Stream;

/**
 * Class UserDaoInMemoryImpl.
 * <p>
 * Created by andreas.czakaj on 06.10.2016
 *
 * @author andreas.czakaj
 */
public class UserDaoInMemoryImpl implements UserDao {

    private final Map<String, User> db = new HashMap<>();

    public UserDaoInMemoryImpl(final User... users) {
        load(Arrays.stream(users));
    }

    public void load(final Stream<User> userStream) {
        userStream.forEach(onUserImport());
    }

    private Consumer<User> onUserImport() {
        return user -> db.put(user.getId(), user);
    }

    @Override
    public User getUserById(final Optional<String> id) {
        return db.get(id.orElse(""));
    }

    @Override
    public void forEachUser(final Consumer<User> consumer) {
        db.values().stream().forEach(consumer::accept);
    }
}
