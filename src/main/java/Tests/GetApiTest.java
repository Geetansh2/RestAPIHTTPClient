package Tests;

import Base.TestBase;
import Client.RestClient;
import Util.TestUtil;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class GetApiTest extends TestBase {
    TestBase testBase;
    RestClient restClient;
    String serviceUrl;
    String apiUrl;
    String url;
    CloseableHttpResponse closeableHttpResponse;
//
    @BeforeMethod
    public void setUp() throws IOException {
        testBase = new TestBase();
         serviceUrl = prop.getProperty("URL");
         apiUrl = prop.getProperty("serviceURL");

         url = serviceUrl + apiUrl;

    }

    @Test(priority = 0)
    public void getAPITestWithoutHeaders() throws IOException, JSONException {
        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code is: " + statusCode );

        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

        JSONObject reponseJson = new JSONObject(responseString);
        System.out.println("Json response is: " + responseString);

        String perPage = TestUtil.getValueByJPath(reponseJson, "/per_page");
        System.out.println("PerPage is: " + perPage);

        Assert.assertEquals(Integer.parseInt(perPage), 6, "Per page value doesn't match");

        Header[] headersArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allheaders = new HashMap<String,String>();

        for(Header header: headersArray){
            allheaders.put(header.getName(), header.getValue());
        }

        System.out.println("Header Array: " + allheaders);

        TestUtil.getValueByJPath(reponseJson, "/data[0]/last_name");

    }


    @Test(priority = 1)
    public void getAPITestWithHeaders() throws IOException, JSONException {
        restClient = new RestClient();

        HashMap<String, String> headerMap = new HashMap<String, String>();
        headerMap.put("Content-Type", "application/json");

        closeableHttpResponse = restClient.get(url, headerMap);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code is: " + statusCode );

        Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200, "Status code is not 200");

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");

        JSONObject reponseJson = new JSONObject(responseString);
        System.out.println("Json response is: " + responseString);

        String perPage = TestUtil.getValueByJPath(reponseJson, "/per_page");
        System.out.println("PerPage is: " + perPage);

        Assert.assertEquals(Integer.parseInt(perPage), 6, "Per page value doesn't match");

        Header[] headersArray = closeableHttpResponse.getAllHeaders();
        HashMap<String, String> allheaders = new HashMap<String,String>();

        for(Header header: headersArray){
            allheaders.put(header.getName(), header.getValue());
        }

        System.out.println("Header Array: " + allheaders);

        TestUtil.getValueByJPath(reponseJson, "/data[0]/last_name");

    }

}
