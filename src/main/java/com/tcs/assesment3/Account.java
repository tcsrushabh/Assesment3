package com.tcs.assesment3;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Account{	

	private static Logger logger = LogManager.getLogger(Account.class);
	public static void main(String[] args) {
		String DB_URL = "jdbc:mysql://localhost/assesment3";
		String DB_USER = "root";
		String DB_PASSWORD = "Nuvelabs123$";
		try(Connection connection = DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
				Statement statement = connection.createStatement();){
//				Create(statement);
//				Deposit(statement);
//			    withdraw(statement);
			    retrieve(statement);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	private static void Create(Statement statement) throws SQLException {
		statement.execute("INSERT INTO ACCOUNTS VALUES(1,'ABC',10000,'17-08-2021','ACTIVE','SAVINGS',1);");
		statement.execute("INSERT INTO ADDRESS VALUES(1,'MUMBAI','MAHARASHTRA',400067,1234567890,221,'BAKER STREET',1);");
	}
	private static void Deposit(Statement statement) throws SQLException {
		statement.executeUpdate("Update Accounts set balance_amount = balance_amount + 15000 where Account_id = 1");
	}
	private static void withdraw(Statement statement) throws SQLException {
		
		statement.executeUpdate("Update Accounts set balance_amount=balance_amount-5000 where balance_amount>=5000 and Account_id = 1" );
		logger.debug("Updated");
	}
	private static void retrieve(Statement statement) throws SQLException {
		ResultSet resultSet = statement.executeQuery("SELECT * from ACCOUNTS where Account_id=101");
		while (resultSet.next()) {
			System.out.println(resultSet.getInt("Account_id"));
			System.out.println(resultSet.getString("Owner_name"));
			System.out.println(resultSet.getInt("Balance_amount"));
			System.out.println(resultSet.getString("Create_date"));
			System.out.println(resultSet.getString("Account_status"));
			System.out.println(resultSet.getString("Account_type"));
			System.out.println(resultSet.getInt("Address_id"));
		}
	}
}
