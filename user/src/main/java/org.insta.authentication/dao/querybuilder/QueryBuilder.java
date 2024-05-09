package org.insta.authentication.dao.querybuilder;

import org.insta.authentication.exception.UserNotFoundException;
import org.insta.authentication.model.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * <p>
 * Helper class to generate SQL queries for user account and address operations.
 * This class provides methods to generate SQL queries for creating, updating, deleting,
 * and retrieving user account and address information.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class QueryBuilder {

    private static QueryBuilder queryBuilder;

    /**
     * <p>
     * Prevent instantiation from outside the class.
     * </p>
     */
    private QueryBuilder() {
    }

    /**
     * <p>
     * Returns the singleton instance of QueryHandler.
     * </p>
     *
     * @return Singleton instance of QueryHandler.
     */
    public static QueryBuilder getInstance() {
        return queryBuilder == null ? new QueryBuilder() : queryBuilder;
    }


    /**
     * <p>
     * Appends query if the data field in user is not null; otherwise, it does not append.
     * </p>
     *
     * @param user      {@link User} Contains the user data.
     * @param queryList Refers to the query list.
     * @param list      Contains the values for the respective columns.
     */
    private void getPasswordQuery(User user, final List<String> queryList, List<Object> list) {
        if (user.getPassword() != null) {
            queryList.add("password = ?");
            list.add(user.getPassword());
        }
    }

    /**
     * <p>
     * Gets the query for creating a new account for the user.
     * </p>
     *
     * @return Query for creating a new account.
     */
    public String getCreateAccountQuery() {
        return String.join(" ", " INSERT INTO account (name, mobile, email, password) VALUES (?, ?, ?, ?)");
    }

    /**
     * <p>
     * Gets the query for creating a new address entry for the user.
     * </p>
     *
     * @return Query for creating a new address entry.
     */
    public String getCreateAddressQuery() {
        return String.join(" ", "INSERT INTO address (door_no, state, user_Id) VALUES (?, ?, ?)");
    }

    /**
     * <p>
     * Gets the query for deleting the user account.
     * </p>
     *
     * @return Query for deleting the user account and associated address.
     */
    public String getDeleteAccountQuery() {
        return String.join(" ", "Delete from address where user_id = ?;",
                "Delete from account where id = ?");
    }

    /**
     * <p>
     * Gets the query for retrieving the user profile.
     * </p>
     *
     * @return Query for retrieving the user profile including address details.
     */
    public String getProfileQuery() {
        return String.join(" ", "select * from account",
                "left join address on address.user_id = account.id",
                "where account.id = ?");
    }

    /**
     * <p>
     * Generates SQL query to check if a mobile number exists in the account table.
     * </p>
     *
     * @return SQL query to check mobile existence
     */
    public String checkMobileExists() {
        return String.join(" ", "select mobile from account ",
                " where mobile = ? ");
    }

    /**
     * <p>
     * Generates SQL query to check if an email exists in the account table.
     * </p>
     *
     * @return SQL query to check email existence
     */
    public String checkEmailExists() {
        return String.join(" ", "select email from account ",
                " where email = ? ");
    }

    /**
     * <p>
     * Generates SQL query to check if a name exists in the account table.
     * </p>
     *
     * @return SQL query to check name existence
     */
    public String checkNameExists() {
        return String.join(" ", "select name from account ",
                " where name = ? ");
    }

    /**
     * <p>
     * Sets the user ID based on the generated keys in the PreparedStatement.
     * </p>
     *
     * @param preparedStatement The PreparedStatement containing the generated keys.
     * @param user              The User object to set the user ID.
     * @return The generated user ID if set successfully, otherwise 0.
     */
    public Integer setUserId(final PreparedStatement preparedStatement, final User user) {
        try (final ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            if (resultSet.next()) {
                user.setUserId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                user.setMobileNumber(resultSet.getString("mobile"));
                user.setEmail(resultSet.getString("email"));

                return user.getUserId();
            } else {
                user.setUserId(0);
            }
        } catch (SQLException ignored) {
            user.setUserId(0);
        }
        return user.getUserId();
    }

    /**
     * <p>
     * Sets the user details based on the ResultSet.
     * </p>
     *
     * @param resultSet The ResultSet containing the generated data.
     * @return The User object with details set from the ResultSet.
     */
    public User setUser(final ResultSet resultSet) {
        User user = null;

        try {
            if (resultSet.next()) {
                user = new User();
                user.setUserId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setMobileNumber(resultSet.getString(3));
                user.setEmail(resultSet.getString(4));
                user.setPassword(resultSet.getString(5));
                user.getAddress().setDoorNumber(resultSet.getInt(7));
                user.getAddress().setState(resultSet.getString(8));
                user.getAddress().setStreetName(resultSet.getString(10));
                return user;
            }
        } catch (final SQLException exception) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }
}
