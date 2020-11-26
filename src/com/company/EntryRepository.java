package com.company;

import com.company.model.Entry;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EntryRepository implements AutoCloseable {

    private static final String INSERT_QUERY = "INSERT INTO test(field) VALUES (?)";
    private static final String TRUNCATE_QUERY = "TRUNCATE test";
    private static final String FIND_ALL_QUERY = "SELECT field FROM test";
    private static final String FIELD_COLUMN = "field";

    private final Connection connection;
    private final Statement statement;

    public EntryRepository(String url, String driver, String username, String password) throws Exception {
        Class.forName(driver).getDeclaredConstructor().newInstance();
        connection = DriverManager.getConnection(url, username, password);
        statement = connection.createStatement();
    }

    public void truncate() throws SQLException {
        statement.executeUpdate(TRUNCATE_QUERY);
    }

    public void insertNRecords(Integer n) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(INSERT_QUERY);

        for (int i = 0; i < n; i++) {
            ps.setInt(1, i + 1);
            ps.addBatch();
        }
        ps.executeBatch();
    }

    public List<Entry> findAll() throws SQLException {
        List<Entry> result = new ArrayList<>();
        ResultSet resultSet = statement.executeQuery(FIND_ALL_QUERY);
        while (resultSet.next()) {
            Entry entry = new Entry(resultSet.getInt(FIELD_COLUMN));
            result.add(entry);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        this.connection.close();
    }
}
