package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

//import app.Project;
//import dao.DBConnect;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import models.AdminManagerModel;
import models.ViewsRouting;

public class ManagerManageAdminController implements Initializable {

	@FXML
	private AnchorPane manager_manage_admin_view_pane;
	@FXML
	private TableView<AdminManagerModel> tblAdmins;

	@FXML
	private TableColumn<AdminManagerModel, String> aid;
	@FXML
	private TableColumn<AdminManagerModel, String> aname;
	@FXML
	private TableColumn<AdminManagerModel, String> aemail;
	@FXML
	private TableColumn<AdminManagerModel, String> astatus;
	@FXML
	private TableColumn<AdminManagerModel, String> actionButton;
	//private TableColumn<AdminManagerModel, String> actionButton1;


	private ObservableList<AdminManagerModel> mgrAdmins;

	private Connection OracleConnection;
	Statement stmt = null;
	ViewsRouting viewr = null;
	AdminManagerModel mgrModel = null;

	public ManagerManageAdminController() {
		OracleConnection = SetConnection();
		viewr = new ViewsRouting();
		mgrModel = new AdminManagerModel();
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
	public void onManageAdminBack() {
		viewr.handleRouting("/views/ManagerPageView.fxml", manager_manage_admin_view_pane, "Manager Home");

	}

	@FXML
	public void onRefreshAdminList() {
		loadData();

	}

	@FXML
	public void onManageAdminExit() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText("Hello dear!");
		alert.setContentText("Do you want to exit?");
		alert.showAndWait();
		System.exit(0);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		loadData();
	}

	public void loadData() {
		mgrAdmins = mgrModel.getAdmins();
		editableCols();
		aid.setCellValueFactory(new PropertyValueFactory<AdminManagerModel, String>("aid"));
		aname.setCellValueFactory(new PropertyValueFactory<AdminManagerModel, String>("aname"));
		aemail.setCellValueFactory(new PropertyValueFactory<AdminManagerModel, String>("aemail"));
		astatus.setCellValueFactory(new PropertyValueFactory<AdminManagerModel, String>("astatus"));

		Callback<TableColumn<AdminManagerModel, String>, TableCell<AdminManagerModel, String>> cellFactory = new Callback<TableColumn<AdminManagerModel, String>, TableCell<AdminManagerModel, String>>() {
			@Override
			public TableCell<AdminManagerModel, String> call(final TableColumn<AdminManagerModel, String> param) {
				final TableCell<AdminManagerModel, String> cell = new TableCell<AdminManagerModel, String>() {

					@Override
					public void updateItem(String item, boolean empty) {

						super.updateItem(item, empty);
						if (empty) {
							setGraphic(null);
							setText(null);
						} else {
							final Button updateButton = new Button("Make Admin");
							updateButton.setOnAction(event -> {
								AdminManagerModel ad = getTableView().getItems().get(getIndex());
								mgrModel.updateTable(ad.getAid());
					
							});
							setGraphic(updateButton);
							setText(null);
						}
					}
				};
				return cell;
			}
		};
System.out.println(cellFactory);
		actionButton.setCellFactory(cellFactory);
		//actionButton1.setCellFactory(cellFactory);

		// auto adjust width of columns depending on their content
		tblAdmins.setItems(mgrAdmins);
		tblAdmins.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(tblAdmins));
	}

	public void editableCols() {
		astatus.setCellFactory(TextFieldTableCell.forTableColumn());
		astatus.setOnEditCommit(e -> {
			e.getTableView().getItems().get(e.getTablePosition().getRow()).setAstatus(e.getNewValue());
		});
		tblAdmins.setEditable(true);
	}

	public void customResize(TableView<?> view) {
		AtomicLong width = new AtomicLong();
		view.getColumns().forEach(col -> {
			width.addAndGet((long) col.getWidth());
		});
		double tableWidth = view.getWidth();

		if (tableWidth > width.get()) {
			view.getColumns().forEach(col -> {
				col.setPrefWidth(col.getWidth() + ((tableWidth - width.get()) / view.getColumns().size()));
			});
		}
	}

}
