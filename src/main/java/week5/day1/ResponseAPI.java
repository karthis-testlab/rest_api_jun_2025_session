package week5.day1;

import java.util.Map;

public interface ResponseAPI {
	
	int getStatusCode();
	String getStatusMessage();
	String getBody();
	Map<String, String> getHeaders();
	String getContentType();

}