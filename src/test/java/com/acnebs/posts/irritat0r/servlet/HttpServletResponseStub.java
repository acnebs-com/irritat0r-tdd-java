package com.acnebs.posts.irritat0r.servlet;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Collection;
import java.util.Locale;

/**
 * Class HttpServletResponseStub.
 * <p>
 * Created by andreas.czakaj on 07.10.2016
 *
 * @author andreas.czakaj
 */
class HttpServletResponseStub implements HttpServletResponse {
    @Override
    public void addCookie(final Cookie cookie) {
    }

    @Override
    public boolean containsHeader(final String s) {
        return false;
    }

    @Override
    public String encodeURL(final String s) {
        return null;
    }

    @Override
    public String encodeRedirectURL(final String s) {
        return null;
    }

    @Override
    public String encodeUrl(final String s) {
        return null;
    }

    @Override
    public String encodeRedirectUrl(final String s) {
        return null;
    }

    @Override
    public void sendError(final int i, final String s) throws IOException {
    }

    @Override
    public void sendError(final int i) throws IOException {
    }

    @Override
    public void sendRedirect(final String s) throws IOException {
    }

    @Override
    public void setDateHeader(final String s, final long l) {
    }

    @Override
    public void addDateHeader(final String s, final long l) {
    }

    @Override
    public void setHeader(final String s, final String s1) {
    }

    @Override
    public void addHeader(final String s, final String s1) {
    }

    @Override
    public void setIntHeader(final String s, final int i) {
    }

    @Override
    public void addIntHeader(final String s, final int i) {
    }

    @Override
    public void setStatus(final int i) {
    }

    @Override
    public void setStatus(final int i, final String s) {
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public String getHeader(final String s) {
        return null;
    }

    @Override
    public Collection<String> getHeaders(final String s) {
        return null;
    }

    @Override
    public Collection<String> getHeaderNames() {
        return null;
    }

    @Override
    public String getCharacterEncoding() {
        return null;
    }

    @Override
    public String getContentType() {
        return null;
    }


    private ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    public void setOutputStream(final ByteArrayOutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public String getOutputAsString() throws UnsupportedEncodingException {
        return outputStream.toString("UTF-8");
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        throw new UnsupportedOperationException("not available in this stub, sorry");
    }

    private ServletOutputStream servletOutputStream;

    private ServletOutputStream createServletOutputStream() {
        return new ServletOutputStream() {
            @Override
            public boolean isReady() {
                return true;
            }

            @Override
            public void setWriteListener(final WriteListener writeListener) {
                throw new UnsupportedOperationException("not available in this stub, sorry");
            }

            @Override
            public void write(final int b) throws IOException {
                outputStream.write(b);
            }

            @Override
            public void write(final byte[] b) throws IOException {
                outputStream.write(b);
            }

            @Override
            public void write(final byte[] b, final int off, final int len) throws IOException {
                outputStream.write(b, off, len);
            }

            @Override
            public void flush() throws IOException {
                outputStream.flush();
            }

            @Override
            public void close() throws IOException {
                outputStream.close();
            }
        };
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        if (servletOutputStream == null) {
            servletOutputStream = createServletOutputStream();
        }

        return servletOutputStream;
    }



    @Override
    public void setCharacterEncoding(final String s) {
    }

    @Override
    public void setContentLength(final int i) {
    }

    @Override
    public void setContentLengthLong(final long l) {
    }

    @Override
    public void setContentType(final String s) {
    }

    @Override
    public void setBufferSize(final int i) {
    }

    @Override
    public int getBufferSize() {
        return 0;
    }

    @Override
    public void flushBuffer() throws IOException {
    }

    @Override
    public void resetBuffer() {
    }

    @Override
    public boolean isCommitted() {
        return false;
    }

    @Override
    public void reset() {
    }

    @Override
    public void setLocale(final Locale locale) {
    }

    @Override
    public Locale getLocale() {
        return null;
    }
}
