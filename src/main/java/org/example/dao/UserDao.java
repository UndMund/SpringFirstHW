package org.example.dao;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

@AllArgsConstructor
@ToString
public class UserDao {
    private Connection connection;
    private final CompanyDao companyDao;
    private static String FIND_BY_ID_SQL = """
            SELECT id,
                username,         
                company_id
            FROM users
            WHERE id = ?
            """;


    public Optional<User> findById(Long id) {
        try (var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            User user = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next()) {
                user = buildUser(result);
            }
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User buildUser(ResultSet result) throws SQLException {
        return new User(
                result.getLong("id"),
                result.getString("username"),
                companyDao.findById(
                        result.getLong("company_id")
                ).orElse(null)
        );
    }
}
