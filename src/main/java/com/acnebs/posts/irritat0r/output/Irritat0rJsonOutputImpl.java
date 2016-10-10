package com.acnebs.posts.irritat0r.output;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.OutputStream;
import java.util.function.Consumer;

/**
 * Class Irritat0rJsonServiceImpl.
 * <p>
 * Created by andreas.czakaj on 09.10.2016
 *
 * @author andreas.czakaj
 */
class Irritat0rJsonOutputImpl {

    private Consumer<Exception> errorConsumer = error -> {throw new RuntimeException("toJson: Exception", error);};

    public void toJson(final String text, final OutputStream outputStream) {
        final JsonResponse jsonResponse = new JsonResponse(text);

        final ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(outputStream, jsonResponse);
        } catch (Exception e) {
            errorConsumer.accept(e);
        }
    }

    static class JsonResponse {
        public JsonResponse(final String text) {
            this.text = text;
        }

        private final String text;

        public String getText() {
            return text;
        }
    }
}
