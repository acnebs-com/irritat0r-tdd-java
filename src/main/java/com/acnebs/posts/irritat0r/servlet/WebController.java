package com.acnebs.posts.irritat0r.servlet;
import com.acnebs.posts.irritat0r.domain.UserIrritat0rService;

import java.util.Optional;

/**
 * Class WebController.
 * <p>
 * Created by andreas.czakaj on 07.10.2016
 *
 * @author andreas.czakaj
 */
class WebController {

    public WebController(final UserIrritat0rService service) {
        this.service = service;
    }

    private final UserIrritat0rService service;

    public String getText(final Irritat0rHttpSessionAdapter adapter) {
        final String principalId = adapter.getPrincipalId();
        return service.getText(Optional.ofNullable(principalId));
    }

    public void println(final Irritat0rHttpSessionAdapter sessionAdapter,
                        final Irritat0rHttpResponseAdapter responseAdapter) {
        responseAdapter.println(getText(sessionAdapter));
    }

    public void queueValue(final Irritat0rHttpSessionAdapter sessionAdapter,
                      final Irritat0rHttpRequestAdapter requestAdapter) {
        requestAdapter.queue(getText(sessionAdapter));
    }

    /*public void queueSupplier(final Irritat0rHttpSessionAdapter sessionAdapter,
                              final Irritat0rHttpRequestAdapter requestAdapter) {
        requestAdapter.queue(() -> getText(sessionAdapter));
    } */
}
