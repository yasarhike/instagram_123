package org.insta.authentication.view;

/**
 * <p>
 * Contains the mobile, email and name for the key to login.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public enum Key {
    MOBILE(1), EMAIL(2), NAME(3);

    public final int id;

    Key(final int id) {
        this.id = id;
    }

    public static Key getKey(final int id) {
        return switch (id) {
            case 1 -> MOBILE;
            case 2 -> EMAIL;
            case 3 -> NAME;
            default -> null;
        };
    }
}
