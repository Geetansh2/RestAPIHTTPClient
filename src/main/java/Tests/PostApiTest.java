package Tests;

import Base.TestBase;
import Client.RestClient;
import Data.Users;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class PostApiTest extends TestBase {

    TestBase testBase;
    RestClient restClient;
    String serviceUrl;
    String apiUrl;
    String url;
    CloseableHttpResponse closeableHttpResponse;

    @BeforeMethod
    public void setUp() throws IOException {
        testBase = new TestBase();
        serviceUrl = prop.getProperty("URL");
        apiUrl = prop.getProperty("serviceURL");

        url = serviceUrl + apiUrl;

    }

    @Test
    public void postAPITest() throws IOException, JSONException {
        restClient = new RestClient();

        HashMap<String, String> headerMap = new HashMap<String, String>();

        headerMap.put("Content-Type", "application-json"); //headers

        //Marshling
        ObjectMapper mapper= new ObjectMapper();
        Users users = new Users("morpheus", "leader");

        //object to json file
        mapper.writeValue(new File("/Users/geetansh/Documents/RestAPIHTTPClient/src/main/java/Data/users.json"), users);

        //object to json in string
        String userJsonString = mapper.writeValueAsString(users);
        System.out.println(userJsonString);

        closeableHttpResponse = restClient.post(url, userJsonString, headerMap);
        int statusCode  = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code is: " + statusCode);
        Assert.assertEquals(TestBase.RESPONSE_STATUS_CODE_201, 201);

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println("Response json is: " + responseString);

        JSONObject responseJson = new JSONObject(responseString);

        //Un-marshling
        Users usersObject = mapper.readValue((responseString), Users.class);
        System.out.println(usersObject);

        System.out.println(usersObject.getName());


    }

}
