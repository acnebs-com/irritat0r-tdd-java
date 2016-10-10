package com.acnebs.posts.irritat0r.servlet;
import javax.servlet.http.HttpServletResponse;
import java.util.function.Consumer;

/**
 * Class Irritat0rHttpResponseAdapter.
 * <p>
 * Created by andreas.czakaj on 07.10.2016
 *
 * @author andreas.czakaj
 */
class Irritat0rHttpResponseAdapter {

    public Irritat0rHttpResponseAdapter(final HttpServletResponse response,
                                        final Consumer<Exception> errorConsumer) {
        this.response = response;
        this.errorConsumer = errorConsumer;
    }


    private final HttpServletResponse response;

    private final Consumer<Exception> errorConsumer;


    public void println(final String text) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/plain");
        try{
            response.getOutputStream().println(text);
        } catch (Exception e) {
            errorConsumer.accept(e);
        }
    }
}
