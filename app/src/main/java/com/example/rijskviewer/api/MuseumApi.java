package com.example.rijskviewer.api;

import com.example.rijskviewer.MainActivity;
import com.example.rijskviewer.R;
import com.example.rijskviewer.models.ArtWork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MuseumApi {
    private final static String TOKEN = "MvDNbZD9";
    private final static String NB_IMAGE = "100";//Only 10 by 10
    private static List<ArtWork> artWorkList = new ArrayList<>();

    private static HttpURLConnection initConnection(String s) {
        HttpURLConnection c = null;

        try {
            URL url = new URL(s);
            c = (HttpURLConnection) url.openConnection();
            int responseCode = c.getResponseCode();

            System.out.println("status : "+ responseCode);
            if(responseCode != 200 && responseCode != 204)
            {
                c = null;
                System.out.println("Echec");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return c;
    }

    public static List<ArtWork> getArtList() {
//        String url = R.drawable.earth;
        HttpURLConnection c =  initConnection("https://www.rijksmuseum.nl/api/en/collection?key=MvDNbZD9&ps=100&format=json&principalMaker=Rembrandt%20van%20Rijn");

        if(c != null) {
            try {
                c.connect();
                InputStream in = c.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder res = new StringBuilder();
                String line;

                while((line = reader.readLine()) != null) {
                    res.append(line+"\r");
                }

                JSONObject jres = new JSONObject(res.toString());
                if(jres.getInt("count") != 0){
                    //url = jres.getJSONArray("artObjects").getJSONObject(0).getJSONObject("webImage").getString("url");
                    //System.out.println(url);
                    //System.out.println(jres.getJSONArray("artObjects").getJSONObject(64).get("webImage").equals(null));
                    JSONArray artList = jres.getJSONArray("artObjects");
                    //System.out.println(artList.toString());
                    System.out.println("***"+artList.length());
                    for(int j = 0, b = 0; j < artList.length(); j++) {
                        JSONObject art = artList.getJSONObject(j);
                        //System.out.println(art.toString());
                        if(!art.get("webImage").equals(null)){
                            String[] tab = new String[3];
                            tab = art.get("longTitle").toString().split(",");
                            //System.out.println(tab[tab.length-1]);
                            artWorkList.add(new ArtWork(art.getString("principalOrFirstMaker"), art.getString("title"), tab[tab.length-1], art.getJSONObject("webImage").getString("url"), b++));
                        }

                        else
                        {
                            System.out.println("Pas d'url");

                        }

                        //System.out.println("Image : "+j);
                    }
                }
                else {
                    System.out.println(jres.getInt("count"));
                    System.out.println("Pas d'image");
                }
            } catch (IOException e) {e.printStackTrace();} catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Image non récupéré");
        }

        return artWorkList;
    }
}