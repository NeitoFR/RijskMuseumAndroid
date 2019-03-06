package com.example.rijksmuseum.api;

public class MuseumApi {
//    private final static String token = "MvDNbZD9";
//    private final static String nbImage = "100";//Only 10 by 10
//    private static ArrayList<Oeuvre> listOeuvres = new ArrayList<Oeuvre>();
//
//    public static void main(String[] args) {
//        getListOeuvres();
//    }
//
//    private static HttpURLConnection initConnection(String s) {
//        HttpURLConnection c = null;
//
//        try {
//            URL url = new URL(s);
//            c = (HttpURLConnection) url.openConnection();
//            int responseCode = c.getResponseCode();
//
//            System.out.println("status : "+ responseCode);
//            if(responseCode != 200 && responseCode != 204)
//            {
//                c = null;
//                System.out.println("Echec");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return c;
//    }
//
//    public static ArrayList<Oeuvre> getListOeuvres() {
//        String url = museumAPI.class.getClassLoader().getResource("Hearthstone.jpg").toString();
//        HttpURLConnection c =  initConnection("https://www.rijksmuseum.nl/api/en/collection?key="+token+"&ps="+nbImage+"&format=json&principalMaker=Rembrandt%20van%20Rijn");
//
//        if(c != null) {
//            try {
//                c.connect();
//                InputStream in = c.getInputStream();
//                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
//                StringBuilder res = new StringBuilder();
//                String line;
//
//                while((line = reader.readLine()) != null) {
//                    res.append(line+"\r");
//                }
//
//                JSONObject jres = new JSONObject(res.toString());
//                if(jres.getInt("count") != 0){
//                    //url = jres.getJSONArray("artObjects").getJSONObject(0).getJSONObject("webImage").getString("url");
//                    //System.out.println(url);
//                    //System.out.println(jres.getJSONArray("artObjects").getJSONObject(64).get("webImage").equals(null));
//                    JSONArray artList = jres.getJSONArray("artObjects");
//                    //System.out.println(artList.toString());
//                    System.out.println("***"+artList.length());
//                    for(int j = 0, b = 0; j < artList.length(); j++) {
//                        JSONObject art = artList.getJSONObject(j);
//                        //System.out.println(art.toString());
//                        if(!art.get("webImage").equals(null)){
//                            String[] tab = new String[3];
//                            tab = art.get("longTitle").toString().split(",");
//                            //System.out.println(tab[tab.length-1]);
//                            listOeuvres.add(new Oeuvre(art.getString("principalOrFirstMaker"), art.getString("title"), tab[tab.length-1], art.getJSONObject("webImage").getString("url"), b++));
//                        }
//
//                        else
//                        {
//                            System.out.println("Pas d'url");
//
//                        }
//
//                        //System.out.println("Image : "+j);
//                    }
//                }
//                else {
//                    System.out.println(jres.getInt("count"));
//                    System.out.println("Pas d'image");
//                }
//            } catch (IOException e) {e.printStackTrace();}
//        }
//        else{
//            System.out.println("Image non récupéré");
//        }
//
//        return listOeuvres;
//    }
}