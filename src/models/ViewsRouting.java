package models;

import java.io.IOException;

import app.Project;
import controllers.AdminAvailableRoomController;
import controllers.AdminManageProfileController;
import controllers.AdminPageController;
import controllers.CustomerAvailableRoomController;
import controllers.CustomerManageProfileController;
import controllers.CustomerPageController;
import controllers.CustomerReservationController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ViewsRouting {
	public ViewsRouting() {
	}

	public void handleRouting(String viewName, Pane paneName, String title) {

		Stage admin = new Stage();
		Parent root = null;
		try {
			FXMLLoader loader = new FXMLLoader(Project.class.getResource(viewName));
			root = loader.load();

		} catch (IOException ex) {
			System.out.println(ex);
		}

		Stage current = (Stage) paneName.getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());

		admin.setTitle(title);
		admin.setScene(scene);

		current.hide();
		admin.show();

	}

	public void handleRoutingAdminPage(String viewName, Pane paneName, String username, String password, String title) {
		Stage admin = new Stage();
		Project.stage = admin;
		Parent root = null;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(Project.class.getResource(viewName));
			root = loader.load();
		} catch (IOException ex) {
			System.out.println(ex);
		}

		AdminPageController adminPageCtrl = (AdminPageController) loader.getController();
		adminPageCtrl.initData(username, password);
		Stage current = (Stage) paneName.getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());

		Project.stage.setTitle(title);
		admin.setScene(scene);

		current.hide();
		admin.show();
	}

	public void handleRoutingCustomerPage(String viewName, Pane paneName, String username, String password,
			String title) {
		Stage admin = new Stage();
		Parent root = null;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(Project.class.getResource(viewName));
			root = loader.load();
		} catch (IOException ex) {
			System.out.println("Exception while loading Customer Page View in Customer Login:" + ex);
		}

		CustomerPageController custPageCtrl = (CustomerPageController) loader.getController();
		custPageCtrl.initData(username, password);
		Stage current = (Stage) paneName.getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());

		admin.setTitle(title);
		admin.setScene(scene);

		current.hide();
		admin.show();
	}

	public void handleRoutingAdminManageProfile(String viewName, Pane paneName, String username, String password,
			String title) {

		Stage admin = new Stage();
		Parent root = null;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(Project.class.getResource(viewName));
			root = loader.load();
		} catch (IOException ex) {
			}

		AdminManageProfileController adminManagerProfileCtrl = (AdminManageProfileController) loader.getController();
		adminManagerProfileCtrl.initData(username, password);

		Stage current = (Stage) paneName.getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());

		admin.setScene(scene);
		admin.setTitle(title);
		current.hide();
		admin.show();
	}

	public void handleRoutingAdminAvailableRoom(String viewName, Pane paneName, String username, String password,
			String title) {

		Stage admin = new Stage();
		Parent root = null;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(Project.class.getResource(viewName));
			root = loader.load();
		} catch (IOException ex) {
			System.out.println(ex);
		}

		AdminAvailableRoomController adminAvailableRoomCtrl = (AdminAvailableRoomController) loader.getController();
		adminAvailableRoomCtrl.initData(username, password);

		Stage current = (Stage) paneName.getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());

		admin.setScene(scene);
		admin.setTitle(title);
		current.hide();
		admin.show();
	}

	public void handleRoutingCustomerManageProfile(String viewName, Pane paneName, String username, String password,
			String title) {

		Stage admin = new Stage();
		Parent root = null;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(Project.class.getResource(viewName));
			root = loader.load();
		} catch (IOException ex) {

		}

		CustomerManageProfileController customerManageProfileCtrl = (CustomerManageProfileController) loader
				.getController();
		customerManageProfileCtrl.initData(username, password);

		Stage current = (Stage) paneName.getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());

		admin.setTitle(title);
		admin.setScene(scene);

		current.hide();
		admin.show();
	}

	public void handleRoutingCustomerAvailableRoom(String viewName, Pane paneName, String username, String password,
			String title) {

		Stage admin = new Stage();
		Parent root = null;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(Project.class.getResource(viewName));
			root = loader.load();
		} catch (IOException ex) {
	}

		CustomerAvailableRoomController customerAvailableRoomController = (CustomerAvailableRoomController) loader
				.getController();
		customerAvailableRoomController.initData(username, password);

		Stage current = (Stage) paneName.getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());

		admin.setTitle(title);
		admin.setScene(scene);

		current.hide();
		admin.show();
	}

	public void handleRoutingCustomerReservaion(String viewName, Pane paneName, String username, String password,
			String title) {

		Stage admin = new Stage();
		Parent root = null;
		FXMLLoader loader = null;
		try {
			loader = new FXMLLoader(Project.class.getResource(viewName));
			root = loader.load();
		} catch (IOException ex) {

			System.out.println(ex);
		}

		CustomerReservationController customerResController = (CustomerReservationController) loader.getController();
		customerResController.initData(username, password);

		Stage current = (Stage) paneName.getScene().getWindow();
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("/app/application.css").toExternalForm());

		admin.setTitle(title);
		admin.setScene(scene);
		current.hide();
		admin.show();
	}

}
