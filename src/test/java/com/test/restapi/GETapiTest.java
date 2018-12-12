package com.test.restapi;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.client.DataWriiter;
import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GETapiTest {

	TestBase testbase;
	String URI;
	String clientURL;
	String apiURL;
	RestClient restclient;

	CloseableHttpResponse Closeablehttp_response;
	DataWriiter datawriter = new DataWriiter();

	@BeforeMethod
	public void setup() {

		testbase = new TestBase();
		clientURL = testbase.prop.getProperty("URL");
		apiURL = testbase.prop.getProperty("ServiceURL");
		URI = clientURL + apiURL;

	}

	@Test
	public void GETapitest() throws ClientProtocolException, IOException {

		restclient = new RestClient();
		Closeablehttp_response = restclient.get(URI);

		// Getting the Status code from Response
		int Status_Cade = Closeablehttp_response.getStatusLine().getStatusCode();
		System.out.println("Response code--->" + Status_Cade);

		Assert.assertEquals(Status_Cade, testbase.RESPONSE_STATUS_CODE_200, "Status code is not 200");

		// Getting the response string in UTF-8 format
		String response_string = EntityUtils.toString(Closeablehttp_response.getEntity(), "UTF-8");
		// System.out.println(response);

		// Converting the Response in JASON format
		JSONObject jason_response = new JSONObject(response_string);
		System.out.println("JASON RESPONSE from API" + jason_response);

		String per_page = TestUtil.getValueByJPath(jason_response, "/per_page");
		System.out.println("per_page = " + per_page);
		Assert.assertEquals(Integer.parseInt(per_page), 3, "per_page count is invalid");
		
		String total_pages = TestUtil.getValueByJPath(jason_response, "/total_pages");
		System.out.println("total_pages = " + total_pages);
		Assert.assertEquals(Integer.parseInt(total_pages), 4, "total_pages count is invalid");
		
		String data_id=TestUtil.getValueByJPath(jason_response, "/data[0]/id");
		String data_name=TestUtil.getValueByJPath(jason_response, "/data[0]/name");
		String data_year=TestUtil.getValueByJPath(jason_response, "/data[0]/year");
		String data_color=TestUtil.getValueByJPath(jason_response, "/data[0]/color");
		String data_pantone_value=TestUtil.getValueByJPath(jason_response, "/data[0]/pantone_value");
		
		System.out.println("data_id= "+data_id);
		System.out.println("data_name="+ data_name);
		System.out.println("data_year= "+data_year);
		System.out.println("data_color= "+data_color);
		System.out.println("data_pantone_value= "+data_pantone_value);
		
		
		Assert.assertEquals(Integer.parseInt(data_id), 100, "data_id count is invalid");
		Assert.assertEquals(data_name, "cerulean", "Name is invalid");
		Assert.assertEquals(Integer.parseInt(data_year), 2000, "data_year count is invalid");
		Assert.assertEquals(data_color, "#98B2D1", "data_color is invalid");
		

		// Getting the Headers present in response. It returns the array of headers
		Header[] headerArray = Closeablehttp_response.getAllHeaders();

		// To get the headers in key-value format we are converting this array in
		// HashMap
		HashMap<String, String> Headers = new HashMap<String, String>();

		// Putting all the values present in Array in HashMap throgh the for loop
		// iteration
		for (Header header : headerArray) {
			Headers.put(header.getName(), header.getValue());
		}
		// Printing all the values of HashMap->Headers
		System.out.println("HeadersArray----->" + Headers);

		datawriter.writeData(Headers);

	}

}
