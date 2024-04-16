package com.example.oopandroidapi;

import android.content.Context;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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
    public ArrayList<PopulationData> getPopulation(Context context, String cityName){
        String code = municipalityNamesToCodeMap.get(cityName);
        ArrayList<PopulationData> populationDataArrayList = new ArrayList<>();
        try{
            JsonNode jsonQuery = objectMapper.readTree(context.getResources().openRawResource(R.raw.populationdata2022));
            ((ObjectNode) jsonQuery.findValue("query").get(1).get("selection")).putArray("values").add(code);
            HttpURLConnection con = connectToAPIAndSendPostRequest(objectMapper,jsonQuery, new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/en/StatFin/synt/statfin_synt_pxt_12dy.px"));
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"))){
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while((responseLine = br.readLine())!= null){
                    response.append(responseLine.trim());
                }
                JsonNode municipalityData = objectMapper.readTree(response.toString());
                JsonNode populationData = null;
                populationData = municipalityData.get("values");

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return populationDataArrayList;
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
    private static HttpURLConnection connectToAPIAndSendPostRequest(ObjectMapper objectMapper, JsonNode jsonQuery, URL url) throws IOException {
        //实现网络连接
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

}
