package org.insta.authentication.service;

import org.insta.authentication.dao.UserAccountDAO;
import org.insta.authentication.dao.UserAccountDAOImpl;
import org.insta.authentication.groups.ValidationOrder;
import org.insta.authentication.model.User;
import org.insta.wrapper.jsonvalidator.ObjectValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Implementation of the UserService interface for user account service.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class UserAccountServiceImplementation implements UserAccountService {

    private static UserAccountServiceImplementation userAccountServiceImplementation;
    private final UserAccountDAO userAccountDAO;
    private final ObjectValidator<User, ValidationOrder> objectValidator;
    private static int userId = 1;
    public final Map<String, User> usersByMailEmail;
    public final Map<Integer, User> usersById;

    /**
     * <p>
     * Private constructor to restrict object creation outside the class
     * </p>
     */
    private UserAccountServiceImplementation() {
        usersByMailEmail = new HashMap<>();
        usersById = new HashMap<>();
        userAccountDAO = UserAccountDAOImpl.getInstance();
        objectValidator = new ObjectValidator<>();
    }

    /**
     * <p>
     * Creates a singleton instance of UserAccountServiceImplementation class if it is not already created.
     * </p>
     *
     * @return the singleton instance of UserAccountServiceImplementation class.
     */
    public static UserAccountServiceImplementation getInstance() {
        return userAccountServiceImplementation == null ? userAccountServiceImplementation = new UserAccountServiceImplementation() : userAccountServiceImplementation;
    }

    /**
     * <p>
     * Creates a new user profile.
     * </p>
     *
     * @param user The user object containing the user data to be created.
     * @return A byte array representing the created user profile, or null if creation failed.
     */
    public byte[] createProfile(final User user) {
        final byte[] violations = objectValidator.validate(user, ValidationOrder.class);

        if (!userAccountDAO.checkUserExists(user))  return objectValidator.stringManualResponse("user already registered");

        return violations.length > 0 ? objectValidator.forFailureResponse(violations, false)
                : objectValidator.forSuccessResponse(userAccountDAO.createProfile(user), violations);
    }

    /**
     * <p>
     * Retrieves a user profile based on the provided user ID.
     * </p>
     *
     * @param id The ID of the user profile to retrieve.
     * @return A byte array representing the retrieved user profile, or null if no profile found.
     */
    public byte[] getProfile(final int id) {
        return objectValidator.objectResponse(userAccountDAO.getProfile(id));
    }

    /**
     * <p>
     * Validates the user input details and return the user object if correct.
     * </p>
     *
     * @param mobileOrEmail Refers mobile number or email of the user.
     * @param password      Refers the password of the user.
     * @return {@link User} Returns the User object if successful, otherwise null.
     */
    private User getProfileByMailEmail(final String mobileOrEmail, final String password) {
        return usersByMailEmail.getOrDefault(mobileOrEmail, null);
    }

    /**
     * <p>
     * Validates the user input details and return the user object if correct.
     * </p>
     *
     * @param id       Refers the ID of the user.
     * @param password Refers the password of the user.
     * @return {@link User} Returns the User object if successful, otherwise null.
     */
    private User getProfileById(final int id, final String password) {
        return usersById.getOrDefault(id, null);
    }

    /**
     * <p>
     * Validates the user input details and allow to create an account by mobile number.
     * </p>
     *
     * @param user Refers to the {@link User} object representing the user account.
     * @return True if the account is successfully created, otherwise false.
     */
    private boolean createProfileByMobile(final User user) {
        if (!usersByMailEmail.containsKey(user.getMobileNumber())) {
            usersByMailEmail.put(user.getMobileNumber(), user);
            usersById.put(userId, usersByMailEmail.get(user.getMobileNumber()));
            user.setUserId(userId++);
            return true;
        }
        return false;
    }

    /**
     * <p>
     * Validates the user input details and allow to create an account by mail.
     * </p>
     *
     * @param user Refers to the {@link User} object representing the user account.
     * @return True if the account is successfully created, otherwise false.
     */
    private boolean createProfileByEmail(final User user) {
        if (!usersByMailEmail.containsKey(user.getEmail()) && !usersById.containsKey(user.getUserId())) {
            usersByMailEmail.put(user.getEmail(), user);
            usersById.put(userId, usersByMailEmail.get(user.getEmail()));
            user.setUserId(userId++);
            return true;
        }
        return false;
    }

    /**
     * <p>
     * Checks if a user with the given ID is registered.
     * </p>
     *
     * @param user ID of the user to check.
     * @return True if the user with the given ID is registered, otherwise false.
     */
    public boolean isUserExists(final int user) {
        return usersById.containsKey(user);
    }

    /**
     * <p>
     * Updates the specified type of information for the given user.
     * </p>
     *
     * @param type     Refers to the type of information to be updated (e.g., "name", "email", "password", "mobile").
     * @param newValue Refers to the new value to be set for the specified type of information.
     * @param user     Refers to the {@link User} whose information is to be updated.
     */
    public void updateProfile(final String type, final String newValue, final User user) {
        switch (type) {
            case "name" : updateName(newValue, user);
            case "email" : updateMail(newValue, user);
            case "password" : updatePassword(newValue, user);
            case "mobile" : updateMobile(newValue, user);
            default : {
            }
        }
    }

    /**
     * <p>
     * Updates the name of the user.
     * </p>
     *
     * @param newName The new name to be set for the user.
     * @param user    Refers to the {@link User} whose name is to be updated.
     */
    private void updateName(final String newName, final User user) {
        if (usersById.containsKey(user.getUserId())) {
            usersById.get(user.getUserId()).setName(newName);
            user.setName(newName);
        }
    }

    /**
     * <p>
     * Updates the email of the user.
     * </p>
     *
     * @param newValue The new email to be set for the user.
     * @param user     Refers to the {@link User} whose email is to be updated.
     */
    private void updateMail(final String newValue, final User user) {
        if (usersByMailEmail.containsKey(user.getEmail())) {
            usersByMailEmail.get(user.getEmail()).setEmail(newValue);
            usersByMailEmail.put(newValue, usersByMailEmail.get(user.getEmail()));
            usersByMailEmail.remove(user.getEmail());
            user.setEmail(newValue);
        }
    }

    /**
     * <p>
     * Updates the mobile of the user.
     * </p>
     *
     * @param newValue The new mobile to be set for the user.
     * @param user     Refers to the {@link User} whose mobile is to be updated.
     */
    private void updateMobile(final String newValue, final User user) {
        if (usersByMailEmail.containsKey(user.getMobileNumber())) {
            usersByMailEmail.get(user.getMobileNumber()).setMobileNumber(newValue);
            usersByMailEmail.put(newValue, usersByMailEmail.get(user.getMobileNumber()));
            usersByMailEmail.remove(user.getMobileNumber());
            user.setMobileNumber(newValue);
        }
    }

    /**
     * <p>
     * Updates the password of the user.
     * </p>
     *
     * @param newValue The new password to be set for the user.
     * @param user     Refers to the {@link User} whose password is to be updated.
     */
    private void updatePassword(final String newValue, final User user) {
        usersByMailEmail.get(user.getPassword()).setPassword(newValue);
        user.setPassword(newValue);
    }

    /**
     * <p>
     * Updates an existing user profile.
     * </p>
     *
     * @param user The user object containing the updated user data.
     * @return A byte array representing the updated user profile, or null if update failed.
     */
    public byte[] updateProfile(final User user) {
        final byte[] violations = objectValidator.validate(user, ValidationOrder.class);
        return violations.length > 0 ? objectValidator.forFailureResponse(violations, false)
                : objectValidator.forSuccessResponse(userAccountDAO.updateProfile(user), violations);
    }

    /**
     * <p>
     * Deletes a user profile based on the provided user ID.
     * </p>
     *
     * @param id The ID of the user profile to delete.
     * @return A byte array representing the deleted user profile, or null if update failed.
     */
    public byte[] deleteProfile(final int id) {
        return objectValidator.manualResponse(userAccountDAO.deleteProfile(id));
    }
}

