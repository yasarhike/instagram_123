package org.insta.authentication.model;

/**
 * <p>
 * Constants for User credential details.
 * </p>
 *
 * @author Mohamed Yasar k
 * @version 1.0 6 Feb 2024
 */
public enum Credentials {

    MOBILE(1), EMAIL(2), NAME(3), SUCCESS(0);

    private final int id;

    Credentials(final int id) {
        this.id = id;
    }

    public static Credentials getMedia(final int id) {
        return switch (id) {
            case 1 -> MOBILE;
            case 2 -> EMAIL;
            case 3 -> NAME;
            default -> SUCCESS;
        };
    }

    public int getId() {
        return id;
    }
}
