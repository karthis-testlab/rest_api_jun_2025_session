package week5.day1;

import org.testng.annotations.Test;
import java.io.File;

public class E2ETestforIncidentTable extends BaseClass {	
	
	@Test(priority = 1)
	public void createNewRecord() {	
		response = createRecord(requestSpec, new File("src/main/resources/request_payload/create_record.json"));
		validateResponse(response, 201, "Created", "application/json");
		sysId = extractSysId(response, "result.sys_id");
	}
	
	@Test(priority = 2)
	public void retreiveARecord() {
		response = retrieveRecord(requestSpec, sysId);
		validateResponse(response, 200, "OK", "application/json");
		validateResponseBody(response, "result.sys_id", sysId);
		validateResponseBody(response, "result.description", "Create record from file");
	}
	
	@Test(priority = 3)
	public void updateExistingRecord() {
		response = updateExistingRecord(requestSpec, sysId, update_requestPayload);
		validateResponse(response, 200, "OK", "application/json");
		validateResponseBody(response, "result.sys_id", sysId);
		validateResponseBody(response, "result.description", "Update existing record from file");
		validateResponseBody(response, "result.short_description", "REST Update using Put Method");				
	}
	
	@Test(priority = 4)
	public void deleteARecord() {
		response = deleteExistingRecord(requestSpec, sysId);
		validateResponse(response, 204, "No Content");		  
	}
	
	@Test(priority = 5)
	public void isRecordDeletedSuccessfully() {
		response = retrieveRecord(requestSpec, sysId);
		validateResponse(response, 404, "Not Found", "application/json");
	}

}