package com.capgemini.bankapp.client;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
/*import com.mysql.jdbc.Connection;*/

public class ConnectionTesting {

	public static void main(String[] args) {
		String dburl = "jdbc:mysql://localhost:3306/bankappdb";
		String userName = "root";
		String password = "root";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(dburl, userName, password);
			if (connection != null)
				System.out.println("connected");
		} catch (ClassNotFoundException e) {
			System.out.println("Failed to load driver class make sure that .class file is available");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Failed to connect");
			e.printStackTrace();
		}

	}

}
