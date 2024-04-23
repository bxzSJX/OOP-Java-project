package com.example.oopandroidapi;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class DrivingTestDataRetriever {
    //https://trafi2.stat.fi:443/PXWeb/api/v1/en/TraFi/Kuljettajantutkinnot/020_tutk_tau_102.px
    static ObjectMapper objectMapper = new ObjectMapper();
    static HashMap<String, String> municipalityNamesToCodesMap = null;
    public static void getMunicipalityCodeMap() {
        if (municipalityNamesToCodesMap == null) {
            JsonNode areas = readAreaDataFromTheAPIURL(objectMapper);
            municipalityNamesToCodesMap = createMunicipalityNamesToCodesMap(areas);
        }
    }
    public ArrayList<DrivingTestData> getDrivingTest(Context context,String cityName) {
        String code = municipalityNamesToCodesMap.get(cityName);

        try {
            JsonNode jsonQuery = objectMapper.readTree(context.getResources().openRawResource(R.raw.employmentdata2022));
            ((ObjectNode) jsonQuery.findValue("query").get(0).get("selection")).putArray("values").add(code);
            HttpURLConnection con = connectToAPIAndSendPostRequest(objectMapper, jsonQuery);
            System.out.println(con.getResponseCode());
            try (BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                JsonNode DrivingTestNode = objectMapper.readTree(response.toString());
                Log.d(TAG,DrivingTestNode.toString());
                int year = 0;
            }
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    private static HttpURLConnection connectToAPIAndSendPostRequest(ObjectMapper objectMapper, JsonNode jsonQuery)
            throws MalformedURLException, IOException, ProtocolException, JsonProcessingException {
        URL url = new URL("https://trafi2.stat.fi:443/PXWeb/api/v1/en/TraFi/Kuljettajantutkinnot/020_tutk_tau_102.px");
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
            if (node.findValue("text").asText().equals("Ajovarma service point")) {
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
            areas = objectMapper.readTree(new URL("https://trafi2.stat.fi:443/PXWeb/api/v1/en/TraFi/Kuljettajantutkinnot/020_tutk_tau_102.px"));


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return areas;
    }

}
