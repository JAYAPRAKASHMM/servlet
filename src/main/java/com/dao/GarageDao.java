package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import com.model.Product;

public class GarageDao {
	private String jdbcURL = "jdbc:mysql://localhost:3306/project";
    private String jdbcUsername = "root";
    private String jdbcPassword = "valo12345";
        private static final String INSERT_USERS_SQL = "INSERT INTO products(name,rs) VALUES (?, ?);";
	    private static final String SELECT_USER_BY_ID = "select id,name,rs from products where id =?";
	    private static final String SELECT_ALL_USERS = "select * from products";
	    private static final String DELETE_USERS_SQL = "delete from products where id = ?;";
        private static final String INSERT_S = "INSERT INTO store(name,rs,quantity) VALUES (?, ?, ?);";

	    public GarageDao() {}
	    
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
	    public Product getp(int id)throws SQLException {
	    	Product t=null;
	    	try (Connection connection = getConnection(); 
	        		PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID)) {
	            preparedStatement.setInt(1,id);
	            ResultSet resultSet = preparedStatement.executeQuery();
	            if (resultSet.next()) {
	                int price = resultSet.getInt("rs");
	                String productName = resultSet.getString("name");

	                t = new Product(productName,price);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
			return t;
	    }
	    public void store(Product item) throws SQLException {
	        //System.out.println(INSERT_USERS_SQL);
	        // try-with-resource statement will auto close the connection.
	        try (Connection connection = getConnection(); 
	        		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_S)) {
	            preparedStatement.setString(1, item.getName());
	            preparedStatement.setInt(2, item.getRs());
	            preparedStatement.setInt(3, item.getQuantity());

	           // System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public void insertProduct(Product item) throws SQLException {
	        //System.out.println(INSERT_USERS_SQL);
	        // try-with-resource statement will auto close the connection.
	        try (Connection connection = getConnection(); 
	        		PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
	            preparedStatement.setString(1, item.getName());
	            preparedStatement.setInt(2, item.getRs());
	           // System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
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
	                int id = rst.getInt("id");
	                String name = rst.getString("name");
	                int rs = rst.getInt("rs");
	               set.add(new Product(id,name,rs));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return set;
	    }
	    public boolean deleteUser(int id) throws SQLException {
	        boolean rowDeleted;
	        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
	            statement.setInt(1, id);
	            rowDeleted = statement.executeUpdate() > 0;
	        }
	        return rowDeleted;
	    }

	    private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }

}
