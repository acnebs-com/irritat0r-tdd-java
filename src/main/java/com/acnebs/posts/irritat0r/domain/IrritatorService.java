package com.acnebs.posts.irritat0r.domain;
import java.util.Optional;

public interface IrritatorService {
    String getText(Context context, Optional<Person> maybePerson);
}
