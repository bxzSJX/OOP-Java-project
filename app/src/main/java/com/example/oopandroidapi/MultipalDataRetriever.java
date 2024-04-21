package com.example.oopandroidapi;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
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

    static ObjectMapper objectMapper = new ObjectMapper();

    static HashMap<String, String> municipalityNamesToCodesMap = null;
    public static void getMunicipalityCodeMap() {
        if (municipalityNamesToCodesMap == null) {
            JsonNode areas = readAreaDataFromTheAPIURL(objectMapper);
            municipalityNamesToCodesMap = createMunicipalityNamesToCodesMap(areas);
        }
    }

    public ArrayList<PopulationData> getPopulation(Context context, String municipalityName) {
        String name  = getCitynameUp(municipalityName);
        String code = municipalityNamesToCodesMap.get(name);
        Log.d(TAG,code);
        try {
            JsonNode jsonQuery = objectMapper.readTree(context.getResources().openRawResource(R.raw.populationdata2022));
            ((ObjectNode) jsonQuery.findValue("query").get(0).get("selection")).putArray("values").add(code);
            HttpURLConnection con = connectToAPIAndSendPostRequest(objectMapper, jsonQuery);
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                JsonNode municipalityData = objectMapper.readTree(response.toString());
                ArrayList<String> years = new ArrayList<>();
                JsonNode populations = null;
                for (JsonNode node : municipalityData.get("dimension").get("Vuosi")
                        .get("category").get("label")) {
                    years.add(node.asText());
                }
                populations = municipalityData.get("value");
                ArrayList<PopulationData> populationData = new ArrayList<>();
                for (int i = 0; i < populations.size(); i++) {
                    Integer population = populations.get(i).asInt();
                    populationData.add(new PopulationData(Integer.parseInt(years.get(i)), population));
                }
                System.out.println(municipalityName);
                System.out.println("==========================");
                for (PopulationData data : populationData) {
                    System.out.print(data.getYear() + ": " + data.getPopulation() + " ");
                    for (int i = 0; i < data.getPopulation() / 10000; i++) {
                        System.out.print("*");
                    }
                    System.out.println();
                }
                return populationData;
            }catch (FileNotFoundException ex){
                Log.e(TAG,"file not found");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
    public String getCitynameUp(String name){
        name = name.substring(0,1).toUpperCase()+name.substring(1).toLowerCase();
        return name;
    }

    private static HttpURLConnection connectToAPIAndSendPostRequest(ObjectMapper objectMapper, JsonNode jsonQuery)
            throws MalformedURLException, IOException, ProtocolException, JsonProcessingException {
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



    private static HashMap<String, String> createMunicipalityNamesToCodesMap(JsonNode areas) {
        JsonNode codes = null;
        JsonNode names = null;
        for (JsonNode node : areas.findValue("variables")) {
            if (node.findValue("text").asText().equals("Area")) {
                codes = node.findValue("values");
                names = node.findValue("valueTexts");
            }
        }
        HashMap<String, String> municipalityNamesToCodesMap = new HashMap<>();
        for (int i = 0; i < names.size(); i++) {
            String name = names.get(i).asText();
            String code = codes.get(i).asText();
            municipalityNamesToCodesMap.put(name, code);

        }
        return municipalityNamesToCodesMap;
    }

    private static JsonNode readAreaDataFromTheAPIURL(ObjectMapper objectMapper) {
        JsonNode areas = null;
        try {
            areas = objectMapper.readTree(new URL("https://pxdata.stat.fi:443/PxWeb/api/v1/en/StatFin/synt/statfin_synt_pxt_12dy.px"));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return areas;
    }


}
