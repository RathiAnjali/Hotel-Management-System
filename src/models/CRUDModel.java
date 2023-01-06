package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import app.Project;

public class CRUDModel extends Project {
	private Connection OracleConnection;
	private Boolean admin;
	private int id;

	private String adminName;

	private String adminUsername;

	private String adminEmail;

	private String adminPassword;

	private int adminAge;

	private String adminCity;

	private String adminState;

	private int adminPincode;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Boolean isAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

	/**
	 * @return the adminName
	 */
	public String getAdminName() {
		return adminName;
	}

	/**
	 * @param adminName the adminName to set
	 */
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	/**
	 * @return the adminUsername
	 */
	public String getAdminUsername() {
		return adminUsername;
	}

	/**
	 * @param adminUsername the adminUsername to set
	 */
	public void setAdminUsername(String adminUsername) {
		this.adminUsername = adminUsername;
	}

	/**
	 * @return the adminEmail
	 */
	public String getAdminEmail() {
		return adminEmail;
	}

	/**
	 * @param adminEmail the adminEmail to set
	 */
	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	/**
	 * @return the adminPassword
	 */
	public String getAdminPassword() {
		return adminPassword;
	}

	/**
	 * @param adminPassword the adminPassword to set
	 */
	public void setAdminPassword(String adminPassword) {
		this.adminPassword = adminPassword;
	}

	/**
	 * @return the adminAge
	 */
	public int getAdminAge() {
		return adminAge;
	}

	/**
	 * @param adminAge the adminAge to set
	 */
	public void setAdminAge(int adminAge) {
		this.adminAge = adminAge;
	}

	/**
	 * @return the adminCity
	 */
	public String getAdminCity() {
		return adminCity;
	}

	/**
	 * @param adminCity the adminCity to set
	 */
	public void setAdminCity(String adminCity) {
		this.adminCity = adminCity;
	}

	/**
	 * @return the adminState
	 */
	public String getAdminState() {
		return adminState;
	}

	/**
	 * @param adminState the adminState to set
	 */
	public void setAdminState(String adminState) {
		this.adminState = adminState;
	}

	/**
	 * @return the adminPincode
	 */
	public int getAdminPincode() {
		return adminPincode;
	}

	/**
	 * @param adminPincode the adminPincode to set
	 */
	public void setAdminPincode(int adminPincode) {
		this.adminPincode = adminPincode;
	}

	/**
	 * @return the admin
	 */
	public Boolean getAdmin() {
		return admin;
	}

	public CRUDModel() {
		OracleConnection = SetConnection();
	}
	
	
	
	public Boolean getCredentials(String username, String password) throws SQLException {
		String query = "SELECT * FROM admins_2711 WHERE username = ? and password = ?";
		try (PreparedStatement stmt = OracleConnection.prepareStatement(query)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				setId(rs.getInt("admin_id"));
				setAdmin(rs.getString("status").equals("true"));
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Boolean getManagerCredentials(String username, String password) {

		String query = "SELECT * FROM managers_2711 WHERE username = ? and password = ?";
		try (PreparedStatement stmt = OracleConnection.prepareStatement(query)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				setId(rs.getInt("manager_id"));
				//setAdmin(rs.getString("status") == "true" );
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Boolean getAdminDetails(String username, String password) {

		String query = "SELECT * FROM admins_2711 WHERE username = ? and password = ?";
		try (PreparedStatement stmt = OracleConnection.prepareStatement(query)) {
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				setId(rs.getInt("admin_id"));
				setAdmin(rs.getString("status").equals("true"));
				setAdminAge(rs.getInt("age"));
				setAdminName(rs.getString("name"));
				setAdminEmail(rs.getString("email"));
				setAdminCity(rs.getString("city"));
				setAdminState(rs.getString("state"));
				setAdminPassword(rs.getString("password"));
				setAdminUsername(rs.getString("username"));
				setAdminPincode(rs.getInt("pincode"));

				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}// end class