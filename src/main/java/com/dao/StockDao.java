package com.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.model.*;
public class StockDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/project";
    private String jdbcUsername = "root";
    private String jdbcPassword = "valo12345";
    private static final String SELECT_ALL_USERS = "select * from store";
    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }
    public List < Product > selectAllProduct() 
    {
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Product > set = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rst = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rst.next()) {
                String name = rst.getString("name");
                int rs = rst.getInt("rs");
                int q = rst.getInt("quantity");

               set.add(new Product(name,rs,q));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return set;
    }
}
