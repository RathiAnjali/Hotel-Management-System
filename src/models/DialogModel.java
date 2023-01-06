package models;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

public class DialogModel {
	ViewsRouting viewr = null;

	public DialogModel() {
		viewr = new ViewsRouting();

	}

	public void handleDialog(String heading, String body, StackPane pane, String view, String title) {

		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.setHeading(new Text(heading));
		dialogLayout.setBody(new Text(body));

		JFXButton ok = new JFXButton("Ok");

		JFXDialog dialog = new JFXDialog(pane, dialogLayout, JFXDialog.DialogTransition.CENTER);

		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				viewr.handleRouting(view, pane, title);

			}
		});

		dialogLayout.setActions(ok);
		dialog.show();

	}

	public void handleDialogCustomerReservation(String heading, String body, StackPane pane, String view,
			String loginUserName, String loginPassword) {

		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.setHeading(new Text(heading));
		dialogLayout.setBody(new Text(body));

		JFXButton ok = new JFXButton("Ok");

		JFXDialog dialog = new JFXDialog(pane, dialogLayout, JFXDialog.DialogTransition.CENTER);

		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				viewr.handleRoutingCustomerPage(view, pane, loginUserName, loginPassword,"Customer Home");
			}
		});

		dialogLayout.setActions(ok);
		dialog.show();

	}
	public void handleDialogAdminManageProfile(String heading, String body, StackPane pane, String view,
			String loginUserName, String loginPassword) {

		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.setHeading(new Text(heading));
		dialogLayout.setBody(new Text(body));

		JFXButton ok = new JFXButton("Ok");

		JFXDialog dialog = new JFXDialog(pane, dialogLayout, JFXDialog.DialogTransition.CENTER);

		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				viewr.handleRoutingAdminPage(view, pane, loginUserName, loginPassword,"Admin Home");
			}
		});

		dialogLayout.setActions(ok);
		dialog.show();

	}

	public void handleLogoutDialog(String heading, String body, StackPane pane, String view, String title) {

		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.setHeading(new Text(heading));
		dialogLayout.setBody(new Text(body));

		JFXButton ok = new JFXButton("Ok");
		JFXButton Cancel = new JFXButton("Cancel");

		JFXDialog dialog = new JFXDialog(pane, dialogLayout, JFXDialog.DialogTransition.CENTER);

		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				viewr.handleRouting(view, pane, title);
				}
		});
		Cancel.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialog.close();
			}
		});

		dialogLayout.setActions(ok, Cancel);

		dialog.show();

	}

}
