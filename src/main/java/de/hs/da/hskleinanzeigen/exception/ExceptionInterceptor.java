package de.hs.da.hskleinanzeigen.exception;

public class ExceptionInterceptor extends Exception {
    public ExceptionInterceptor(Integer id) {
        super("Not found! ID:" + id);
    }
}
