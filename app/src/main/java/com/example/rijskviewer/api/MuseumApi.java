package com.example.rijskviewer.api;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.rijskviewer.Interfaces.VolleyCallback;
import com.example.rijskviewer.R;
import com.example.rijskviewer.beans.ArtWork;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MuseumApi {
    private final static String TOKEN = "QKkH603N";
    private final static String NB_IMAGE = "100";//Only 10 by 10
    private static String ARTIST_URL = "https://www.rijksmuseum.nl/api/en/collection?key=" + TOKEN + "&format=json";

    /**
     * Lit une url qui renvoie un format JSON.
     * Si artisteName renseigné, lit l'url pour un artiste sinon pour toute la collection.
     * @param callback
     * @param applicationContext
     * @param artistName
     * @return la liste des oeuvres pour un artiste provenant de l'url.
     */
    public List<ArtWork> readFromJson(final VolleyCallback callback, final Context applicationContext, String artistName) {
        List<ArtWork> artWorkList = new ArrayList<>();
//        https://www.rijksmuseum.nl/api/en/collection?key=MvDNbZD9&ps=100&format=json&principalMaker=George%20Hendrik%20Breitner

        if(artistName != null){
            ARTIST_URL += "&ps=" + NB_IMAGE + "principalMaker=" + artistName;
        }

        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, ARTIST_URL, null, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            System.out.println("OnResponse");
                            callback.onSuccess(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            System.out.println("Erreur lors de la récupération de l'url : " + error);
                            //PopupWindow popupWindow = new PopupWindow(applicationContext);

                            final Dialog dialog = new Dialog(applicationContext);
                            dialog.setContentView(R.layout.network_error);

                            Toolbar artistDialToolbar = dialog.findViewById(R.id.network_error_dial_toolbar);
                            artistDialToolbar.setTitle("Activer le réseau");

                            Button artistDialBtnYes = dialog.findViewById(R.id.network_error_dial_btn_yes);
                            artistDialBtnYes.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                                    applicationContext.startActivity(intent);
                                }
                            });

                            Button artistDialBtnNo = dialog.findViewById(R.id.network_error_dial_btn_no);
                            artistDialBtnNo.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    dialog.dismiss();
                                }
                            });

                            dialog.show();

                            //Intent intent = new Intent(Settings.ACTION_WIRELESS_SETTINGS);
                            //applicationContext.startActivity(intent);

                            if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            } else if (error instanceof AuthFailureError) {
                                //TODO
                            } else if (error instanceof ServerError) {
                                //TODO
                            } else if (error instanceof NetworkError) {
                                //TODO
                            } else if (error instanceof ParseError) {
                                //TODO
                            }
                        }
                    });

            Volley.newRequestQueue(applicationContext).add(jsonObjectRequest);
            // TODO: Faire un  callback correct
            //Thread.sleep(2000);
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