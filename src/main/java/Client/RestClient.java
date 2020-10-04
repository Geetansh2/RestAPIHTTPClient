package Client;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONException;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {

    public CloseableHttpResponse get(String url) throws IOException, JSONException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);
        return  closeableHttpResponse;
    }

    public CloseableHttpResponse get(String url, HashMap<String, String> hashMap) throws IOException, JSONException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);

        for (Map.Entry<String, String>entry: hashMap.entrySet()){
            httpget.addHeader(entry.getKey(), entry.getValue());
        }

        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpget);
        return  closeableHttpResponse;
    }

    public CloseableHttpResponse post(String url, String entityString, HashMap<String, String> hashMap) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url); //post request

        httpPost.setEntity(new StringEntity(entityString));//payload

        for(Map.Entry<String,String> entry: hashMap.entrySet()){   //headers
            httpPost.addHeader(entry.getKey(), entry.getValue());
        }
        CloseableHttpResponse closeableHttpResponse = httpClient.execute(httpPost); //execute
        return closeableHttpResponse;
    }
}
