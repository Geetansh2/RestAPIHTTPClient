package Tests.BestBuyApi;

import Base.TestBase;
import Base.TestBaseBestBuyAPI;
import Client.RestClient;
import Util.TestUtilBestBuy;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class ProductGETAPITest extends TestBaseBestBuyAPI {

    TestBaseBestBuyAPI testBaseBestBuyAPI;
    RestClient restClient;
    CloseableHttpResponse closeableHttpResponse;
    String domain;
    String apiUrl;
    String url;

    public ProductGETAPITest() throws IOException {
    }

    @BeforeMethod
  public void setUp() throws IOException {
      testBaseBestBuyAPI = new TestBaseBestBuyAPI();


  }
    @Test
    public void getProductsTest() throws IOException, JSONException {
        domain = prop.getProperty("getPoductDomain");
        apiUrl = prop.getProperty("getProductApi");
        url = domain + apiUrl;

        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);

        int statusCode = closeableHttpResponse.getStatusLine().getStatusCode();
        System.out.println("Status code is: " + statusCode);

        String jsonString = EntityUtils.toString(closeableHttpResponse.getEntity(), "UTF-8");
        System.out.println("--------------------------------------");

        JSONObject jsonObject = new JSONObject(jsonString);
        String getData = TestUtilBestBuy.getValueOfJsonObject(jsonObject, "/data");
        JSONArray array = new JSONArray(getData);
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = (JSONObject)array.get(i);
            String getCategories = TestUtilBestBuy.getValueOfJsonObject(obj, "/categories");
//            System.out.println(getCategories);
            JSONArray arrayCategories = new JSONArray(getCategories);
            for (int j=0; j<arrayCategories.length();j++){
                JSONObject objCat = (JSONObject)arrayCategories.get(j);
//                System.out.println(objCat);
                String getName = TestUtilBestBuy.getValueOfJsonObject(objCat, "/name");
                System.out.println(getName);
            }
            System.out.println("--------------------------");
        }

    }
}
