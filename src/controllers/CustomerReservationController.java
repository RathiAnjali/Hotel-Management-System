package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;

import app.Project;
//import dao.DBConnect;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import models.CustomerModel;
import models.DialogModel;
import models.RoomModel;
import models.ViewsRouting;

public class CustomerReservationController {

	@FXML
	private StackPane stackpane_customerreservation;
	@FXML
	private TextField txtSearchCust;

	@FXML
	private Pane reservation_pane;

	@FXML
	private Pane customer_pane;

	@FXML
	private TextField txtRoomType;

	@FXML
	private TextField txtRoomNo;

	@FXML
	private TextField txtNoOfPpl;

	@FXML
	private TextField txtRoomPrice;

	@FXML
	private DatePicker dateStartField;

	@FXML
	private DatePicker dateEndField;

	@FXML
	private TextField txtServiceFees;

	@FXML
	private TextField txtTotal;

	@FXML
	private Pane custinfo_pane;

	@FXML
	private TextField txtName;

	@FXML
	private TextField txtAge;

	@FXML
	private TextField txtCity;

	@FXML
	private TextField txtState;

	@FXML
	private TextField txtPincode;

	@FXML
	private Button bookBtn;

	@FXML
	private Button resetBtn;

	private CustomerModel custModel;
	private RoomModel roomModel;
	private ViewsRouting viewr;
	String loginUserName = null;
	String loginUserPass = null;
	private Connection OracleConnection;
	Statement stmt = null;
	DialogModel dialog = null;

