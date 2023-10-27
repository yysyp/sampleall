package ps.demo.quicktest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.json.JSONObject;

import java.util.Map;
import java.util.TreeMap;

public class JsonComparing {

    @SneakyThrows
    public static void main(String[] args) {

        String string1 = "{\"fullName\": \"Emily Jenkins\", \"age\": 27, \"consumption_info\": {\"fav_product\": \"Coke\", \"last_buy\": \"2012-04-23\"}}";
        String string2 = "{\"age\": 27, \"consumption_info\": {\"last_buy\": \"2012-04-23\", \"fav_product\": \"Coke\"}, \"fullName\": \"Emily Jenkins\"}";


        JsonElement json1 = JsonParser.parseString(string1);
        JsonElement json2 = JsonParser.parseString(string2);

        System.out.println(String.format("json1.equals json2 = %s", json1.equals(json2)));

        System.out.println("json1="+json1);
        System.out.println("json2="+json2);

        ObjectMapper objectMapper = new ObjectMapper();
        Map map1 = objectMapper.readValue(string1, TreeMap.class);
        Map map2 = objectMapper.readValue(string2, TreeMap.class);
        System.out.println("map1=" + map1);
        System.out.println("map2=" + map2);

        JSONObject jsonObject1 = new JSONObject(string1);
        JSONObject jsonObject2 = new JSONObject(string2);

        System.out.println("jsonObject1=["+jsonObject1+"]");
        System.out.println("jsonObject2=["+jsonObject2+"]");
        System.out.println("jsonObject1.tostring.equals(jsonObject2.tostring)="+jsonObject1.toString().equals(jsonObject2.toString()));


    }

}
