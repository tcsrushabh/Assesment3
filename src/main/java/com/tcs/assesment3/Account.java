package com.tcs.assesment3;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Scanner;

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
				Create(statement);
				Deposit(statement);
				withdraw(statement);
			    retrieve(statement);
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	static int accid = 101,addid = 2002;
	private static void Create(Statement statement) throws SQLException {
		
		statement.execute("INSERT INTO ACCOUNTS VALUES(102,'ABC',10000,'17/06.2021','ACTIVE','SAVINGS',2002);");
		statement.execute("INSERT INTO ADDRESS VALUES(2002,'MUMBAI','MAHARASHTRA',400067,1234567890,221,'BAKER STREET',102);");
	}
	private static void Deposit(Statement statement) throws SQLException {
		Scanner sc = new Scanner(System.in);
		logger.info("Enter Account id and amount to deposit");
		int id = sc.nextInt();
		int amount = sc.nextInt();
		statement.executeUpdate("Update Accounts set balance_amount = balance_amount + "+amount+" where Account_id = "+id+"");
		ResultSet resultSet = statement.executeQuery("SELECT balance_amount from ACCOUNTS where Account_id="+id+"");
		while(resultSet.next()) {
			logger.info(resultSet.getInt("balance_amount"));
		}
	}
	private static void withdraw(Statement statement) throws SQLException {
		Scanner sc = new Scanner(System.in);
		logger.info("Enter Account id and amount to deposit");
		int id = sc.nextInt();
		int amount = sc.nextInt();
		ResultSet resultSet = statement.executeQuery("SELECT balance_amount from ACCOUNTS where Account_id="+id+"");
		while(resultSet.next()) {
			int amount1 = resultSet.getInt("balance_amount");
			if(amount1>=amount) {
				statement.executeUpdate("Update Accounts set balance_amount="+amount1+"-"+amount+" where Account_id = "+id+"" );
				logger.debug("Updated");
			}
			else {
				logger.info("Insufficient Balance");
			}
		}
		
	}
	private static void retrieve(Statement statement) throws SQLException {
		Scanner sc = new Scanner(System.in);
		logger.info("Enter Account id");
		int id = sc.nextInt();
		ResultSet resultSet = statement.executeQuery("SELECT * from ACCOUNTS where Account_id="+id+"");
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
