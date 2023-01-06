package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import models.DialogModel;
import models.ViewsRouting;

public class CustomerPageController implements Initializable {
	@FXML
	private Pane customer_page_pane;

	@FXML
	private Pane reservation_pane;

	@FXML
	private Pane customer_manage_profile_pane;
	@FXML
	private Pane availableroom_pane;
	
	@FXML
	private Pane customer_page_logout_pane;
	@FXML
	private StackPane stackepane_customerpageview_pane;

	String loginUserName;
	String loginUserPass;
	ViewsRouting viewr = null;
	DialogModel dialog = null;

	public CustomerPageController() {
		viewr = new ViewsRouting();
		dialog = new DialogModel();

	}

	public void initData(String username, String password) {
		this.loginUserName = username;
		this.loginUserPass = password;
	}

	@FXML
	private void mouse_exit_1(MouseEvent event) {
		reservation_pane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_hover_1(MouseEvent event) {
		reservation_pane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_exit_2(MouseEvent event) {
		customer_manage_profile_pane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_hover_2(MouseEvent event) {
		customer_manage_profile_pane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_exit_3(MouseEvent event) {
		customer_page_logout_pane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_hover_3(MouseEvent event) {
		customer_page_logout_pane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_exit_4(MouseEvent event) {
		availableroom_pane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_hover_4(MouseEvent event) {
		availableroom_pane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
	}

	@FXML
	private void reservationClicked(MouseEvent event) {
		viewr.handleRoutingCustomerReservaion("/views/CustomerReservationView.fxml", stackepane_customerpageview_pane,loginUserName, loginUserPass, "Customer Reservation");
	}
	
	@FXML
	private void availableRoomClicked(MouseEvent event) {
		viewr.handleRoutingCustomerAvailableRoom("/views/CustomerAvailableRoomView.fxml", stackepane_customerpageview_pane, loginUserName, loginUserPass, "Customer Available Rooms");
	}

	@FXML
	private void customermanageprofileClicked(MouseEvent event) {
		viewr.handleRoutingCustomerManageProfile("/views/CustomerManageProfile.fxml", stackepane_customerpageview_pane,
				loginUserName, loginUserPass,"Customer Manage profile");
	}

	@FXML
	private void customerpagelogoutClicked(MouseEvent event) {

		dialog.handleLogoutDialog("Alert", "Do you want to Logout?", stackepane_customerpageview_pane,
				"/views/CustomerView.fxml","Customer Login");

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}
}
