package com.acnebs.posts.irritat0r.servlet;
import javax.servlet.http.HttpServletRequest;

/**
 * Class Irritat0rHttpResponseAdapter.
 * <p>
 * Created by andreas.czakaj on 07.10.2016
 *
 * @author andreas.czakaj
 */
class Irritat0rHttpRequestAdapter {

    public Irritat0rHttpRequestAdapter(final HttpServletRequest request) {
        this.request = request;
    }

    public static final String KEY_TEXT = "irritat0rText";

    private final HttpServletRequest request;


    public void queue(final String text) {
        request.setAttribute(KEY_TEXT, text);
    }
}
