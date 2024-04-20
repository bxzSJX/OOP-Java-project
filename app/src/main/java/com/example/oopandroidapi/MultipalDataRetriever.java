package com.example.oopandroidapi;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

public class MultipalDataRetriever {
    static HashMap<String,String> municipalityNamesToCodeMap = null;
    static ObjectMapper objectMapper = new ObjectMapper();
    public static HashMap<String, String> getMunicipalityCodeMap(){
        if (municipalityNamesToCodeMap == null){
            JsonNode areas = readAreaDataFromTheAPIURL(objectMapper);
            municipalityNamesToCodeMap = createMunicipalityNamesToCodeMap(areas);
        }
        return municipalityNamesToCodeMap;
    }
    public void getPopulation(Context context, String cityName, final PopulationDataCallback callback) {
        System.out.println("-----------" + cityName);
        System.out.println(municipalityNamesToCodeMap);
        String code = municipalityNamesToCodeMap.get(getCityNameUp(cityName));
        if (code == null){
            Log.d(TAG, "没有code");

        }else {
            Log.d(TAG,code +"有了");
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JsonNode jsonQuery = objectMapper.readTree(context.getResources().openRawResource(R.raw.populationdata2022));
                    ((ObjectNode) jsonQuery.findValue("query").get(1).get("selection")).putArray("values").add(code);
                    HttpURLConnection con = connectToAPIAndSendPostRequest(objectMapper, jsonQuery);
                    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                        StringBuilder response = new StringBuilder();
                        String responseLine;
                        while ((responseLine = bufferedReader.readLine()) != null) {
                            response.append(responseLine.trim());
                        }
                        JsonNode municipalityData = objectMapper.readTree(response.toString());
                        JsonNode populations = null;
                        String year = null;
                        for (JsonNode node : municipalityData.get("dimension").get("Vuosi").get("category").get("label")) {
                            year = node.asText();
                        }
                        populations = municipalityData.get("value");

                        ArrayList<PopulationData> populationData = new ArrayList<>();
                        for (int i = 0; i < populations.size(); i++) {
                            Integer population = populations.get(i).asInt();
                            populationData.add(new PopulationData(Integer.parseInt(year), population));
                        }
                        callback.onSuccess(populationData);
                    }
                } catch (IOException ex) {
                    Log.e(TAG, "Error fetching population data", ex);
                    // Handle error case
                }
            }
        });

        thread.start();
    }
    public String getCityNameUp(String name){
        //从索引 0 到 1（不包括 1）变成大写
        name = name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
        return name;

    }


    private static HashMap<String, String> createMunicipalityNamesToCodeMap(JsonNode areas){
        //将jsonnode中的数据全部储存在hashmap中
        JsonNode codes = null;
        JsonNode names = null;
        for (JsonNode node: areas.findValue("variables")){
            if (node.findValue("text").asText().equals("Area")){
                codes = node.findValue("values");
                names = node.findValue("valueTexts");
            }
        }
        HashMap<String,String> municilityNamesToCodeMap = new HashMap<>();
        for (int i = 0; i< names.size();i++){
            String name = names.get(i).asText();
            String code = codes.get(i).asText();
            municilityNamesToCodeMap.put(name,code);
        }

        return municilityNamesToCodeMap;

    }
    private static HttpURLConnection connectToAPIAndSendPostRequest(ObjectMapper objectMapper, JsonNode jsonQuery) throws IOException {
        //实现网络连接
        URL url = new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/en/StatFin/synt/statfin_synt_pxt_12dy.px");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        try (OutputStream os = con.getOutputStream()) {

            byte[] input = objectMapper.writeValueAsBytes(jsonQuery);
            os.write(input, 0, input.length);
        }
        return con;

    }

    private static JsonNode readAreaDataFromTheAPIURL(ObjectMapper objectMapper){
        //这个方法将json转变为jsonnode，用于解析和操作json数据
        JsonNode areas = null;
        try{
            areas = objectMapper.readTree(new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/en/StatFin/synt/statfin_synt_pxt_12dy.px"));

        }catch (MalformedURLException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return areas;
    }
    public interface PopulationDataCallback{
        void onSuccess(ArrayList<PopulationData> populationData);
    }

}
