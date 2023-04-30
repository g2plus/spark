package top.arhi.test;

import com.alibaba.fastjson.JSONObject;

public class JsonParseTest {
    private static String json="{\n" +
            "  \"status\": \"1\",\n" +
            "  \"regeocode\": {\n" +
            "    \"addressComponent\": {\n" +
            "      \"city\": \"广州市\",\n" +
            "      \"province\": \"广东省\",\n" +
            "      \"adcode\": \"440106\",\n" +
            "      \"district\": \"天河区\",\n" +
            "      \"towncode\": \"440106012000\",\n" +
            "      \"streetNumber\": {\n" +
            "        \"number\": \"194号\",\n" +
            "        \"location\": \"113.366054,23.131065\",\n" +
            "        \"direction\": \"东北\",\n" +
            "        \"distance\": \"166.107\",\n" +
            "        \"street\": \"中山大道西\"\n" +
            "      },\n" +
            "      \"country\": \"中国\",\n" +
            "      \"township\": \"天园街道\",\n" +
            "      \"businessAreas\": [\n" +
            "        [\n" +
            "          \n" +
            "        ]\n" +
            "      ],\n" +
            "      \"building\": {\n" +
            "        \"name\": \"天河公园\",\n" +
            "        \"type\": \"风景名胜;公园广场;公园\"\n" +
            "      },\n" +
            "      \"neighborhood\": {\n" +
            "        \"name\": [\n" +
            "          \n" +
            "        ],\n" +
            "        \"type\": [\n" +
            "          \n" +
            "        ]\n" +
            "      },\n" +
            "      \"citycode\": \"020\"\n" +
            "    },\n" +
            "    \"formatted_address\": \"广东省广州市天河区天园街道天河公园\"\n" +
            "  },\n" +
            "  \"info\": \"OK\",\n" +
            "  \"infocode\": \"10000\"\n" +
            "}";

    public static void main(String[] args) {
        test();
    }

    public static void test(){
        JSONObject jsonObject = JSONObject.parseObject(json);
        JSONObject regeocode = jsonObject.getJSONObject("regeocode");
        JSONObject addressComponent = regeocode.getJSONObject("addressComponent");
        String city = addressComponent.get("city").toString();
        String province = addressComponent.get("province").toString();
        String district = addressComponent.get("district").toString();
        JSONObject streetNumber = addressComponent.getJSONObject("streetNumber");
        String number = streetNumber.get("number").toString();
        String street = streetNumber.get("street").toString();
        String township = addressComponent.get("township").toString();
        JSONObject building = addressComponent.getJSONObject("building");
        String name = building.get("name").toString();
        System.out.println(province+city+district+number+street+township+name);
    }
}
