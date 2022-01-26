package net.usermanagement.dao;

import java.sql.*;
import java.util.ArrayList;

import net.usermanagement.model.User;

//All the CRUD operations are in the DAO class
// DAO - Data Access Object (It's a design pattern which separates 
// all the database related things to a separate class)

//This DAO class provides CRUD operations for the table users in the database.

public class UserDAO {
	private String jdbcURL = "jdbc:mysql://localhost:3308/demo";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	
	
	
	
	private static final String INSERT_USER_SQL = "INSERT INTO users" + " (name,email,country) VALUES " + " (?, ?, ?);";
	
	private static final String SELECT_USER_BY_ID = "select id,name,email,country from users where id = ?;";
	private static final String SELECT_ALL_USERS = "select * from users";
	private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
	private static final String UPDATE_USERS_SQL = "update users set name = ?, email= ?, country= ? where id= ?;";
	
	
	//This getConnection() is used in below CRUD operations
	protected Connection getConnection() {
		Connection connection = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return connection;
	}
	
	
	//Create or insert user 
	public void insertUser(User user) throws SQLException {
		try(Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)){
			
			preparedStatement.setString(1, user.getName());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getCountry());
			preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Something went wrong!");
		}
	}
	
	
	//update user
	public boolean updateUser(User user) throws SQLException {
		boolean rowUpdated;
		try(Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL)){
			
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setString(3, user.getCountry());
			statement.setInt(3, user.getId());
			
			rowUpdated = statement.executeUpdate() > 0;
			
		}	
		return rowUpdated;
	}
	
	
	//select user by id
	public User selectUser(int id) throws SQLException {
		User user = null;
		//Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				//Step 2: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
			preparedStatement.setInt(1, id);
			System.out.println(preparedStatement);
			//Step 3: Execute the query or update query
			//executeQuery() method is used becz we need to display a result set 
			ResultSet rs = preparedStatement.executeQuery();
			
			//Step 4: Process the ResultSet object.
			while(rs.next()) {
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				user = new User(id, name, email, country);
			}
		}
		return user;
		
	}
	
	
	//select users
	public ArrayList<User> selectAllUsers() throws SQLException {
		ArrayList<User> users = new ArrayList<>();
		//Step 1: Establishing a Connection
		try (Connection connection = getConnection();
				//Step 2: Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
			System.out.println(preparedStatement);
			//Step 3: Execute the query or update query
			//executeQuery() method is used becz we need to display a result set 
			ResultSet rs = preparedStatement.executeQuery();
			
			//Step 4: Process the ResultSet object.
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String country = rs.getString("country");
				users.add(new User(id, name, email, country));
			}
		}
		return users;
		
	}
	
	
	//delete user
	public boolean deleteUser(int id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);){
			
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
	
		}
		return rowDeleted;
	}
}
