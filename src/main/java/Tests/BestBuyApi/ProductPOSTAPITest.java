package Tests.BestBuyApi;

import Base.TestBaseBestBuyAPI;
import Client.RestClient;
import Data.PostProductData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

public class ProductPOSTAPITest extends TestBaseBestBuyAPI{
    TestBaseBestBuyAPI testBaseBestBuyAPI;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    String domainUrl;
    String apiUrl;
    String url;

    public ProductPOSTAPITest() throws IOException {
    }

    @BeforeMethod
    public void setup() throws IOException {
        testBaseBestBuyAPI = new TestBaseBestBuyAPI();
    }

    @Test
    public void postProductsTestWithHeaders() throws IOException, JSONException {
        restClient = new RestClient();
        domainUrl = prop.getProperty("getPoductDomain");
        apiUrl = prop.getProperty("getProductApi");
        url = domainUrl + apiUrl;
        System.out.println("The complete post URI: " + url);

        HashMap<String, String>headers = new HashMap<String, String>();
        headers.put("Content-Type", "application/json");

        //Marshling
        ObjectMapper objectMapper=new ObjectMapper();
        PostProductData postProductData = new PostProductData("Shoes", "loafer", 1000, 20, "random", "Nice shoes", "Nike", "Nike", "abc@123.com", "abc.png");

        String payloadJsonString = objectMapper.writeValueAsString(postProductData);

        System.out.println(payloadJsonString);

        closeableHttpResponse = restClient.post(url, payloadJsonString, headers);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();

        System.out.println("Status code is: " + statusCode);
//        System.out.println(closeableHttpResponse);

        String responseString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println(responseString);

        //Unmarshling
        PostProductData postProductData1 = objectMapper.readValue(responseString, PostProductData.class);
        Assert.assertEquals(postProductData1.getUpc(), postProductData.getUpc());

    }
}
