package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import models.CustomerModel;
import models.ViewsRouting;

public class CustomerLoginController {
	@FXML
	private AnchorPane customer_view_pane;

	@FXML
	private TextField txtCustomerUsername;
	@FXML
	private PasswordField txtCustomerPassword;
	@FXML
	private Label lblError;

	private CustomerModel model;
	ViewsRouting viewr = null;

	public CustomerLoginController() {
		model = new CustomerModel();
		viewr = new ViewsRouting();

	}

	@FXML
	public void onCustomerLogin() {

		lblError.setText("");
		String username = this.txtCustomerUsername.getText();
		String password = this.txtCustomerPassword.getText();
/*System.out.println(username);
System.out.println(password);*/
		// Validations
		if (username == null || username.trim().equals("")) {
			lblError.setText("Username Cannot be empty or spaces");
			return;
		}
		if (password == null || password.trim().equals("")) {
			lblError.setText("Password Cannot be empty or spaces");
			return;
		}
		if (username == null || username.trim().equals("") && (password == null || password.trim().equals(""))) {
			lblError.setText("User name / Password Cannot be empty or spaces");
			return;
		}

		// authentication check
		checkCredentials(username, password);

	}

	@FXML
	public void onCustomerRegister() {
	viewr.handleRouting("/views/CustomerRegisterView.fxml", customer_view_pane,"Customer Registration");


	}

	public void checkCredentials(String username, String password) {
		Boolean isValid = model.getCredentials(username, password);
		System.out.println(model.getCustomerStatus());
		System.out.println(isValid);
		if (!isValid) {
			lblError.setText("Customer does not exist!");
			return;
		}
		try {
			//if (model.getCustomerStatus() && isValid) {
			if (isValid) {
				viewr.handleRoutingCustomerPage("/views/CustomerPageView.fxml", customer_view_pane, username, password, "Customer Home");
			}

		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}

	}

	@FXML
	public void onCustomerBack() {
		viewr.handleRouting("/views/HomescreenView.fxml", customer_view_pane, "Home");

	}

}
