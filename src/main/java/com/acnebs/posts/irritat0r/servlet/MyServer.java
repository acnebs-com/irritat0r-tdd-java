package com.acnebs.posts.irritat0r.servlet;
import com.acnebs.posts.irritat0r.domain.*;
import com.acnebs.posts.irritat0r.user.UserDaoJsonJacksonImpl;
import com.acnebs.posts.irritat0r.user.UserServiceImpl;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.util.component.AbstractLifeCycle;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Class MyServer.
 * <p>
 * Created by andreas.czakaj on 08.10.2016
 *
 * @author andreas.czakaj
 */
public class MyServer {

    private static Supplier<MyServer> myServerFactory = createDefaultMyServerFactory();

    static Supplier<MyServer> createDefaultMyServerFactory() {
        return MyServer::new;
    }

    public static void setMyServerFactory(final Supplier<MyServer> myServerFactory) {
        MyServer.myServerFactory = myServerFactory;
    }

    private static ThrowingConsumer<Server> throwingConsumer = createDefaultThrowingConsumer();

    static ThrowingConsumer<Server> createDefaultThrowingConsumer() {
        return AbstractLifeCycle::start;
    }

    public static void setThrowingConsumer(final ThrowingConsumer<Server> throwingConsumer) {
        MyServer.throwingConsumer = throwingConsumer;
    }

    private static BiConsumer<Throwable, String> errorConsumer = createDefaultErrorConsumer();

    static BiConsumer<Throwable, String> createDefaultErrorConsumer() {
        return (error, message) -> {
            throw new RuntimeException(message, error);
        };
    }

    public static void setErrorConsumer(final BiConsumer<Throwable, String> errorConsumer) {
        MyServer.errorConsumer = errorConsumer;
    }


    private int port = 8080;
    private Server server;

    public static void main(String[] args) {
        MyServer myServer = myServerFactory.get();
        try {
            myServer.start();
        } catch (Exception e) {
            throw new RuntimeException("main: Exception e", e);
        }
    }

    public void start() throws Exception {
        server = new Server(port);

        server.setHandler(createIrritat0rServlet());

        final Runnable runnable = () -> {
            throwingConsumer.apply(
                    server,
                    error -> errorConsumer.accept(error, "Exception when starting jetty"));
        };
        final Thread thread = new Thread(runnable);
        thread.start();
    }

    interface ThrowingConsumer<ParamType> {
        default void apply(ParamType param, Consumer<Throwable> errorConsumer) {
            try {
                doApply(param);
            } catch (Throwable e) {
                errorConsumer.accept(e);
            }
        }

        void doApply(ParamType param) throws Throwable;
    }

    WebController createWebController() {
        return new WebController(
                new UserIrritat0rServiceImpl(
                        new UserServiceImpl(
                                new UserDaoJsonJacksonImpl(
                                        this.getClass().getResource("/users.json")
                                )
                        ),
                        new Irritat0rServiceImpl(
                                new Irritat0rSalutationImpl(),
                                new Irritat0rMessageFactory(
                                        new Irritat0rMessageDefaultImpl()
                                )
                        )
                )
        );
    }

    private ServletContextHandler createIrritat0rServlet() {
        ServletContextHandler handler = new ServletContextHandler(ServletContextHandler.SESSIONS);
        handler.addServlet(new ServletHolder(new Irritat0rServlet(createWebController())), "/inf0rm-me.html");
        return handler;
    }
}
