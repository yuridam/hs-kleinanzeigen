package de.hs.da.hskleinanzeigen;

public class AdNotFoundException extends Exception {
    public AdNotFoundException(Integer id) {
        super("Could not find Ad " + id);

    }
}
