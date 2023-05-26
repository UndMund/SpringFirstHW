package org.example.dao;

import lombok.AllArgsConstructor;
import lombok.ToString;
import org.example.entity.Company;
import org.example.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
@ToString
@Repository
public class CompanyDao {
    private Connection connection;

    public CompanyDao(@Value("#{connectionManager.open()}") Connection connection) {
        this.connection = connection;
    }

    private static String FIND_BY_ID_SQL = """
            SELECT id,
                company_name
            FROM company
            WHERE id = ?
            """;


    public Optional<Company> findById(Long id) {
        try (var statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            Company company = null;
            statement.setLong(1, id);
            var result = statement.executeQuery();
            if (result.next()) {
                company = buildCompany(result);
            }
            return Optional.ofNullable(company);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Company buildCompany(ResultSet result) throws SQLException {
        return new Company(
                result.getLong("id"),
                result.getString("company_name")
        );
    }
}
