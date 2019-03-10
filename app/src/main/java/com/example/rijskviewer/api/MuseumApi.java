package com.example.rijskviewer.api;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rijskviewer.Interfaces.VolleyCallback;
import com.example.rijskviewer.beans.ArtWork;
import com.example.rijskviewer.beans.Artist;

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
     * Si artisteName renseigné, lit l'url pour un artiste sinon pour toute la collection.
     * @param callback
     * @param applicationContext
     * @param artistName
     * @return la liste des oeuvres pour un artiste provenant de l'url.
     */
    public List<ArtWork> readFromJson(final VolleyCallback callback, Context applicationContext, String artistName) {
        List<ArtWork> artWorkList = new ArrayList<>();
//        https://www.rijksmuseum.nl/api/en/collection?key=MvDNbZD9&ps=100&format=json&principalMaker=George%20Hendrik%20Breitner
        String artistUrl = "https://www.rijksmuseum.nl/api/en/collection?key=" + TOKEN + "&format=json";

        if(artistName != null){
            artistUrl += "&ps=" + NB_IMAGE + "principalMaker=" + artistName;
        }

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, artistUrl, null, new Response.Listener<JSONObject>() {
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
            Thread.sleep(1500);
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
                System.out.println("*** Nombres d'oeuvres pour l'artiste selectionné : " + artList.length() + " ***");

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
            System.out.println("Erreur lors du parsing JSON : " + e);
        }

        return artistsList;
    }

    /**
     * Récupère la liste des artistes.
     * @param artWorkList
     * @param response
     * @return la liste des artistes
     */
    public List<ArtWork> getArtists(List<ArtWork> artWorkList, JSONObject response){
        try {
            if(response.getInt("count") != 0){
                JSONArray firstFacets = response.getJSONArray("facets");

                for(int i = 0; i < firstFacets.length(); i++) {
                    JSONObject facets = firstFacets.getJSONObject(i);

                    if(facets.getString("name").equals("principalMaker")) {
                        JSONArray facetsList = facets.getJSONArray("facets");
                        System.out.println("*** Nombres d'artistes dans la liste getArtists : " + facetsList.length() + " ***");

                        for(int j = 0, b = 0; j < facetsList.length(); j++) {
                            JSONObject art = facetsList.getJSONObject(j);
                            ArtWork artWork = new ArtWork();

                            artWork.setAuthor(art.getString("key"));
                            artWork.setArtWorkNumber(art.getInt("value"));
                            artWork.setIndex(b++);

                            artWorkList.add(artWork);
                        }
                    }
                }
            } else {
                System.out.println(response.getInt("count"));
                System.out.println("Pas d'image");
            }
        } catch (JSONException e) {
            System.out.println("Erreur lors du parsing JSON : " + e);
        }

        return artWorkList;
    }
}