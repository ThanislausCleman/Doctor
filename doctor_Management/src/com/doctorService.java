package com;



import model.doctor;

//For REST Service 
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

//For JSON 
import com.google.gson.*;

//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

@Path("/doctor")

public class doctorService {

	
	doctor docObj = new doctor();
	
	//Read doctor details from doctor table 
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readDoctor() {
		return docObj.readDoctor();
	}
	
	
	
	//Insert Doctor details into Doctor table
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertDoctor(@FormParam("Name") String name, 
			@FormParam("Specialization") String specialization,
			@FormParam("NIC") String nic,
			@FormParam("Mobile") String mobile, 
			@FormParam("DoctorFee") String doctorFee) {
		String output = docObj.insertDoctor(name, specialization, nic, mobile);
		return output;
	}
	
	
	
	
	
	
	
	//Doctor details update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateDoctor(String doctorData) {
		// Convert the input string to a JSON object
		JsonObject doctorObject = new JsonParser().parse(doctorData).getAsJsonObject();

		// Read the values from the JSON object
		String did = doctorObject.get("DID").getAsString();
		String name = doctorObject.get("Name").getAsString();
		String specialization = doctorObject.get("Specialization").getAsString();
		String mobile = doctorObject.get("mobile").getAsString();
		String doctorFee = doctorObject.get("DoctorFee").getAsString();

		String output =  docObj.updateDoctor(did,name,specialization,mobile, doctorFee );

		return output;
	}
	
	
	
	
	
	
	//Delete doctor details
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteDoctor(String doctorData) 
	{ // Convert the input string to XML document 
		Document doc = Jsoup.parse(doctorData, "",Parser.xmlParser()); 
		//Read the value from the element <itemID> 
		String DID =doc.select("DID").text();

		String output = docObj.deleteDoctor(DID);

		return output;
	}

	
	
	
}
