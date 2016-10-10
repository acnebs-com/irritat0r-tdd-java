package com.acnebs.posts.irritat0r.servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Class HttpSessionStub.
 * <p>
 * Created by andreas.czakaj on 07.10.2016
 *
 * @author andreas.czakaj
 */
class HttpSessionStub implements HttpSession {
    private final Map<String, Object> attributes = new HashMap<>();

    @Override
    public long getCreationTime() {
        return 0;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public long getLastAccessedTime() {
        return 0;
    }

    @Override
    public ServletContext getServletContext() {
        return null;
    }

    @Override
    public void setMaxInactiveInterval(final int i) {
    }

    @Override
    public int getMaxInactiveInterval() {
        return 0;
    }

    @Override
    public HttpSessionContext getSessionContext() {
        return null;
    }

    @Override
    public Object getAttribute(final String s) {
        return attributes.get(s);
    }

    @Override
    public Object getValue(final String s) {
        return null;
    }

    @Override
    public Enumeration<String> getAttributeNames() {
        return null;
    }

    @Override
    public String[] getValueNames() {
        return new String[0];
    }

    @Override
    public void setAttribute(final String s, final Object o) {
        attributes.put(s, o);
    }

    @Override
    public void putValue(final String s, final Object o) {
        attributes.put(s, o);
    }

    @Override
    public void removeAttribute(final String s) {
        attributes.remove(s);
    }

    @Override
    public void removeValue(final String s) {
    }

    @Override
    public void invalidate() {
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
