package org.insta.authentication.dao;

import org.insta.authentication.model.User;

/**
 * <p>
 * Manage user accounts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public interface UserAccountDAO {

    /**
     * <p>
     * Creates a user profile.
     * </p>
     *
     * @param user {@link User} contains the user data.
     * @return The ID of the created user profile.
     */
    int createProfile(final User user);

    /**
     * <p>
     * Retrieves a user profile by ID.
     * </p>
     *
     * @param id contains the ID of the user profile.
     * @return The user profile if found, null otherwise.
     */
    User getProfile(final int id);

    /**
     * <p>
     * Updates a user profile.
     * </p>
     *
     * @param user {@link User} contains the updated user data.
     * @return True if the user profile is successfully updated, otherwise false.
     */
    int updateProfile(final User user);

    /**
     * <p>
     * Deletes a user profile by ID.
     * </p>
     *
     * @param id contains the ID of the user profile to be deleted.
     * @return True if the user profile is successfully deleted, otherwise false.
     */
    boolean deleteProfile(final int id);

    boolean checkUserExists(final User user);
}
