package de.hs.da.hskleinanzeigen.type;

public enum AdvertisementType {
    OFFER {
        public String toString() {
            return "Offer";
        }
    },

    REQUEST {
        public String toString() {
            return "Request";
        }
    }
}
