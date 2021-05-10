package com.example.myapplication;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class OffloadingConnection{
    private static final int MY_SOCKET_TIMEOUT_MS = Integer.MAX_VALUE;
    Context context;
    OffloadingConnection(Context context){
        this.context=context;
    }
    TextView textView;
    static String output;
    public String makeJsonObjectRequest(String code, String n)
    {
        String url = "https://e8bfef353667.ngrok.io/run";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {

                        try {
                            JSONObject respObj = new JSONObject(response);
//                            textView.setText(respObj.getString("output"));
                                output=respObj.getString("output");
                                NQueenProblem nqueen=new NQueenProblem();
                                nqueen.printSolution(output,context);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(context, "Done", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("sourceCode", code);
                params.put("language", "java");
                return params;
            }
        };
        Volley.newRequestQueue(context).add(stringRequest);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                MY_SOCKET_TIMEOUT_MS,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        return output;
    }

}