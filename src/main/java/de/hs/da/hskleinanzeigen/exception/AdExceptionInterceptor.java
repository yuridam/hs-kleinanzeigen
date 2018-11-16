package de.hs.da.hskleinanzeigen.exception;

public class AdExceptionInterceptor extends Exception {
    public AdExceptionInterceptor(Integer id) {
        super("Not found! ID:" + id);
    }
}
