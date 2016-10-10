package com.acnebs.posts.irritat0r.servlet;
import javax.servlet.http.HttpSession;

/**
 * Class Irritat0rHttpSessionAdapter.
 * <p>
 * Created by andreas.czakaj on 07.10.2016
 *
 * @author andreas.czakaj
 */
class Irritat0rHttpSessionAdapter {

    public static final String KEY_PRINCIPAL_ID = "principalId";

    public Irritat0rHttpSessionAdapter(final HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    private final HttpSession httpSession;

    public String getPrincipalId() {
        return (String) httpSession.getAttribute(KEY_PRINCIPAL_ID);
    }

    public void setPrincipalId(final String principalId) {
        httpSession.setAttribute(KEY_PRINCIPAL_ID, principalId);
    }
}
