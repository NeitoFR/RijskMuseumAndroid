package com.example.rijskviewer;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.LinkedHashMap;
import java.util.Map;

public class TestTaMere {

//    public static void getString(
//            int method,
//            String url,
//            final Map<String, String> headers,
//            Response.Listener<String> listener,
//            Response.ErrorListener errorListener,
//            boolean putInCache)
//    {
//        System.out.println("getJSONObject url: " + url);
//
//        StringRequest jsonObjReq = new StringRequest(method, url, listener, errorListener) {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                if (headers == null) {
//                    return new LinkedHashMap<>(0);
//                } else {
//                    return headers;
//                }
//            }
//        };
//
//        jsonObjReq.setShouldCache(putInCache);
//        addRequestPolicy(jsonObjReq);
//
//        // Adding request to request queue
//        MainActivity.addToRequestQueue(jsonObjReq, "req_object_" + url.hashCode());
//    }
//
//    public static void addRequestPolicy(Request<String> request)
//    {
//        request.setRetryPolicy(
//                new DefaultRetryPolicy(
//                        10000, // 10 000
//                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
//                )
//        );
//    }
//

}
