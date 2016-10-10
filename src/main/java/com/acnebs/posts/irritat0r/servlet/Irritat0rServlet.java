package com.acnebs.posts.irritat0r.servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Class Irritat0rServlet.
 * <p>
 * Created by andreas.czakaj on 08.10.2016
 *
 * @author andreas.czakaj
 */
class Irritat0rServlet extends HttpServlet {

    public Irritat0rServlet(final WebController webController) {
        this.webController = webController;
    }

    private final WebController webController;

    @Override
    protected void doGet(final HttpServletRequest request,
                         final HttpServletResponse response
    ) throws ServletException, IOException {
        webController.println(
                new Irritat0rHttpSessionAdapter(request.getSession(true)),
                new Irritat0rHttpResponseAdapter(response, error -> {
                })
        );
    }
}