	public CustomerReservationController() {
		// TODO Auto-generated constructor stub
		custModel = new CustomerModel();
		roomModel = new RoomModel();
		OracleConnection = SetConnection();
		viewr = new ViewsRouting();
		dialog = new DialogModel();
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

	@FXML
	public void onSearchName() {
		String searchText = txtSearchCust.getText().toString();
		System.out.println(searchText);

		if (searchText == "" || searchText == null) {
			handleDialog("Please enter valid name");
		} else {
			custModel.getCustomerDetailsUsingName(searchText);
			if (custModel.getCustomerName() != "" && custModel.getCustomerName() != null) {
				custinfo_pane.setVisible(true);
				reservation_pane.setVisible(true);
				bookBtn.setVisible(true);
				resetBtn.setVisible(true);
				txtName.setText(custModel.getCustomerName());
				txtAge.setText(custModel.getCustomerAge() + "");
				txtCity.setText(custModel.getCustomerCity());
				txtState.setText(custModel.getCustomerState());
				txtPincode.setText(custModel.getCustomerPincode() + "");

			} else {
				handleDialog("Please enter valid name/Check if the name is correctly entered.");
				custinfo_pane.setVisible(false);
				reservation_pane.setVisible(false);
			}
		}
	}

	@FXML
	public void onBookingClicked() throws SQLException {

		String customerName = this.txtName.getText();
		int customerAge = this.txtAge.getText() == "" ? 0 : Integer.parseInt(this.txtAge.getText());

		String customerCity = this.txtCity.getText();
		String customerState = this.txtState.getText();
		int customerPincode = this.txtPincode.getText() == "" ? 0 : Integer.parseInt(this.txtPincode.getText());
		int customerRoomNumber = this.txtRoomNo.getText() == "" ? 0 : Integer.parseInt(this.txtRoomNo.getText());

		String customerRoomtype = this.txtRoomType.getText();
		int customerNoumberofPeople = this.txtNoOfPpl.getText() == "" ? 0 : Integer.parseInt(this.txtNoOfPpl.getText());

		int customerRoomPrice = this.txtRoomPrice.getText() == "" ? 0 : Integer.parseInt(this.txtRoomPrice.getText());

		int customerTotal = this.txtTotal.getText() == "" ? 0 : Integer.parseInt(this.txtTotal.getText());
		int customerServicefee = this.txtServiceFees.getText() == "" ? 0 : Integer.parseInt(this.txtServiceFees.getText());
		LocalDate startLocalDate = dateStartField.getValue();
		LocalDate endLocalDate = dateEndField.getValue();
		Date startDate = Date.from(startLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDate = Date.from(endLocalDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
		String strStartDate = formatter.format(startDate);
		String strEndDate = formatter.format(endDate);

		try {
			stmt = OracleConnection.createStatement();

			/*String sql = "INSERT INTO bookings_2711 (custname, custage, custcity, custstate, custpincode, roomtype, numberofpeople, roomprice, servicefee,total, startdate, enddate, roomnumber, username) VALUES ('"
					+ customerName + "'," + customerAge + ",'" + customerCity + "','" + customerState + "',"
					+ customerPincode + ",'" + customerRoomtype + "'," + customerNoumberofPeople + ","
					+ customerRoomPrice + "," + customerServicefee + "," + customerTotal + ",'" + strStartDate + "','"
					+ strEndDate + "'," + customerRoomNumber + ",'" + loginUserName + "')";
			*/
			String sql = "INSERT INTO bookings_2711 (servicefee,total, startdate, enddate, roomnumber, username) VALUES ("
				    + customerServicefee + "," + customerTotal + ",'" + strStartDate + "','"
					+ strEndDate + "'," + customerRoomNumber + ",'" + loginUserName + "')";

			String updateSql = "update rooms_2711 set roomstatus = 'busy' where roomnumber ="
					+ customerRoomNumber + "";
			int c = stmt.executeUpdate(sql);
			int c2 = stmt.executeUpdate(updateSql);

			if (c > 0 && c2 > 0) {
				dialog.handleDialogCustomerReservation("Success", "Booking done Successfully!",
						stackpane_customerreservation, "/views/CustomerPageView.fxml", loginUserName, loginUserPass);
			}
			OracleConnection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@FXML
	public void findRoom() {
		String searchText = txtRoomNo.getText().toString();
		if (searchText == "" || searchText == null) {
			handleDialog("Please enter the valid Room No");
		} else {
			roomModel.findRoomByRoomNumber(Integer.parseInt(searchText));
			if (roomModel.getRoomNumber() != "" && roomModel.getRoomNumber() != null) {
				txtRoomPrice.setText(roomModel.getRoomPrice());
				txtRoomType.setText(roomModel.getRoomType() + "");
				txtNoOfPpl.setText(roomModel.getNumberOfPeople());
				int totalVal = Integer.parseInt(roomModel.getRoomPrice()) + Integer.parseInt(txtServiceFees.getText());
				txtTotal.setText(totalVal + "");
			} else {
				handleDialog("Room Number - 107 is Not available between 12/07/22 to 12/08/22 ");
			}
		}
	}

	@FXML
	public void onExitBtn() {
		System.exit(0);
	}

	@FXML
	public void onBackBtn() {
		viewr.handleRoutingCustomerPage("/views/CustomerPageView.fxml", stackpane_customerreservation, loginUserName,
				loginUserPass, "Customer Home");
	}

	@FXML
	public void onResetBtn() {

		this.txtRoomType.clear();
		this.txtRoomNo.clear();
		this.txtRoomPrice.clear();
		this.txtNoOfPpl.clear();
		this.txtTotal.clear();
		this.dateEndField.getEditor().clear();
		this.dateStartField.getEditor().clear();

	}

	public void handleDialog(String body) {
		JFXDialogLayout dialogLayout = new JFXDialogLayout();
		dialogLayout.setHeading(new Text("Alert"));
		dialogLayout.setBody(new Text(body));

		JFXButton ok = new JFXButton("Ok");

		JFXDialog dialog = new JFXDialog(stackpane_customerreservation, dialogLayout,
				JFXDialog.DialogTransition.CENTER);

		ok.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dialog.close();
			}
		});

		dialogLayout.setActions(ok);
		dialog.show();
	}
}
