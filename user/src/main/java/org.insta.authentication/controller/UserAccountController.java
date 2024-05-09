package org.insta.authentication.controller;

import org.insta.authentication.dao.UserAccountDAO;
import org.insta.authentication.dao.UserAccountDAOImpl;
import org.insta.authentication.model.User;
import org.insta.authentication.service.UserAccountService;
import org.insta.authentication.service.UserAccountServiceImplementation;

/**
 * <p>
 * Managing user accounts provides methods to interact with user profiles, including creation,
 * retrieval, updating, and deletion.
 * </p>
 *
 * <p>
 * This class acts as a controller for user account operations, providing methods to create, retrieve,
 * update, and delete user profiles. It delegates the actual implementation to the {@link UserAccountService}
 * and {@link UserAccountDAO} interfaces.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 * @see UserAccountService
 * @see UserAccountDAO
 */
public final class UserAccountController {

    private static UserAccountController profileController;
    private final UserAccountDAO userAccountDAOImpl;
    private final UserAccountService userAccountServiceImplementation;

    /**
     * <p>
     * Restrict object creation outside the class.
     * </p>
     */
    private UserAccountController() {
        userAccountDAOImpl = UserAccountDAOImpl.getInstance();
        userAccountServiceImplementation = UserAccountServiceImplementation.getInstance();
    }

    /**
     * <p>
     * Returns the singleton instance of UserAccountController class.
     * </p>
     *
     * @return Singleton instance of UseAccountController class.
     */
    public static UserAccountController getInstance() {
        return profileController == null ? new UserAccountController() : profileController;
    }

    /**
     * <p>
     * Create a new user profile.
     * </p>
     *
     * @param user The {@link User} object representing the user profile to be created.
     * @return The profile data as a byte array if the profile is successfully created, otherwise null.
     */
    public byte[] createProfile(final User user) {
        return userAccountServiceImplementation.createProfile(user);
    }

    /**
     * <p>
     * Retrieves the user profile based on the provided ID.
     * </p>
     *
     * @param id The unique identifier of the profile.
     * @return The profile data as a byte array if found, otherwise null.
     */
    public byte[] getProfile(final int id) {
        return userAccountServiceImplementation.getProfile(id);
    }

    /**
     * <p>
     * Updates the user profile with the provided information.
     * </p>
     *
     * @param user {@link User} Object containing updated profile information.
     * @return Updated profile data as a byte array if the profile is successfully updated, otherwise null.
     */
    public byte[] updateProfile(final User user) {
        return userAccountServiceImplementation.updateProfile(user);
    }

    /**
     * <p>
     * Deletes the user profile associated with the given ID.
     * </p>
     *
     * @param id The unique identifier of the user profile to be deleted.
     * @return True if the profile is successfully deleted, otherwise false.
     */
    public boolean deleteProfile(final int id) {
        return userAccountDAOImpl.deleteProfile(id);
    }
}
