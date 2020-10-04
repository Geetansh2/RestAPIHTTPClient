package Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestUtil {


    public static String getValueByJPath(JSONObject responsejson, String jpath) throws JSONException {
        Object obj = responsejson;
        JSONArray jsonArray;
        for (String s : jpath.split("/")) {
//            System.out.println(s);
            if (!s.isEmpty()) {
                if (!(s.contains("[") || s.contains("]"))) {
                    obj = ((JSONObject) obj).get(s);
//                    System.out.println(obj);
                }
                else if(s.contains("[") || s.contains("]")){
//                    System.out.println(s);
                    jsonArray = ((JSONArray) ((JSONObject) obj).get(s.split("\\[")[0]));
                       obj = jsonArray.get(Integer.parseInt(s.split("\\[")[1].replace("]", "")));
//                    System.out.println("-----------------------------");
//                    System.out.println(obj);
                }

            }
        }
        return obj.toString();
    }
}