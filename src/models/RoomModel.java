package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import app.Project;
//import dao.DBConnect;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RoomModel extends Project {
	String roomId;
	String roomType;
	String roomNumber;
	String numberOfPeople;
	String roomPrice;
	String roomStatus;
	String startDate;
	String endDate;

	// Declare DB objects
	private Connection OracleConnection;
	Statement stmt = null;

	public RoomModel() {
		OracleConnection = SetConnection();

	}

	public RoomModel(String id, String roomType, String roomNumber, String numberOfPeople, String roomPrice,
			String roomStatus, String startDate, String endDate) {
		super();
		this.roomId = id;
		this.roomType = roomType;
		this.roomNumber = roomNumber;
		this.numberOfPeople = numberOfPeople;
		this.roomPrice = roomPrice;
		this.roomStatus = roomStatus;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	/**
	 * @return the id
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * @param id the id to set
	 */
	public void setRoomId(String id) {
		this.roomId = id;
	}

	/**
	 * @return the roomType
	 */
	public String getRoomType() {
		return roomType;
	}

	/**
	 * @param roomType the roomType to set
	 */
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	/**
	 * @return the roomNumber
	 */
	public String getRoomNumber() {
		return roomNumber;
	}

	/**
	 * @param roomNumber the roomNumber to set
	 */
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * @return the numberOfPeople
	 */
	public String getNumberOfPeople() {
		return numberOfPeople;
	}

	/**
	 * @param numberOfPeople the numberOfPeople to set
	 */
	public void setNumberOfPeople(String numberOfPeople) {
		this.numberOfPeople = numberOfPeople;
	}

	/**
	 * @return the roomPrice
	 */
	public String getRoomPrice() {
		return roomPrice;
	}

	/**
	 * @param roomPrice the roomPrice to set
	 */
	public void setRoomPrice(String roomPrice) {
		this.roomPrice = roomPrice;
	}

	/**
	 * @return the roomStatus
	 */
	public String getRoomStatus() {
		return roomStatus;
	}

	/**
	 * @param roomStatus the roomStatus to set
	 */
	public void setRoomStatus(String roomStatus) {
		this.roomStatus = roomStatus;
	}
	public String getStartDate() {
		return startDate;
	}

	/**
	 * @param roomStatus the roomStatus to set
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getEndDate() {
		return endDate;
	}

	/**
	 * @param roomNumber the roomNumber to set
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public ObservableList<RoomModel> getRooms(String query) {
		ObservableList<RoomModel> rooms = FXCollections.observableArrayList();
		try (PreparedStatement statement = OracleConnection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				RoomModel room = new RoomModel();
				// grab record data by table field name into ClientModel account object
				room.setRoomId(resultSet.getInt("roomid") + "");
				room.setRoomType(resultSet.getString("roomtype"));
				room.setNumberOfPeople(resultSet.getInt("numberofpeople") + "");
				room.setRoomNumber(resultSet.getInt("roomnumber") + "");
				room.setRoomPrice(resultSet.getInt("roomprice") + "");
				room.setRoomStatus(resultSet.getString("roomstatus"));
				room.setStartDate(resultSet.getString("startdate").substring(0 , 10));
				room.setEndDate(resultSet.getString("enddate").substring(0 , 10));

				rooms.add(room);
			}
		} catch (SQLException e) {
			System.out.println("Error fetching Room Info: " + e);
		}
		return rooms;
	}

	public int updateRoomByRoomNumber(int roomNumber) {
		int result = 0;
		String query = "UPDATE  rooms_2711 SET roomstatus=? WHERE roomnumber=?";
		try (PreparedStatement statement = OracleConnection.prepareStatement(query)) {
			statement.setString(1, "available");
			statement.setInt(2, roomNumber);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error Updating Room Info: " + e);
		}
		return result;
	}
	/*
	public int deleteRoomByRoomNumber(int roomNumber) {
		int result = 0;
		String query = "DELETE FROM  rooms_2711 WHERE roomnumber=?";
		try (PreparedStatement statement = OracleConnection.prepareStatement(query)) {
			//statement.setString(1, "available");
			statement.setInt(1, roomNumber);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error Deleting Room Info: " + e);
		}
		return result;
	}*/
	
	public int deleteRoomByRoomNumber(int roomNumber) {
		int result = 0;
		String query = "DELETE FROM  rooms_2711 WHERE roomnumber=?";
		try (PreparedStatement statement = OracleConnection.prepareStatement(query)) {
			//statement.setString(1, "available");
			statement.setInt(1, roomNumber);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error Deleting Room Info: " + e);
		}
		return result;
	}		
	
	public void findRoomByRoomNumber(int roomNumber) {
		String query = "select * from  rooms_2711 WHERE roomstatus='available' and roomnumber=?";
		try (PreparedStatement statement = OracleConnection.prepareStatement(query)) {
			statement.setInt(1, roomNumber);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				setRoomId(resultSet.getInt("roomid") + "");
				setRoomType(resultSet.getString("roomtype"));
				setNumberOfPeople(resultSet.getInt("numberofpeople") + "");
				setRoomNumber(resultSet.getInt("roomnumber") + "");
				setRoomPrice(resultSet.getInt("roomprice") + "");
				setRoomStatus(resultSet.getString("roomstatus"));
				setRoomStatus(resultSet.getString("roomstatus"));
				
			}

		} catch (SQLException e) {
			System.out.println("Error fetching Room Info: " + e);
		}
	
	}
	
	//If resultSet.getString("roomstatus")="busy" then
		//	SYstem()

	public int deleteRoomByRoomNumber2(int roomNumber) {
		// TODO Auto-generated method stub
		int result = 0;
		String query = "DELETE FROM  rooms_2711 WHERE roomnumber=?";
		try (PreparedStatement statement = OracleConnection.prepareStatement(query)) {
			//statement.setString(1, "available");
			statement.setInt(1, roomNumber);
			result = statement.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error Deleting Room Info: " + e);
		}
		return result;
	}

}
