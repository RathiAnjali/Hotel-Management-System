package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import models.CRUDModel;
import models.DialogModel;
import models.ViewsRouting;

public class AdminManageProfileController implements Initializable {
	@FXML
	private AnchorPane adminmanageprofile;
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
	private StackPane stackepanemanageprofile;
	@FXML
	private Label adminmanagelblError;

	// Declare DB objects
	private Connection OracleConnection;
	Statement stmt = null;
	ViewsRouting viewr = null;
	String loginUserName = null;
	String loginUserPass = null;
	CRUDModel model = null;
	DialogModel dialog = null;

	public AdminManageProfileController() {
		OracleConnection = SetConnection();
		viewr = new ViewsRouting();
		model = new CRUDModel();
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

	public void initData(String username, String password) {
		this.loginUserName = username;
		this.loginUserPass = password;
		model.getAdminDetails(username, password);
		this.txtAdminRegisterUsername.setText(username);
		this.txtAdminRegisterPassword.setText(password);
		this.txtAdminRegisterAge.setText(model.getAdminAge() + "");
		this.txtAdminRegisterCity.setText(model.getAdminCity());
		this.txtAdminRegisterState.setText(model.getAdminState());
		this.txtAdminRegisterName.setText(model.getAdminName());
		this.txtAdminRegisterEmail.setText(model.getAdminEmail());
		this.txtAdminRegisterPincode.setText(model.getAdminPincode() + "");

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		}

	@FXML
	private void onAdminProfileUpdate() {
		try {
			// Execute a query

			adminmanagelblError.setText("");
			String username = this.txtAdminRegisterUsername.getText();
			String password = this.txtAdminRegisterPassword.getText();
			String name = this.txtAdminRegisterName.getText();

			// Validations
			if (name == null || name.trim().equals("")) {
				adminmanagelblError.setText("Name Cannot be empty or spaces");
				return;
			}
			if (username == null || username.trim().equals("")) {
				adminmanagelblError.setText("Username Cannot be empty or spaces");
				return;
			}
			if (password == null || password.trim().equals("")) {
				adminmanagelblError.setText("Password Cannot be empty or spaces");
				return;
			}

			if (username == null || username.trim().equals("") && name == null
					|| name.trim().equals("") && (password == null || password.trim().equals(""))) {
				adminmanagelblError.setText("Name / Username / Password Cannot be empty or spaces");
				return;
			}

			stmt = OracleConnection.createStatement();
			String sql = null;

			int pinCode = txtAdminRegisterPincode.getText().equals("") ? 0
					: Integer.parseInt(txtAdminRegisterPincode.getText());
			int age = txtAdminRegisterAge.getText().equals("") ? 0 : Integer.parseInt(txtAdminRegisterAge.getText());

			sql = "UPDATE admins_2711 set password='" + txtAdminRegisterPassword.getText() + "', username='"
					+ txtAdminRegisterUsername.getText() + "',name='" + txtAdminRegisterName.getText()
					+ "',pincode=" + pinCode + ",state='" + txtAdminRegisterState.getText() + "',city='"
					+ txtAdminRegisterCity.getText() + "',email='" + txtAdminRegisterEmail.getText()
					+ "',age=" + age + " where username='" + this.loginUserName + "' ";

			int count = stmt.executeUpdate(sql);
			if (count > 0) {

					dialog.handleDialogAdminManageProfile("Success", "Admin Profile Updated Successfully!",
						stackepanemanageprofile, "/views/AdminPageView.fxml", loginUserName, loginUserPass);

			}
			OracleConnection.close();

		} catch (Exception se) {
			dialog.handleDialog("Failed", "UserName or EmailID already exists", stackepanemanageprofile,
					"/views/AdminView.fxml","Admin Login");		}

	}

	@FXML
	private void onAdminProfileReset() {
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
	private void onAdminProfileBack() {
		viewr.handleRoutingAdminPage("/views/AdminPageView.fxml", adminmanageprofile, loginUserName, loginUserPass,"Admin Home");

	}
}
