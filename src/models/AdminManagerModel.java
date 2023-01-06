package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import app.Project;
//import application.main;
//import dao.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;

public class AdminManagerModel extends Project {
	private int aid;
	private String aname;
	private String aemail;
	private String astatus;
	private Button actionButton;
	
	private Connection OracleConnection;

	// Declare DB objects
	Statement stmt = null;

	public AdminManagerModel() {
		OracleConnection = SetConnection();
	}

	/**
	 * @return the aid
	 */
	public int getAid() {
		return aid;
	}

	/**
	 * @param aid the aid to set
	 */
	public void setAid(int aid) {
		this.aid = aid;
	}

	/**
	 * @return the aname
	 */
	public String getAname() {
		return aname;
	}

	/**
	 * @param aname the aname to set
	 */
	public void setAname(String aname) {
		this.aname = aname;
	}

	/**
	 * @return the aemail
	 */
	public String getAemail() {
		return aemail;
	}

	/**
	 * @param aemail the aemail to set
	 */
	public void setAemail(String aemail) {
		this.aemail = aemail;
	}

	/**
	 * @return the astatus
	 */
	public String getAstatus() {
		return astatus;
	}

	/**
	 * @param astatus the astatus to set
	 */
	public void setAstatus(String astatus) {
		this.astatus = astatus;
	}

	/**
	 * @return the actionButton
	 */
	public Button getActionButton() {
		return actionButton;
	}

	/**
	 * @param actionButton the actionButton to set
	 */
	public void setActionButton(Button actionButton) {
		this.actionButton = actionButton;
	}

	public ObservableList<AdminManagerModel> getAdmins() {
		ObservableList<AdminManagerModel> admins = FXCollections.observableArrayList();
		String query = "SELECT admin_id, name, email, status FROM admins_2711";
		try (PreparedStatement statement = OracleConnection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			System.out.println(statement + "statement");
			while (resultSet.next()) {
				AdminManagerModel admin = new AdminManagerModel();
				// grab record data by table field name into ClientModel account object
				admin.setAid(resultSet.getInt("admin_id"));
				admin.setAname(resultSet.getString("name"));
				admin.setAemail(resultSet.getString("email"));
				admin.setAstatus(resultSet.getString("status"));
//				admin.setActionButton(new Button("Make Admin"));
				admins.add(admin); // add account data to arraylist
				System.out.println(admin +"2");
			}
		} catch (SQLException e) {
			System.out.println("Error fetching Admin Info: " + e);
		}
		return admins; // return arraylist
	}
	/*
	public void updateTable(int id) {
		//String sta;
		//String query;
		//String sta = "Select Status From admins_2711 Where id=" + id + "";
		//System.out.println(sta);
		//System.out.println(id);
		//try (PreparedStatement stmt = OracleConnection.prepareStatement(sta)) {
			int count = stmt.executeUpdate();
			if (count > 0) {
				//System.out.println("Updated successfully");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(stmt);
		
		String query;
		if(sta == "true") {
			 query = "update admins_2711 set status = 'false' where id=" + id + "";
		}
		else {
		 //query = "update admins_2711 set status = 'true' where id=" + id + "";
		 query = "delete from admins_2711 where id=" + id + "";

			try (PreparedStatement stmt = OracleConnection.prepareStatement(query)) {
			int count = stmt.executeUpdate();
			if (count > 0) {
				System.out.println("Updated successfully");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}*/
		public void updateTable(int id) {
			String query = "update admins_2711 set status = 'true' where admin_id=" + id + "";
				try (PreparedStatement stmt = OracleConnection.prepareStatement(query)) {
				int count = stmt.executeUpdate();
				if (count > 0) {
					System.out.println("Updated successfully");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}
	