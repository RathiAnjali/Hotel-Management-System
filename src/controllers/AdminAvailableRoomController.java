package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

//import app.Project;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import models.RoomModel;
import models.ViewsRouting;

public class AdminAvailableRoomController implements Initializable {

	@FXML
	private StackPane stackpane_availablerooms;

	@FXML
	private TableView<RoomModel> tblViewRooms;
	@FXML
	private TableColumn<RoomModel, String> roomId;
	@FXML
	private TableColumn<RoomModel, String> roomType;
	@FXML
	private TableColumn<RoomModel, String> roomNumber;
	@FXML
	private TableColumn<RoomModel, String> numberOfPeople;
	@FXML
	private TableColumn<RoomModel, String> roomPrice;
	@FXML
	private TableColumn<RoomModel, String> roomStatus;

	@FXML
	private TextField txtSearch;

	@FXML
	private Button btnSearchRoomNo;

	@FXML
	private Button btnMakeAvail;
	
	@FXML
	private Button btnMakeAvail1;

	@FXML
	private Label lblSearch;

	private ObservableList<RoomModel> roomList;

	private Connection OracleConnection;
	Statement stmt = null;
	ViewsRouting viewr = null;
	RoomModel roomModel = null;
	String loginUserName = null;
	String loginUserPass = null;

	public AdminAvailableRoomController() {
		OracleConnection = SetConnection();
		viewr = new ViewsRouting();
		roomModel = new RoomModel();
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
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		String query = "SELECT * FROM  rooms_2711 order by roomnumber asc";
		roomList = roomModel.getRooms(query);
		loadData(query);
	}

	public void loadData(String query) {

		roomId.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("roomId"));
		roomType.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("roomType"));
		roomNumber.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("roomNumber"));
		numberOfPeople.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("numberOfPeople"));
		roomPrice.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("roomPrice"));
		roomStatus.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("roomStatus"));

		// auto adjust width of columns depending on their content
		tblViewRooms.setItems(roomList);
		tblViewRooms.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(tblViewRooms));
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

	@FXML
	void makeAvailableRoom(ActionEvent event) {
		String searchText = txtSearch.getText().toString();
		if (searchText == "" || searchText == null) {
			handleDialog();
		} else {
			int roomNo = Integer.parseInt(searchText);
			int result = roomModel.updateRoomByRoomNumber(roomNo);
			if (result > 0) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Room Info Update");
				alert.setHeaderText("Modification");
				alert.setContentText("Record updated successfully!");
				alert.showAndWait();
				String query = "SELECT * FROM  rooms_2711";
				roomList = roomModel.getRooms(query);
				loadData(query);
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Room Info Update");
				alert.setHeaderText("Error");
				alert.setContentText("Database Error!");
				alert.showAndWait();
			}
		}
	}
	@FXML
	void DeleteRoom(ActionEvent event) {
		String searchText = txtSearch.getText().toString();
		if (searchText == "" || searchText == null) {
			handleDialog();
		} else {
			int roomNo = Integer.parseInt(searchText);
			int result = roomModel.deleteRoomByRoomNumber(roomNo);
			if (result > 0) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Room Info Delete");
				alert.setHeaderText("Modification");
				alert.setContentText("Record Deleted successfully!");
				alert.showAndWait();
				String query = "SELECT * FROM  rooms_2711";
				roomList = roomModel.getRooms(query);
				loadData(query);
			} else {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Room Info Dalete");
				alert.setHeaderText("Error");
				alert.setContentText("Database Error!");
				alert.showAndWait();
			}
		}
	}/*
	@FXML
	
	}
	@FXML
	void DeleteRoom1(ActionEvent event) {
		String searchText = txtSearch.getText().toString();

		if (searchText == "" || searchText == null) {
			handleDialog();
		} else {
			String query = "delete FROM rooms_2711 where roomnumber =" + Integer.parseInt(searchText) + "";
			roomList = roomModel.getRooms(query);
			loadData(query);
		}
	}*/
	
	
	@FXML
	public void onAvailableRooms() {
		txtSearch.clear();
		String query = "SELECT * FROM  rooms_2711";
		roomList = roomModel.getRooms(query);
		loadData(query);
	}

	@FXML
	void searchByRoomNo(ActionEvent event) {
		String searchText = txtSearch.getText().toString();

		if (searchText == "" || searchText == null) {
			handleDialog();
		} else {
			String query = "SELECT * FROM  rooms_2711 where roomnumber =" + Integer.parseInt(searchText) + "";
			roomList = roomModel.getRooms(query);
			loadData(query);
		}
	}

	public void handleDialog() {
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.setHeading(new Text("Alert"));
		dialogLayout.setBody(new Text("Please enter Valid Room Number"));

		JFXButton ok = new JFXButton("Ok");

		JFXDialog dialog = new JFXDialog(stackpane_availablerooms, dialogLayout, JFXDialog.DialogTransition.CENTER);

		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialog.close();
			}
		});

		dialogLayout.setActions(ok);
		dialog.show();
	}

	@FXML
	public void onBackBtn() {
		viewr.handleRoutingAdminPage("/views/AdminPageView.fxml", stackpane_availablerooms, loginUserName, loginUserPass,"Admin Home");
	}

	@FXML
	public void onExitBtn() {
		System.exit(0);
	}

}
