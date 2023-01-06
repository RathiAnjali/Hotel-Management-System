package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import com.mysql.cj.xdevapi.Statement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Project extends Application {

	public static Stage stage; // set global stage object!!!
	public static Connection OracleConnection;
	
	public Connection SetConnection(){
		 try {
			return DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-QRVS9B0:1521:xe","system","SHankar$1996");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		}
	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			FXMLLoader loader = new FXMLLoader(Project.class.getResource("/views/HomescreenView.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root,600,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setTitle("Home");
			stage.setScene(scene);
			stage.getIcons().add(new Image("/images/009-hotel.png"));

			stage.show();

		} catch (Exception e) {
			System.out.println("Error occured while inflating view: " + e);
		}

	}

	public static void main(String[] args)throws Exception {
		launch(args);
		//Class.forName("oracle.jdbd.driver.OracleDriver");
		OracleConnection =  DriverManager.getConnection("jdbc:oracle:thin:@DESKTOP-QRVS9B0:1521:xe","system","SHankar$1996");
		Statement st = OracleConnection.createStatement();
		String query = "select * from admins_2711";
		ResultSet rs = st.executeQuery(query);
		while(rs.next()) {
			System.out.println(rs.getInt(1)+""+rs.getString(2));
		}
		rs.close();
		st.close();
		OracleConnection.close();
	}
}
