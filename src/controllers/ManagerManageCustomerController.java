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
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import models.ManagerModel;
import models.ViewsRouting;

public class ManagerManageCustomerController implements Initializable {
	
	@FXML
	private AnchorPane manager_manage_customer_view_pane;
	@FXML
	private TableView<ManagerModel> tblCustomers;
	
	@FXML
	private TableColumn<ManagerModel, String> roomtype;
	@FXML
	private TableColumn<ManagerModel, String> cname;
	@FXML
	private TableColumn<ManagerModel, String> roomNo;
	@FXML
	private TableColumn<ManagerModel, String> ccity;
	@FXML
	private TableColumn<ManagerModel, String> noofpeople;
	@FXML
	private TableColumn<ManagerModel, String> roomprice;
	@FXML
	private TableColumn<ManagerModel, String> startdate;
	@FXML
	private TableColumn<ManagerModel, String> enddate;

	private Connection OracleConnection;
	Statement stmt = null;
	ViewsRouting viewr = null;
	ManagerModel mgrModel = null;

	public ManagerManageCustomerController() {
		OracleConnection = SetConnection();
		viewr = new ViewsRouting();
		mgrModel = new ManagerModel();
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
	public void onManageCustomerBack() {
//		tblCustomers.getItems().setAll(mgrModel.getCustomers()); // load table data from ClientModel List		
		viewr.handleRouting("/views/ManagerPageView.fxml", manager_manage_customer_view_pane,"Manager Home");

	}

	@FXML
	public void onRefreshCustomerList() {
		tblCustomers.getItems().setAll(mgrModel.getBooking()); // load table data from ClientModel List		
	}

	

	@FXML
	public void onManageCustomerExit() {
		System.exit(0);

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		tblCustomers.getItems().setAll(mgrModel.getBooking()); // load table data from ClientModel List		

		roomtype.setCellValueFactory(new PropertyValueFactory<ManagerModel, String>("roomtype"));
		cname.setCellValueFactory(new PropertyValueFactory<ManagerModel, String>("cname"));
		ccity.setCellValueFactory(new PropertyValueFactory<ManagerModel, String>("ccity"));		
		noofpeople.setCellValueFactory(new PropertyValueFactory<ManagerModel, String>("noofpeople"));
		roomprice.setCellValueFactory(new PropertyValueFactory<ManagerModel, String>("roomprice"));
		roomNo.setCellValueFactory(new PropertyValueFactory<ManagerModel, String>("roomNo"));
		startdate.setCellValueFactory(new PropertyValueFactory<ManagerModel, String>("startdate"));
		enddate.setCellValueFactory(new PropertyValueFactory<ManagerModel, String>("enddate"));
		
		// auto adjust width of columns depending on their content
		tblCustomers.setColumnResizePolicy((param) -> true);
		Platform.runLater(() -> customResize(tblCustomers));

	}
	
	public void customResize(TableView<?> view) {
        AtomicLong width = new AtomicLong();
        view.getColumns().forEach(col -> {
            width.addAndGet((long) col.getWidth());
        });
        double tableWidth = view.getWidth();

        if (tableWidth > width.get()) {
            view.getColumns().forEach(col -> {
                col.setPrefWidth(col.getWidth()+((tableWidth-width.get())/view.getColumns().size()));
            });
        }
    }

}
