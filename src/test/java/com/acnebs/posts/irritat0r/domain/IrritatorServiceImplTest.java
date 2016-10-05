package com.acnebs.posts.irritat0r.domain;


import com.acnebs.posts.irritat0r.util.SysdateStubImpl;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class IrritatorServiceImplTest {

    @Test
    public void test_getText_default() throws Exception {
        IrritatorServiceImpl service = new IrritatorServiceImpl(
                new Irritat0rSalutationImpl(),
                new Irritat0rMessageFactory()
        );

        final Person person = Person.Builder
                .of(" Jane ")
                .build();

        final String actual = service.getText(Context.ANY, Optional.of(person));
        assertEquals(
                "Hey Jane, did you know that it is 8 times more likely to get killed by a pig than by a shark?",
                actual
        );
    }

    @Test
    public void test_getText_random_text() throws Exception {
        IrritatorServiceImpl service = new IrritatorServiceImpl(
                new Irritat0rSalutationImpl(),
                new Irritat0rMessageFactory(
                        new Irritat0rMessageTextImpl("a"),
                        new Irritat0rMessageTextImpl("b"),
                        new Irritat0rMessageTextImpl("c")
                )
        );

        final Person person = Person.Builder
                .of(" Jane ")
                .build();

        final Map<String, Integer> collector = new HashMap<>();
        final int limit = 10000;
        for (int i = 0; i < limit; i++) {
            final String actual = service.getText(Context.ANY, Optional.of(person));
            collector.put(actual, Optional.ofNullable(collector.get(actual)).orElse(0) + 1);
        }

        assertEquals(3, collector.size());
        for (Integer i : collector.values()) {
            assertEquals(
                    limit / 3,
                    i,
                    limit * .01
            );
        }
    }

    @Test
    public void test_getText_context_afterLogin() throws Exception {
        IrritatorServiceImpl service = new IrritatorServiceImpl(
                new Irritat0rSalutationImpl(),
                new Irritat0rMessageFactory(
                        new Irritat0rMessageDefaultImpl(),
                        new Irritat0rMessageEarthMovementImpl(
                                new SysdateStubImpl(LocalDateTime.of(2016, 10, 1, 13, 23, 47))
                        )
                )
        );

        final Person person = Person.Builder
                .of(" Joni ")
                .withLastLoginTemporal(LocalDateTime.of(2016, 10, 1, 13, 23, 43))
                .build();

        final String actual = service.getText(new ContextAfterLogin(), Optional.of(person));
        assertEquals(
                "Hey Joni, did you know that earth has moved 120 km since your last login?",
                actual
        );
    }

}
