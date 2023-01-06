package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import app.Project;

public class ManagerModel extends Project {
	private int cid;
	private String cname;
	private String ccity;
	private String cstate;
	private int cage;
	private int cpincode;
	private String cemail;
	private String roomtype;
	private int roomprice;
	private int noofpeople;
	private String startdate;
	private String enddate;
	private String roomNo;
	// Declare DB objects
	private Connection OracleConnection;
	Statement stmt = null;

	public ManagerModel() {
		OracleConnection = SetConnection();
	}

	
	
	/**
	 * @return the cid
	 */
	public int getCid() {
		return cid;
	}

	/**
	 * @param cid the cid to set
	 */
	public void setCid(int cid) {
		this.cid = cid;
	}

	/**
	 * @return the cname
	 */
	public String getCname() {
		return cname;
	}

	/**
	 * @param cname the cname to set
	 */
	public void setCname(String cname) {
		this.cname = cname;
	}

	/**
	 * @return the ccity
	 */
	public String getCcity() {
		return ccity;
	}

	/**
	 * @param ccity the ccity to set
	 */
	public void setCcity(String ccity) {
		this.ccity = ccity;
	}

	/**
	 * @return the cstate
	 */
	public String getCstate() {
		return cstate;
	}

	/**
	 * @param cstate the cstate to set
	 */
	public void setCstate(String cstate) {
		this.cstate = cstate;
	}

	/**
	 * @return the cage
	 */
	public int getCage() {
		return cage;
	}

	/**
	 * @param cage the cage to set
	 */
	public void setCage(int cage) {
		this.cage = cage;
	}

	/**
	 * @return the cpincode
	 */
	public int getCpincode() {
		return cpincode;
	}

	/**
	 * @param cpincode the cpincode to set
	 */
	public void setCpincode(int cpincode) {
		this.cpincode = cpincode;
	}

	/**
	 * @return the cemail
	 */
	public String getCemail() {
		return cemail;
	}

	/**
	 * @param cemail the cemail to set
	 */
	public void setCemail(String cemail) {
		this.cemail = cemail;
	}

	/**
	 * @return the roomtype
	 */
	public String getRoomtype() {
		return roomtype;
	}

	/**
	 * @param roomtype the roomtype to set
	 */
	public void setRoomtype(String roomtype) {
		this.roomtype = roomtype;
	}

	/**
	 * @return the roomprice
	 */
	public int getRoomprice() {
		return roomprice;
	}

	/**
	 * @param roomprice the roomprice to set
	 */
	public void setRoomprice(int roomprice) {
		this.roomprice = roomprice;
	}

	/**
	 * @return the noofpeople
	 */
	public int getNoofpeople() {
		return noofpeople;
	}

	/**
	 * @param noofpeople the noofpeople to set
	 */
	public void setNoofpeople(int noofpeople) {
		this.noofpeople = noofpeople;
	}

	/**
	 * @return the startdate
	 */
	public String getStartdate() {
		return startdate;
	}

	/**
	 * @param startdate the startdate to set
	 */
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	/**
	 * @return the enddate
	 */
	public String getEnddate() {
		return enddate;
	}

	/**
	 * @param enddate the enddate to set
	 */
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}


	/**
	 * @return the roomNo
	 */
	public String getRoomNo() {
		return roomNo;
	}

	/**
	 * @param roomNo the roomNo to set
	 */
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}

	public List<ManagerModel> getCustomers() {
		List<ManagerModel> customers = new ArrayList<>();
		String query = "SELECT id, name, city, state, email, pincode, age FROM customers_2711";
		try (PreparedStatement statement = OracleConnection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ManagerModel customer = new ManagerModel();
				// grab record data by table field name into ClientModel account object
				customer.setCid(resultSet.getInt("id"));
				customer.setCage(resultSet.getInt("age"));
				customer.setCpincode(resultSet.getInt("pincode"));
				customer.setCname(resultSet.getString("name"));
				customer.setCcity(resultSet.getString("city"));
				customer.setCstate(resultSet.getString("state"));
				customer.setCemail(resultSet.getString("email"));
				customers.add(customer); // add account data to arraylist
			}
		} catch (SQLException e) {
			System.out.println("Error fetching Customers: " + e);
		}
		return customers; // return arraylist
	}

	public List<ManagerModel> getBooking() {
		List<ManagerModel> bookings = new ArrayList<>();
		//System.out.println("here");
		//String query = "SELECT custname, custcity,  roomtype, numberofpeople, roomprice, startdate, enddate, roomnumber FROM bookings_2711";
		String query = "SELECT Customers_2711.NAME, Customers_2711.CITY,Rooms_2711.RoomTYPE , Rooms_2711.numberofpeople,rooms_2711.roomprice, startdate, enddate, Rooms_2711.Roomnumber FROM Customers_2711,rooms_2711,Bookings_2711 WHERE  Customers_2711.USERNAME = Bookings_2711.USERNAME AND rooms_2711.RoomNUMBER = Bookings_2711.RoomNUMBER ";
		try (PreparedStatement statement = OracleConnection.prepareStatement(query)) {
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				ManagerModel booking = new ManagerModel();
				// grab record data by table field name into ClientModel account object
				booking.setRoomtype(resultSet.getString("roomtype"));
				booking.setNoofpeople(resultSet.getInt("numberofpeople"));
				booking.setRoomprice(resultSet.getInt("roomprice"));
				booking.setCname(resultSet.getString("name"));
				booking.setCcity(resultSet.getString("city"));
				booking.setRoomNo(resultSet.getInt("roomnumber")+"");
				booking.setStartdate(resultSet.getDate("startdate") + "");
				booking.setEnddate(resultSet.getDate("enddate") + "");
				bookings.add(booking);
			}
		} catch (SQLException e) {
			System.out.println("Error fetching Bookings: " + e);
		}
		return bookings; 
	}
}
