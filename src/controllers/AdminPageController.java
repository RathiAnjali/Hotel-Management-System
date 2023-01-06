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

public class AdminPageController implements Initializable {
	@FXML
	private Pane admin_page_pane;

	@FXML
	private Pane reservation_pane;

	@FXML
	private Pane manage_profile_pane;
	@FXML
	private Pane admin_page_logout_pane;
	@FXML
	private StackPane stackepane;

	String loginUserName;
	String loginUserPass;
	ViewsRouting viewr = null;
	DialogModel dialog = null;

	public AdminPageController() {
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
		manage_profile_pane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_hover_2(MouseEvent event) {
		manage_profile_pane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_exit_3(MouseEvent event) {
		admin_page_logout_pane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_hover_3(MouseEvent event) {
		admin_page_logout_pane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
	}

	@FXML
	private void reservationClicked(MouseEvent event) {
		viewr.handleRoutingAdminAvailableRoom("/views/AdminAvailableRoomView.fxml", admin_page_pane, loginUserName,
				loginUserPass,"Manage Available rooms");
	}

	@FXML
	private void manageprofileClicked(MouseEvent event) {
		viewr.handleRoutingAdminManageProfile("/views/AdminManageProfile.fxml", admin_page_pane, loginUserName,
				loginUserPass,"Manage Admin Profile");

	}

	@FXML
	private void adminpagelogoutClicked(MouseEvent event) {

		dialog.handleLogoutDialog("Alert", "Do you want to Logout?", stackepane, "/views/AdminView.fxml","Admin Login");

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
