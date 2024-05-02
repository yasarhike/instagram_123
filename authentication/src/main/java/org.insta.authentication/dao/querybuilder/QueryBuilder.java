package org.insta.authentication.dao.querybuilder;

import org.insta.authentication.model.User;
import org.insta.authentication.view.Key;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        return queryBuilder == null ? queryBuilder = new QueryBuilder() : queryBuilder;
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
            System.out.println("Operation failed");
        }
        return user;
    }


    /**
     * <p>
     * Gets query for mobile, email, and name for login.
     * </p>
     *
     * @param key The Key enum containing the type of query.
     * @return The generated query based on the user type, otherwise null.
     */
    public String getQuery(final Key key) {
        switch (key) {
            case MOBILE -> {
                return String.join(" ", "select * from account", "left join address on address.user_id = account.id", "where account.mobile = ? and account.password = ?");
            }
            case EMAIL -> {
                return String.join(" ", "select * from account", "left join address on address.user_id = account.id", "where account.email = ? and account.password = ? ");
            }
            case NAME -> {
                return String.join(" ", "select * from account", "left join address on address.user_id = account.id", "where account.name = ? and account.password = ? ");
            }
        }
        return null;
    }

    /**
     * <p>
     * Generates an SQL query for updating user account.
     * </p>
     *
     * @param user {@link User} Contains the user data.
     * @param list Contains the values for the respective columns.
     * @return Appended query if the data is not null; otherwise, it does not append.
     */
    private String generateQueryAccount(final User user, final List<Object> list) {
        final List<String> queryList = new ArrayList<>();

        executeQueryAccount(user, queryList, list);

        final String query = String.join(" ", "UPDATE account SET ", String.join(",", queryList), " where id =  ", Integer.toString(user.getUserId()));

        return !queryList.isEmpty() ? query : null;
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
     * Generates an SQL query for updating address entity.
     * </p>
     *
     * @param user {@link User} Contains the user data.
     * @param list Contains the values for the respective columns.
     * @return Query for the address entity.
     */
    private String generateQueryAddress(final User user, final List<Object> list) {
        List<String> queryList = new ArrayList<>();

        executeQueryAddress(user, queryList, list);

        final String query = String.join(" ", " UPDATE address SET ", String.join(",", queryList), " where id =  ", Integer.toString(user.getUserId()));

        return !queryList.isEmpty() ? query : null;
    }


    /**
     * <p>
     * Executes the flow to generate query for address entity.
     * </p>
     *
     * @param user      {@link User} Contains the user data.
     * @param queryList Contains the query list.
     * @param list      Contains the values for the respective columns.
     */
    private void executeQueryAddress(final User user, final List<String> queryList, final List<Object> list) {
        getStateQuery(user, queryList, list);
        getStreetQuery(user, queryList, list);
        getDoorQuery(user, queryList, list);
    }

    /**
     * <p>
     * Executes the flow to generate query for account entity.
     * </p>
     *
     * @param user      {@link User} Contains the user data.
     * @param queryList Contains the query list.
     * @param list      Contains the values for the respective columns.
     */
    private void executeQueryAccount(final User user, final List<String> queryList, final List<Object> list) {
        getNameQuery(user, queryList, list);
        getMailQuery(user, queryList, list);
        getMobileQuery(user, queryList, list);
        getPasswordQuery(user, queryList, list);
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
    private void getDoorQuery(final User user, final List<String> queryList, final List<Object> list) {
        if (user.getAddress().getDoorNumber() != 0) {
            queryList.add("door_no = ?");
            list.add(user.getAddress().getDoorNumber());
        }
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
    private void getStreetQuery(final User user, final List<String> queryList, final List<Object> list) {
        if (user.getAddress().getStreetName() != null) {
            queryList.add("street = ?");
            list.add(user.getAddress().getStreetName());
        }
    }

    /**
     * <p>
     * Generates the query for the account and address tables.
     * </p>
     *
     * @param user {@link User} Contains the user data.
     * @param list Refers to value for the specific fields.
     * @return Generated string for the user profile.
     */
    public String generateQuery(final User user, final List<Object> list) {
        final String queryForAccount = generateQueryAccount(user, list);
        final String queryForAddress = generateQueryAddress(user, list);

        StringBuilder stringBuilder = new StringBuilder();

        if (queryForAccount == null) {
            stringBuilder.append(queryForAddress);
        } else if (queryForAddress == null) {
            stringBuilder.append(queryForAccount);
        } else {
            stringBuilder.append(queryForAccount).append(';').append(queryForAddress);
        }

        return stringBuilder.toString();
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
    private void getNameQuery(final User user, final List<String> queryList, final List<Object> list) {
        if (user.getName() != null) {
            queryList.add("name = ? ");
            list.add(user.getName());
        }
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
    private void getMailQuery(final User user, final List<String> queryList, final List<Object> list) {
        if (user.getEmail() != null) {
            queryList.add("email = ?");
            list.add(user.getEmail());
        }
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
    private void getMobileQuery(final User user, final List<String> queryList, final List<Object> list) {
        if (user.getMobileNumber() != null) {
            queryList.add("mobile = ?");
            list.add(user.getAddress().getState());
        }
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
    private void getStateQuery(final User user, final List<String> queryList, final List<Object> list) {
        if (user.getAddress().getState() != null) {
            queryList.add("state = ?");
            list.add(user.getAddress().getState());
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
        return String.join(" ", "Delete from address where user_id = ?;" + "Delete from account where id = ?");
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

    public String checkUserExists() {
        return String.join(" ", "select * from account",
                " where name = ? or mobile = ? or email ?");
    }
}
