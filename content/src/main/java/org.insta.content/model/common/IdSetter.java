package org.insta.content.model.common;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IdSetter {

    private static IdSetter idSetter;

    private IdSetter() {
    }

    public static IdSetter getInstance() {
        return idSetter == null ? new IdSetter() : idSetter;
    }

    public <T extends Common>Integer setId(final PreparedStatement preparedStatement, final T user) {
        try (final ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));

                return user.getId();
            } else {
                user.setId(0);
            }
        } catch (SQLException ignored) {
            user.setId(0);
        }
        return user.getId();
    }

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
