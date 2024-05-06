package org.insta.content.model.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * <p>
 * Utility class for setting IDs retrieved from a database query result.
 * </p>
 *
 * @author Mohamed Yasar
 * @version 1.0 6 Feb 2024
 */
public final class IdSetter {

    private static IdSetter idSetter;

    /**
     * <p>
     * Private constructor to restrict object creation outside of the class.
     * </p>
     */
    private IdSetter() {
    }

    /**
     * <p>
     * Returns the singleton instance of the IdSetter class.
     * </p>
     *
     * @return The singleton instance of IdSetter class.
     */
    public static IdSetter getInstance() {
        return idSetter == null ? new IdSetter() : idSetter;
    }

    /**
     * <p>
     * Sets the ID retrieved from the database query result to the provided entity.
     * </p>
     *
     * @param preparedStatement the PreparedStatement object used for the query
     * @param object            the entity to set the ID for
     * @return the ID set to the entity
     */
    public <T extends Common> Integer setId(final PreparedStatement preparedStatement, final T object) {
        object.setId(0);

        try (final ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            if (resultSet.next()) {
                object.setId(resultSet.getInt("id"));

                return object.getId();
            }
        } catch (SQLException ignored) {
        }
        return object.getId();
    }

    /**
     * <p>
     * Retrieves the ID from the database query result.
     * </p>
     *
     * @param preparedStatement the PreparedStatement object used for the query
     * @return the retrieved ID
     */
    public int setId(final PreparedStatement preparedStatement) {
        try (final ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        } catch (SQLException ignored) {
        }
        return 0;
    }
}
