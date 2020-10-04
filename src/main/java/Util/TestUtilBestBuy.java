package Util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TestUtilBestBuy {


    public static String getValueOfJsonObject(JSONObject jsonResponse, String valueOf) throws JSONException {

        Object obj = jsonResponse;
        JSONArray jsonArray;
        for(String str: valueOf.split("/")){
            if(!str.isEmpty()){
                if (!(str.contains("[")||str.contains("]"))){
                    obj = ((JSONObject)obj).get(str);
                }
                else if (str.contains("[")|| str.contains("]")){
                    jsonArray = (JSONArray)((JSONObject)obj).get(str.split("\\[")[0]);
                    obj = jsonArray.get(Integer.parseInt(str.split("\\[")[1].replace("]", "")));
                    System.out.println("------------" + obj);
//                    System.out.println(jsonArray);
                }

            }
        }
        return obj.toString();

    }

//    public static int getLengthOfJsonObject(JSONObject jsonResponse,String lengthOf) throws JSONException {
//        Object obj = jsonResponse;
//        JSONArray jsonArray;
//        for(String str: lengthOf.split("/")){
//            if(!str.isEmpty()){
//                if (!(str.contains("[")||str.contains("]"))){
//                    obj = ((JSONObject)obj).get(str);
//
//                }
//                else if (str.contains("[")|| str.contains("]")){
//                    jsonArray = (JSONArray)((JSONObject)obj).get(str.split("\\[")[0]);
//                    obj = jsonArray.get(Integer.parseInt(str.split("\\[")[1].replace("]", "")));
//                    System.out.println("------------" + obj);
////                    System.out.println(jsonArray);
//                }
//
//            }
//        }
//        return 1;
//    }

}
