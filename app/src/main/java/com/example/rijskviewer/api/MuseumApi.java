package com.example.rijskviewer.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rijskviewer.Interfaces.VolleyCallback;
import com.example.rijskviewer.models.ArtWork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MuseumApi {
    private final static String TOKEN = "MvDNbZD9";
    private final static String NB_IMAGE = "100";//Only 10 by 10

    public List<ArtWork> readFromJson(final VolleyCallback callback, Context applicationContext) {
        List<ArtWork> artWorkList = new ArrayList<>();
//        https://www.rijksmuseum.nl/api/en/collection?key=MvDNbZD9&ps=100&format=json&principalMaker=George%20Hendrik%20Breitner
        String token = "MvDNbZD9";
        int nbImage = 100;
        String name = "George%20Hendrik%20Breitner";
        String artist_url = "https://www.rijksmuseum.nl/api/en/collection?key=" + token + "&ps=" + nbImage + "&format=json&principalMaker=" + name;

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, artist_url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
//                                textView.setText("Response: " + response.toString());
                            callback.onSuccess(response);
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("Erreur lors de la récupération de l'url : " + error);
                        }
                    });

            Volley.newRequestQueue(applicationContext).add(jsonObjectRequest);
            // TODO: Faire un  callback correct
            Thread.sleep(1000);
        }catch(Exception e){
            System.out.println("Erreur : " + e);
        }

//        if(urlConnection != null) {
//
//        }
//        else{
//            System.out.println("Image non récupéré");
//        }
        System.out.println("my artWorkList2 : " + artWorkList);
        return artWorkList;
    }

    public List<ArtWork> getArtist(List<ArtWork> artWorkList, JSONObject response){
        System.out.println("getArtist");
        try {
            if(response.getInt("count") != 0){
                //url = jres.getJSONArray("artObjects").getJSONObject(0).getJSONObject("webImage").getString("url");
                //System.out.println(url);
                //System.out.println(jres.getJSONArray("artObjects").getJSONObject(64).get("webImage").equals(null));
                JSONArray artList = response.getJSONArray("artObjects");
                //System.out.println(artList.toString());
                System.out.println("***"+artList.length());
                for(int j = 0, b = 0; j < artList.length(); j++) {
                    JSONObject art = artList.getJSONObject(j);
                    //System.out.println(art.toString());
                    if(!art.get("webImage").equals(null)) {
                        String[] tab = new String[3];
                        tab = art.get("longTitle").toString().split(",");
                        //System.out.println(tab[tab.length-1]);
                        artWorkList.add(new ArtWork(art.getString("principalOrFirstMaker"), art.getString("title"), tab[tab.length-1], art.getJSONObject("webImage").getString("url"), b++));
                    } else {
                        System.out.println("Pas d'url");
                    }
                }
            } else {
                System.out.println(response.getInt("count"));
                System.out.println("Pas d'image");
            }
        } catch (JSONException e) {
            System.out.println("Erreur lors du parsing JSON" + e);
        }

        System.out.println("my artWorkList1 : " + artWorkList);
        return artWorkList;
    }
}