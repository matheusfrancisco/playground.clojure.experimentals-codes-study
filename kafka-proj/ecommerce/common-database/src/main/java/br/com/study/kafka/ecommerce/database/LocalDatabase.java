package br.com.study.kafka.ecommerce.database;

import java.sql.*;

public class LocalDatabase {

    private final Connection connection;

    public LocalDatabase(String name) throws SQLException {
        String url = "jdbc:sqlite:target/"+name+".db";
        this.connection = DriverManager.getConnection(url);
    }

    //yes this function is way too generic
    //according to your database tool avoid injection
    public void createIfNotExists(String sql) {
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException ex) {
            //be careful, the sql could be wrong, be really careful
            ex.printStackTrace();

        }
    }

    private PreparedStatement prepare(String statement, String[] params) throws SQLException {
        var preparedStatement = connection.prepareStatement(statement);
        for (int i = 0; i < params.length; i++) {
            preparedStatement.setString(i + 1, params[1]);

        }
        return preparedStatement;
    }


    public boolean update(String statement, String ... params) throws SQLException {
        return prepare(statement, params).execute();
    }


    public ResultSet query(String query, String ... params) throws SQLException {
        return prepare(query, params).executeQuery();
    }

    public void close() throws SQLException {
        this.connection.close();
    }
}
