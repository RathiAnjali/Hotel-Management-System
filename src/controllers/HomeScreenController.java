package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import models.ViewsRouting;

/**
 * FXML Controller class
 *
 */
public class HomeScreenController implements Initializable {

	@FXML
	private Pane home_admin_pane;

	@FXML
	private Pane home_customer_pane;

	@FXML
	private Pane home_manager_pane;
	@FXML
	private Pane home_exit_pane;

	@FXML
	private AnchorPane home_pane;

	@FXML
	private StackPane stackepane_home;

	ViewsRouting viewr = null;

	public HomeScreenController() {
		viewr = new ViewsRouting();

	}

	/**
	 * Initializes the controller class.
	 */
	@FXML
	private void mouse_exit_1(MouseEvent event) {
		home_admin_pane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_hover_1(MouseEvent event) {
		home_admin_pane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_exit_2(MouseEvent event) {
		home_customer_pane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_hover_2(MouseEvent event) {
		home_customer_pane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_exit_3(MouseEvent event) {
		home_exit_pane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_hover_3(MouseEvent event) {
		home_exit_pane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_exit_4(MouseEvent event) {
		home_manager_pane.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");
	}

	@FXML
	private void mouse_hover_4(MouseEvent event) {
		home_manager_pane.setStyle("-fx-background-color: #377ce8; -fx-background-radius: 6px;");
	}

	@FXML
	private void adminClicked(MouseEvent event) {
		viewr.handleRouting("/views/AdminView.fxml", home_pane,"Admin Login");
		
	}

	@FXML
	private void customerClicked(MouseEvent event) {
		viewr.handleRouting("/views/CustomerView.fxml", home_pane,"Customer Login");

	}

	@FXML
	private void exitClicked(MouseEvent event) {
		System.exit(0);
	}

	@FXML
	private void managerClicked(MouseEvent event) {
	 viewr.handleRouting("/views/ManagerLoginView.fxml", home_pane,"Manager Login");
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
