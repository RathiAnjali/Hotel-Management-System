package controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicLong;

import app.Project;
//import dao.DBConnect;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.StackPane;
import models.RoomModel;
import models.ViewsRouting;

public class CustomerAvailableRoomController implements Initializable {

	@FXML
	private StackPane stackpane_custavailablerooms;

	@FXML
	private TableView<RoomModel> tblRooms;
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
	private TableColumn<RoomModel, String> startDate;
	@FXML
	private TableColumn<RoomModel, String> endDate;

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

	public CustomerAvailableRoomController() {
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

		//String query = "SELECT * FROM  rooms_2711";
		String query = "Select Rooms_2711.roomId,Rooms_2711.Roomtype,Rooms_2711.roomnumber,Rooms_2711.numberofpeople,Rooms_2711.RoomPrice,Rooms_2711.roomstatus,Bookings_2711.StartDate,Bookings_2711.enddate From Bookings_2711,rooms_2711 Where Bookings_2711.Roomnumber = Rooms_2711.Roomnumber";
System.out.println(query);
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
		startDate.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("startDate"));
		endDate.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("endDate"));

		// auto adjust width of columns depending on their content
		tblRooms.setItems(roomList);
		tblRooms.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(tblRooms));
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
	public void onCustomerAvaibleRoomBack() {
		viewr.handleRoutingCustomerPage("/views/CustomerPageView.fxml", stackpane_custavailablerooms, loginUserName, loginUserPass,"Customer Home");

	}

	@FXML
	public void onCustomerAvaibleRoomExit() {
		System.exit(0);
	}

}
