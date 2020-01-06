package de.hs.da.hskleinanzeigen.exception;

public class UserExceptionInterceptor extends Exception {
    public UserExceptionInterceptor(Integer id) {
        super("Not found! ID:" + id);
    }
}
