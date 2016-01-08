package com.healthdb.dbconn;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

public class DatbaseConnector {
	private static DatbaseConnector dbConnector = null;
	private Connection dbConnection = null;
	private Statement queryString = null;

	public static DatbaseConnector getInstance() {
		if (dbConnector == null) {
			dbConnector = new DatbaseConnector();
			dbConnector.createConnection();
		}
		return dbConnector;
	}

	private void createConnection() {
		try {
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:C:\\Users\\ge\\Documents\\HealthSystem\\SQLIte\\current_doc.db";
			dbConnection = DriverManager.getConnection(dbURL);
			if (dbConnection != null) {
				System.out.println("Connected to the database");
				DatabaseMetaData dm = (DatabaseMetaData) dbConnection.getMetaData();
				System.out.println("Driver name: " + dm.getDriverName());
				System.out.println("Driver version: " + dm.getDriverVersion());
				System.out.println("Product name: " + dm.getDatabaseProductName());
				System.out.println("Product version: " + dm.getDatabaseProductVersion());
			}
			queryString = dbConnection.createStatement();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void createTable() {
		try {

			String sql = "CREATE TABLE tblcustomers(Id INT PRIMARY KEY, Name TEXT NOT NULL, Sex TEXT NOT NULL,"
					+ " Address TEXT NOT NULL, Email TEXT NOT NULL, Phone TEXT NOT NULL)";
			queryString.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}

	public void insertSampleData(String userName, String password, String mailID, String gender, String medicalStream,
			String specialization, String fullName, String degree, String designation, String services, int experience,
			String profilePath, String sPONCERSHIP) {
		String sqlinsertion = "INSERT INTO doctors("
				+ "userName,password,mailID,gender,medicalStream,specialization,fullName,degree,designation,services,experience,profilePath,sPONCERSHIP)";
		sqlinsertion += "VALUES('" + userName + "','" + password + "','" + mailID + "','" + gender + "','"
				+ medicalStream + "','" + specialization + "','" + fullName + "','" + degree + "','" + designation
				+ "','" + services + "'," + experience + ",'" + profilePath + "','" + sPONCERSHIP + "')";
		try {
			queryString.executeUpdate(sqlinsertion);
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}

	public void getDoctorDataFromDB(DefaultTableModel datamodel) {
		String sqlselection = "SELECT * FROM doctors";
		// Reset the table model
		datamodel.setRowCount(0);
		
		// Fetch the data from DB
		try {
			ResultSet result = queryString.executeQuery(sqlselection);
			if (result != null) {
				while (result.next()) {
					datamodel.addRow(new Object[] { result.getInt("ID"), result.getString("UserName"),
							result.getString("Password"), result.getString("MailID"), result.getString("Gender"),
							result.getString("MedicalStream"), result.getString("Specialization"),
							result.getString("FullName"), result.getString("Degree"), result.getString("Designation"),
							result.getString("Services"), result.getInt("Experience"), result.getString("ProfilePath"),
							result.getString("SPONCERSHIP") });
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		
		datamodel.fireTableDataChanged();

	}

	public void closeConnection() throws SQLException {
		queryString.close();
		dbConnection.close();
	}

	public void insertSampleData(String userName, String password, String fullName) {

		String sqlinsertion = "INSERT INTO doctors(userName,password,fullName)" + "VALUES('" + userName + "','"
				+ password + "','" + fullName + "')";
		try {
			queryString.executeUpdate(sqlinsertion);
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}
