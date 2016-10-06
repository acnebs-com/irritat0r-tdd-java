package com.acnebs.posts.irritat0r.user;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.net.URL;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.StreamSupport;

/**
 * Class UserDaoJsonJacksonImpl.
 * <p>
 * Created by andreas.czakaj on 06.10.2016
 *
 * @author andreas.czakaj
 */
class UserDaoJsonJacksonImpl implements UserDao {

    private final URL resource;

    public UserDaoJsonJacksonImpl(final URL resource) {
        this.resource = resource;
    }

    public User getUserById(final String userId) {
        final Collection<User> users = new HashSet<>();

        loadUsers(
                user -> userId.equals(user.getId()),
                users::add,
                error -> {throw new RuntimeException("getUserById: Exception e");}
        );

        final Iterator<User> iterator = users.iterator();
        return iterator.hasNext() ? iterator.next() : null;
    }

    @Override
    public void forEachUser(final Consumer<User> consumer) {
        loadUsers(user -> true, consumer, error -> {throw new RuntimeException("forEachUser: Exception e");});
    }

    void loadUsers(final Predicate<User> filter, final Consumer<User> onUser, final Consumer<Exception> onError) {
        try {
            final InputStream is = resource.openStream();
            final ObjectMapper mapper = new ObjectMapper();

            final TypeReference<User> valueTypeRef = new TypeReference<User>() {};
            final JsonNode jn = mapper.readValue(is, JsonNode.class);
            final Spliterator<JsonNode> jsonNodeSpliterator = jn.spliterator();

            StreamSupport.stream(jsonNodeSpliterator, true)
                    .map(jsonNode -> jsonNodeToUser(jsonNode, mapper, valueTypeRef, onError))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .filter(filter::test)
                    .forEach(onUser::accept);
        } catch (Exception e) {
            onError.accept(new RuntimeException("loadUsers: Exception e", e));
        }
    }

    Optional<User> jsonNodeToUser(final JsonNode jsonNode,
                                  final ObjectMapper mapper,
                                  final TypeReference<User> valueTypeRef,
                                  final Consumer<Exception> onError) {
        try {
            final JsonParser jsonParser = jsonNode.traverse(mapper);
            return Optional.<User>of(jsonParser.readValueAs(valueTypeRef));
        } catch (Exception e) {
            onError.accept(new RuntimeException("jsonNodeToUser: Exception e", e));
            return Optional.<User>empty();
        }
    }
}
