package com.example.simplewatherapp;


import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpRequestsTest {

    @Value("${api.url}")
    private String BASE_URL;

    @Value("${api.key}")
    private String APP_ID;

    @Value("${api.city}")
    private String CITY;

    @Before
    public void setUp(){
    }

    @Test
    public void defaultResponseContentTypeIsJsonTest() throws ClientProtocolException, IOException {
        String jsonMimeType = "application/json";
        HttpUriRequest request = new HttpGet(new UriTemplate(BASE_URL).expand(CITY, APP_ID));

        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
        assertEquals( jsonMimeType, mimeType );
    }

    @Test
    public void isStatusOkReceivedTest() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpGet( new UriTemplate(BASE_URL).expand(CITY, APP_ID));

        HttpResponse response = HttpClientBuilder.create().build().execute( request );

        assertThat(response.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_OK));
    }
}
