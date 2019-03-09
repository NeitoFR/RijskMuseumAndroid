package com.example.rijskviewer.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rijskviewer.Interfaces.VolleyCallback;
import com.example.rijskviewer.beans.ArtWork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MuseumApi {
    private final static String TOKEN = "MvDNbZD9";
    private final static String NB_IMAGE = "100";//Only 10 by 10

    /**
     * Lit une url qui renvoie un format JSON.
     * @param callback
     * @param applicationContext
     * @param artistName
     * @return la liste des oeuvres pour un artiste provenant de l'url.
     */
    public List<ArtWork> readFromJson(final VolleyCallback callback, Context applicationContext, String artistName) {
        List<ArtWork> artWorkList = new ArrayList<>();
//        https://www.rijksmuseum.nl/api/en/collection?key=MvDNbZD9&ps=100&format=json&principalMaker=George%20Hendrik%20Breitner
        String artist_url = "https://www.rijksmuseum.nl/api/en/collection?key=" + TOKEN + "&ps=" + NB_IMAGE + "&format=json&principalMaker=" + artistName;

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, artist_url, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
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
            Thread.sleep(1250);
        }catch(Exception e){
            System.out.println("Erreur : " + e);
        }

        return artWorkList;
    }

    /**
     * Récupère la liste des oeuvres pour un artiste.
     * @param artistsList
     * @param response
     * @return la liste des oeuvres pour un artiste
     */
    public List<ArtWork> getArtWorkByArtist(List<ArtWork> artistsList, JSONObject response){
        try {
            if(response.getInt("count") != 0){
                JSONArray artList = response.getJSONArray("artObjects");
                System.out.println("***"+artList.length());
                for(int j = 0, b = 0; j < artList.length(); j++) {
                    JSONObject art = artList.getJSONObject(j);
                    if(!art.get("webImage").equals(null)) {
                        String[] tab = new String[3];
                        tab = art.get("longTitle").toString().split(",");
                        String date = tab[tab.length-1];

                        ArtWork artWork = new ArtWork();
                        artWork.setAuthor(art.getString("principalOrFirstMaker"));
                        artWork.setTitle(art.getString("title"));
                        artWork.setDate(date);
                        artWork.setUrl(art.getJSONObject("webImage").getString("url"));
                        artWork.setIndex(b++);

                        artistsList.add(artWork);
                    } else {
                        System.out.println("Pas d'url pour cette image");
                    }
                }
            } else {
                System.out.println(response.getInt("count"));
                System.out.println("Pas d'image");
            }
        } catch (JSONException e) {
            System.out.println("Erreur lors du parsing JSON" + e);
        }

        return artistsList;
    }

    /**
     * Récupère ls artistes.
     * @param artWorkList
     * @param response
     * @return la liste des artistes
     */
    public List<ArtWork> getArtists(List<ArtWork> artWorkList, JSONObject response){
        try {
            if(response.getInt("count") != 0){
                JSONObject firstFacets = response.getJSONObject("facets");

                if(firstFacets.getJSONObject("name").equals("principalMaker")) {
                    JSONArray facetsList = firstFacets.getJSONArray("facets");
                    System.out.println("***" + facetsList.length());

                    for(int j = 0, b = 0; j < facetsList.length(); j++) {
                        JSONObject art = facetsList.getJSONObject(j);
                        if(!art.get("webImage").equals(null)) {
                            ArtWork artWork = new ArtWork();
                            artWork.setAuthor(art.getString("key"));
                            artWork.setArtWorkNumber(art.getString("value"));

                            artWorkList.add(artWork);
                        } else {
                            System.out.println("Pas d'url pour cette image");
                        }
                    }
                }
            } else {
                System.out.println(response.getInt("count"));
                System.out.println("Pas d'image");
            }
        } catch (JSONException e) {
            System.out.println("Erreur lors du parsing JSON" + e);
        }

        return artWorkList;
    }
}