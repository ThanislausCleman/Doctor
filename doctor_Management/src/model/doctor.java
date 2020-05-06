package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import Util.DB_Connection;

public class doctor {
	
	
	
	

		
	
	
	
	//Insert doctor details into doctor table
	// Insert
	public String insertDoctor( String name, String specialization, String mobile, String doctorFee) {
		String output = "";

		try {
		DB_Connection obj_DB_Connection= new DB_Connection();
			Connection con = obj_DB_Connection.connect();

			if (con == null) {
				return "Error while connecting to the database";
			}

			// create a prepared statement for insert values into database 
			String query = "insert into Doctor(`DID`,`Name`,`Specialization`,`Mobile`, `DoctorFee`)"
					+ " values (?, ?, ?,?,?)";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, name);
			preparedStmt.setString(3, specialization);
			
			preparedStmt.setInt(5, Integer.parseInt(mobile));
			
			preparedStmt.setDouble(7, Double.parseDouble(doctorFee));
			

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Inserted doctor details successfully";
		} catch (Exception e) {
			output = "Error while inserting";
			System.err.println(e.getMessage());
		}

		return output;
	}
	
	
	
	
	
	

	
	//Read doctor details
	
	

		public String readDoctor() {
			String output = "";

			try {
				DB_Connection obj_DB_Connection= new DB_Connection();
				Connection con = obj_DB_Connection.connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}

				// Prepare the html table to be displayed
				
				output = "<table border=\"1\"><tr><th>Doctor Name</th>" + "<th>Specialization</th><th>NIC</th>"
						+ "<th>Mobile</th>" + "<th>Email</th>" + "<th>Doctor Fee</th></tr>";

				String query = "select *  from Doctor ";
				
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);

				// iterate through the rows in the result set
				while (rs.next()) {
					String DID = Integer.toString(rs.getInt("DID"));
					String Name = rs.getString("Name");
					String Specialization = rs.getString("Specialization");
					
					String Mobile = Integer.toString(rs.getInt("Mobile"));
					
					String DoctorFee = Double.toString(rs.getDouble("DoctorFee"));
					
					

					// Add into the html table
					output += "<tr><td>" + Name + "</td>";
					output += "<td>" + Specialization + "</td>";
					
					output += "<td>" + Mobile + "</td>";
				
					output += "<td>" + DoctorFee + "</td>";
					
					

					// buttons
					
				}
				con.close();

				// Complete the html table
				output += "</table>";

			} catch (Exception e) {
				output = "Error while reading the doctor.";
				System.err.println(e.getMessage());
			}

			return output;

		}
		
		
		
		
		//Update doctor details
		// update

		public String updateDoctor(String ID, String name, String specialization, String mobile,String doctorFee) {
			String output = "";

			try {
				DB_Connection obj_DB_Connection= new DB_Connection();
				Connection con = obj_DB_Connection.connect();

				if (con == null) {
					return "Error while connecting to the database for updating.";
				}

				// create a prepared statement
				String query = "UPDATE Doctor SET Name=?,Specialization=?,Mobile=?, DoctorFee=? WHERE DID=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values doctor table
				preparedStmt.setString(1, name);
				preparedStmt.setString(2, specialization);
				preparedStmt.setInt(4, Integer.parseInt(mobile));
				preparedStmt.setDouble(6, Double.parseDouble(doctorFee));
				preparedStmt.setInt(7, Integer.parseInt(ID));
				
				// execute the statement 
				preparedStmt.execute(); 
				con.close();

				output = "Updated doctor details successfully";
			} catch (Exception e) {
				output = "Error while updating the doctor.";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		
		
		// Delete

		public String deleteDoctor(String DID) {
			String output = "";

			try {
				DB_Connection obj_DB_Connection= new DB_Connection();
				Connection con = obj_DB_Connection.connect();
				if (con == null) {
					return "Error while connecting to the database for deleting.";
				}

				// create a prepared statement
				
				String query = "delete from Doctor where DID=?";

				PreparedStatement preparedStmt = con.prepareStatement(query);

				// binding values
				preparedStmt.setInt(1, Integer.parseInt(DID));
				// execute the statement
				preparedStmt.execute();
				con.close();

				output = "Deleted successfully";

			} catch (Exception e) {
				output = "Error while deleting the item.";
				System.err.println(e.getMessage());
			}

			return output;
		}
		
		
		
		
	}


