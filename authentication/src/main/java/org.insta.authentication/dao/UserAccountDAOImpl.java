package org.insta.authentication.dao;

import org.insta.authentication.dao.querybuilder.QueryBuilder;
import org.insta.authentication.exception.ProfileCreationFailedException;
import org.insta.authentication.exception.ProfileDeleteFailedException;
import org.insta.authentication.exception.ProfileRetrivalFailedException;
import org.insta.authentication.exception.ProfileUpdateFailedException;
import org.insta.authentication.model.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.insta.databaseconnection.DatabaseConnection;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Manage user accounts.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class UserAccountDAOImpl implements UserAccountDAO {

    private static UserAccountDAOImpl userAccountDAOImpl;
    private final Connection connection;
    private final QueryBuilder queryBuilder;
    private final Logger logger = LogManager.getLogger(UserAccountDAOImpl.class);

    /**
     * <p>
     * Prevent instantiation from outside the class.
     * </p>
     */
    private UserAccountDAOImpl() {
        queryBuilder = QueryBuilder.getInstance();
        connection = DatabaseConnection.get();
    }

    /**
     * <p>
     * Returns the singleton instance of UserAccountDAOImpl.
     * </p>
     *
     * @return Singleton instance of UserAccountDAOImpl.
     */
    public static UserAccountDAOImpl getInstance() {
        return userAccountDAOImpl == null ? userAccountDAOImpl = new UserAccountDAOImpl() : userAccountDAOImpl;
    }

    /**
     * <p>
     * Creates a user profile.
     * </p>
     *
     * @param user {@link User} contains the user data.
     * @return The ID of the created user profile.
     */
    public int createProfile(final User user){

        user.setUserId(0);
        try {
            connection.setAutoCommit(false);

            final PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.getCreateAccountQuery(), Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getMobileNumber());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());

            if (preparedStatement.executeUpdate() > 0) {
                queryBuilder.setUserId(preparedStatement, user);
                return createAddress(user);
            }

        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                throw new ProfileCreationFailedException("Roll back failed at account creation");
            }
            throw new ProfileCreationFailedException("Profile creation failed");
        }

        return user.getUserId();
    }

    /**
     * <p>
     * Creates a user address.
     * </p>
     *
     * @param user {@link User} contains the user data
     * @return id of the created address otherwise return zero.
     */
    private int createAddress(final User user) {
        try(final PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.getCreateAddressQuery())) {

            preparedStatement.setInt(1, user.getAddress().getDoorNumber());
            preparedStatement.setString(2, user.getAddress().getState());
            preparedStatement.setInt(3, user.getUserId());

            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
                return user.getUserId();
            }
        } catch(Exception ignored) {
        }
        return user.getUserId();
    }

    /**
     * <p>
     * Updates a user profile.
     * </p>
     *
     * @param user {@link User} contains the updated user data.
     * @return True if the user profile is successfully updated, otherwise false.
     */
    public int updateProfile(final User user) {
        final List<Object> list = new ArrayList<>();

        try (final PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.generateQuery(user, list)
        , Statement.RETURN_GENERATED_KEYS)) {
            int index = 0;

            connection.setAutoCommit(false);

            for (final Object element : list) {
                preparedStatement.setObject(++index, element);
            }

            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
                return queryBuilder.setUser(preparedStatement.getResultSet()).getUserId();
            }
        } catch (final SQLException exception) {

            try {
                connection.rollback();
            } catch (SQLException sqlException) {
                throw new ProfileUpdateFailedException("Roll back failed at account update");
            }

            throw new ProfileUpdateFailedException("Profile update failed");
        }
        return 0;
    }

    /**
     * <p>
     * Retrieves a user profile by ID.
     * </p>
     *
     * @param id contains the ID of the user profile.
     * @return The user profile if found, null otherwise.
     */
    public User getProfile(final int id) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.getProfileQuery())) {

            connection.setAutoCommit(true);
            preparedStatement.setInt(1,id);

            final ResultSet resultSet = preparedStatement.executeQuery();

            return queryBuilder.setUser(resultSet);

        } catch (SQLException exception) {
            throw new ProfileRetrivalFailedException("Profile retrival failed");
        }
    }

    /**
     * <p>
     * Deletes a user profile by ID.
     * </p>
     *
     * @param id contains the ID of the user profile to be deleted.
     * @return True if the user profile is successfully deleted, otherwise false.
     */
    public boolean deleteProfile(final int id) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.getDeleteAccountQuery())) {

            connection.setAutoCommit(false);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);

            if (preparedStatement.executeUpdate() > 0) {
                connection.commit();
                return true;
            }
        } catch (SQLException sqlException) {
            try {
                connection.rollback();
            } catch (SQLException sqlException1) {
                throw new ProfileDeleteFailedException("Roll back failed at profile deletion");
            }
            throw new ProfileDeleteFailedException("Profile deletion failed");
        }
        return false;
    }

    public boolean checkUserExists(final User user) {
        try (final PreparedStatement preparedStatement = connection.prepareStatement(queryBuilder.checkUserExists()
        , Statement.RETURN_GENERATED_KEYS)) {

            if (user.getName() != null) preparedStatement.setString(1, user.getName());
            if (user.getMobileNumber() != null) preparedStatement.setString(2, user.getMobileNumber());
            if (user.getEmail() != null) preparedStatement.setString(3, user.getEmail());

            if (preparedStatement.executeUpdate() > 0) {
               return true;
            }
        } catch (SQLException sqlException) {
            throw new ProfileCreationFailedException("Profile creation failed");
        }
        return false;
    }
}
