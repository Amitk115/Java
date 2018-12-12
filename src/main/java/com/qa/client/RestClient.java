package com.qa.client;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	
	
	// GET METHOD API

	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {

		// This will create a client
		CloseableHttpClient httpclient = HttpClients.createDefault();

		// As the API method is GET we have created the object HttpGet with given url.
		HttpGet httpget = new HttpGet(url);

		// Client is executing the GET call with given URL and giving complete response.
		CloseableHttpResponse Closeablehttp_response = httpclient.execute(httpget);

		return Closeablehttp_response;
	}

}
