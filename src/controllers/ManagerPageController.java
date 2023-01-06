package controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import models.DialogModel;
import models.ViewsRouting;

public class ManagerPageController {
	@FXML
	private Pane manageradmin_pane;
	@FXML
	private Pane managerlogout_pane;
	@FXML
	private Pane managercustomer_pane;
	@FXML
	private AnchorPane manager_anchor_pane;
	@FXML
	private StackPane stackepane_managerpageview;
	ViewsRouting viewr = null;
	DialogModel dialog= null;
	public ManagerPageController() {
		viewr = new ViewsRouting();
		dialog = new DialogModel();

	}

	@FXML
	private void mouse_exit_1(MouseEvent event) {
		manageradmin_pane.setStyle("-fx-background-color: #FFFAFA; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_hover_1(MouseEvent event) {
		manageradmin_pane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_exit_2(MouseEvent event) {
		managerlogout_pane.setStyle("-fx-background-color: #FFFAFA; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_hover_2(MouseEvent event) {
		managerlogout_pane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_exit_3(MouseEvent event) {
		managercustomer_pane.setStyle("-fx-background-color: #FFFAFA; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_hover_3(MouseEvent event) {
		managercustomer_pane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
	}

	@FXML
	private void manageAdminClicked(MouseEvent event) {
		//System.out.println("here1");
		viewr.handleRouting("/views/ManagerManageAdminView.fxml", manager_anchor_pane,"Admin Management");
	}

	@FXML
	private void managerLogoutClicked(MouseEvent event) {
		
		dialog.handleLogoutDialog("Alert", "Do you want to Logout?", stackepane_managerpageview,
				"/views/ManagerLoginView.fxml","Manager Login");

	}

	@FXML
	private void manageCustomerClicked(MouseEvent event) {
		viewr.handleRouting("/views/ManagerManageCustomerView.fxml", manager_anchor_pane,"Customer Management");

	}

}
