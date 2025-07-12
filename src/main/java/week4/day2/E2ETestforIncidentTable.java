package week4.day2;

import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import java.io.File;

public class E2ETestforIncidentTable extends BaseClass {	
	
	IncidentService incident = new IncidentService();
	
	@Test(priority = 1)
	public void createNewRecord() {	
		response = incident.createRecord(requestSpec, new File("src/main/resources/request_payload/create_record.json"));
		incident.validateResponse(response, 201, "Created", ContentType.JSON);
		sysId = incident.extractSysId(response, "result.sys_id");
	}
	
	@Test(priority = 2)
	public void retreiveARecord() {
		response = incident.retrieveRecord(requestSpec, sysId);
		incident.validateResponse(response, 200, "OK", ContentType.JSON);
		incident.validateResponseBody(response, "result.sys_id", sysId);
		incident.validateResponseBody(response, "result.description", "Create record from file");
	}
	
	@Test(priority = 3)
	public void updateExistingRecord() {
		response = incident.updateExistingRecord(requestSpec, sysId, update_requestPayload);
		incident.validateResponse(response, 200, "OK", ContentType.JSON);
		incident.validateResponseBody(response, "result.sys_id", sysId);
		incident.validateResponseBody(response, "result.description", "Update existing record from file");
		incident.validateResponseBody(response, "result.short_description", "REST Update using Put Method");				
	}
	
	@Test(priority = 4)
	public void deleteARecord() {
		response = incident.deleteExistingRecord(requestSpec, sysId);
		incident.validateResponse(response, 204, "No Content");		  
	}
	
	@Test(priority = 5)
	public void isRecordDeletedSuccessfully() {
		response = incident.retrieveRecord(requestSpec, sysId);
		incident.validateResponse(response, 404, "Not Found", ContentType.JSON);
	}

}