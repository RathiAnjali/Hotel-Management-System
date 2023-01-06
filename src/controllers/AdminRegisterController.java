package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import app.Project;
//import dao.DBConnect;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import models.DialogModel;
import models.ViewsRouting;

public class AdminRegisterController {
	@FXML
	private StackPane adminregistrationstackpane;
	@FXML
	private AnchorPane adminregistration;
	@FXML
	private TextField txtAdminRegisterName;
	@FXML
	private TextField txtAdminRegisterUsername;
	@FXML
	private TextField txtAdminRegisterEmail;
	@FXML
	private PasswordField txtAdminRegisterPassword;
	@FXML
	private TextField txtAdminRegisterAge;
	@FXML
	private TextField txtAdminRegisterCity;
	@FXML
	private TextField txtAdminRegisterState;
	@FXML
	private TextField txtAdminRegisterPincode;
	@FXML
	Label adminregisterlblError;

	// Declare DB objects
	private Connection OracleConnection;
	Statement stmt = null;
	ViewsRouting viewr = null;
	DialogModel dialog = null;

	public AdminRegisterController() {
		OracleConnection = SetConnection();
		viewr = new ViewsRouting();
		dialog = new DialogModel();
	}
	
	public Connection SetConnection(){
		 try {
			return DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-QRVS9B0:1521:xe","system","SHankar$1996");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}

	public void viewAccounts() {

	}

	@FXML
	public void onAdminRegisterSubmit() {
		String adminRegisterName = this.txtAdminRegisterName.getText();
		String adminRegisterUsername = this.txtAdminRegisterUsername.getText();
		String adminRegisterPassword = this.txtAdminRegisterPassword.getText();
		String adminRegisterEmail = this.txtAdminRegisterEmail.getText();
		int adminRegisterAge = this.txtAdminRegisterAge.getText() == "" ? 0
				: Integer.parseInt(this.txtAdminRegisterAge.getText());

		String adminRegisterCity = this.txtAdminRegisterCity.getText();
		String adminRegisterState = this.txtAdminRegisterState.getText();
		int adminRegisterPincode = this.txtAdminRegisterPincode.getText() == "" ? 0
				: Integer.parseInt(this.txtAdminRegisterPincode.getText());

		try {
			// Open connection.
			stmt = OracleConnection.createStatement();
			// resultset query string to sort the pep field in descending order.
			adminregisterlblError.setText("");
			String username = this.txtAdminRegisterUsername.getText();
			String password = this.txtAdminRegisterPassword.getText();
			String name = this.txtAdminRegisterName.getText();

			// Validations
			if (name == null || name.trim().equals("")) {
				adminregisterlblError.setText("Name Cannot be empty or spaces");
				return;
			}
			if (username == null || username.trim().equals("")) {
				adminregisterlblError.setText("Username Cannot be empty or spaces");
				return;
			}
			if (password == null || password.trim().equals("")) {
				adminregisterlblError.setText("Password Cannot be empty or spaces");
				return;
			}

			if (username == null || username.trim().equals("") && name == null
					|| name.trim().equals("") && (password == null || password.trim().equals(""))) {
				adminregisterlblError.setText("Name / Username / Password Cannot be empty or spaces");
				return;
			}

			String sql = "INSERT INTO admins_2711 (username, password, name, age, email, city, state, pincode, status) VALUES ('"
					+ adminRegisterUsername + "','" + adminRegisterPassword + "','" + adminRegisterName + "',"
					+ adminRegisterAge + ",'" + adminRegisterEmail + "','" + adminRegisterCity + "','"
					+ adminRegisterState + "'," + adminRegisterPincode + ",'false' )";
			System.out.println(sql);
	
			int c = stmt.executeUpdate(sql);
			if (c > 0)
				dialog.handleDialog("Success", "Admin registered Successfully!", adminregistrationstackpane,
						"/views/AdminView.fxml","Admin Login");

			// close opened connection.
			OracleConnection.close();
		} catch (Exception e) {
			System.out.println("admin reg fail");
			dialog.handleDialog("Failed", "UserName or EmailID already exists", adminregistrationstackpane,
					"/views/AdminView.fxml","Admin Login");
		}

	}

	@FXML
	public void onAdminRegisterReset() {
		this.txtAdminRegisterName.clear();

		this.txtAdminRegisterUsername.clear();

		this.txtAdminRegisterEmail.clear();

		this.txtAdminRegisterPassword.clear();

		this.txtAdminRegisterAge.clear();

		this.txtAdminRegisterCity.clear();

		this.txtAdminRegisterState.clear();

		this.txtAdminRegisterPincode.clear();

	}

	@FXML
	public void onAdminRegisterBack() {
		viewr.handleRouting("/views/AdminView.fxml", adminregistration,"Admin Login");

	}

}
