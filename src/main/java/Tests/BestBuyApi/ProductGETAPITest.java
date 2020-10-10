package Tests.BestBuyApi;
import Base.TestBaseBestBuyAPI;
import Client.RestClient;
import Util.TestUtilBestBuy;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
    public void getProductsTestWithOutHeader() throws IOException, JSONException {
        int t=0;
        domain = prop.getProperty("getPoductDomain");
        apiUrl = prop.getProperty("getProductApi");
        url = domain + apiUrl;

        restClient = new RestClient();
        closeableHttpResponse = restClient.get(url);

        HashMap<Integer, String> hashMap=new HashMap<Integer, String>();
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
            JSONArray arrayCategories = new JSONArray(getCategories);
            for (int j=0; j<arrayCategories.length();j++){
                JSONObject objCat = (JSONObject)arrayCategories.get(j);
                String getName = TestUtilBestBuy.getValueOfJsonObject(objCat, "/name");
                hashMap.put(t, getName);
                t+=1;
            }
        }

        //PrintingHashmap
        for(Map.Entry<Integer, String> entity: hashMap.entrySet()){
            System.out.println(entity.getKey() + " --> " + entity.getValue());
        }

    }
}
