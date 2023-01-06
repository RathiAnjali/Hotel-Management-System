package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import app.Project;
//import dao.DBConnect;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import models.DialogModel;
import models.ViewsRouting;

public class CustomerRegisterController {
	@FXML
	private StackPane customer_register_stackpane;

	@FXML
	private AnchorPane customer_register_pane;
	@FXML
	private TextField txtCustomerRegisterName;
	@FXML
	private TextField txtCustomerRegisterUsername;
	@FXML
	private TextField txtCustomerRegisterEmail;
	@FXML
	private PasswordField txtCustomerRegisterPassword;
	@FXML
	private TextField txtCustomerRegisterAge;
	@FXML
	private TextField txtCustomerRegisterCity;
	@FXML
	private TextField txtCustomerRegisterState;
	@FXML
	private TextField txtCustomerRegisterPincode;
	@FXML
	private Label customerregisterlblError;

	// Declare DB objects
	private Connection OracleConnection;
	Statement stmt = null;
	ViewsRouting viewr = null;
	DialogModel dialog = null;

	public CustomerRegisterController() {
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

	@FXML
	public void onCustomerRegisterSubmit() {
		String customerRegisterName = this.txtCustomerRegisterName.getText();
		String customerRegisterUsername = this.txtCustomerRegisterUsername.getText();
		String customerRegisterPassword = this.txtCustomerRegisterPassword.getText();
		String customerRegisterEmail = this.txtCustomerRegisterEmail.getText();
		int customerRegisterAge = this.txtCustomerRegisterAge.getText() == "" ? 0
				: Integer.parseInt(this.txtCustomerRegisterAge.getText());

		String customerRegisterCity = this.txtCustomerRegisterCity.getText();
		String customerRegisterState = this.txtCustomerRegisterState.getText();
		int customerRegisterPincode = this.txtCustomerRegisterPincode.getText() == "" ? 0
				: Integer.parseInt(this.txtCustomerRegisterPincode.getText());

		try {
			customerregisterlblError.setText("");
			String username = this.txtCustomerRegisterUsername.getText();
			String password = this.txtCustomerRegisterPassword.getText();
			String name = this.txtCustomerRegisterName.getText();

			// Validations
			if (name == null || name.trim().equals("")) {
				customerregisterlblError.setText("Name Cannot be empty or spaces");
				return;
			}
			if (username == null || username.trim().equals("")) {
				customerregisterlblError.setText("Username Cannot be empty or spaces");
				return;
			}
			if (password == null || password.trim().equals("")) {
				customerregisterlblError.setText("Password Cannot be empty or spaces");
				return;
			}

			if (username == null || username.trim().equals("") && name == null
					|| name.trim().equals("") && (password == null || password.trim().equals(""))) {
				customerregisterlblError.setText("Name / Username / Password Cannot be empty or spaces");
				return;
			}

			// Open connection.
			stmt = OracleConnection.createStatement();
			// resultset query string to sort the pep field in descending order.

			String sql = "INSERT INTO customers_2711 (username, password, name, age, email, city, state, pincode, status) VALUES ('"
					+ customerRegisterUsername + "','" + customerRegisterPassword + "','" + customerRegisterName + "',"
					+ customerRegisterAge + ",'" + customerRegisterEmail + "','" + customerRegisterCity + "','"
					+ customerRegisterState + "'," + customerRegisterPincode + ",'true')";
			int c = stmt.executeUpdate(sql);
			if (c > 0) {
				dialog.handleDialog("Success", "Customer registered Successfully!", customer_register_stackpane,
						"/views/CustomerView.fxml", "Customer Login");
			}
			// close opened connection.
			OracleConnection.close();
		} catch (Exception e) {
			dialog.handleDialog("Failed", "UserName or EmailID already exists", customer_register_stackpane,
					"/views/AdminView.fxml","Admin Login");		}

	}

	@FXML
	public void onCustomerRegisterReset() {
		this.txtCustomerRegisterName.clear();

		this.txtCustomerRegisterUsername.clear();

		this.txtCustomerRegisterEmail.clear();

		this.txtCustomerRegisterPassword.clear();

		this.txtCustomerRegisterAge.clear();

		this.txtCustomerRegisterCity.clear();

		this.txtCustomerRegisterState.clear();

		this.txtCustomerRegisterPincode.clear();

	}

	@FXML
	public void onCustomerRegisterBack() {
		viewr.handleRouting("/views/CustomerView.fxml", customer_register_pane, "Customer Login");

	}
}
