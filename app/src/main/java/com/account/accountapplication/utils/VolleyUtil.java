package com.account.accountapplication.utils;


import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class VolleyUtil {
    private static VolleyUtil volleyUtil;
    private RequestQueue requestQueue;

    private VolleyUtil(Context context) {
        requestQueue = Volley.newRequestQueue(context);
    }

    /**
     * 单例模式
     *
     * @param context
     * @return
     */
    public static VolleyUtil getInstance(Context context) {
        if (volleyUtil == null) {
            volleyUtil = new VolleyUtil(context);
        }
        return volleyUtil;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    /**
     * @param url           : get请求url
     * @param listener      : 请求监听
     * @param errorListener : 请求失败监听
     */
    public void httpGetRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, listener, errorListener);
        if (requestQueue == null) {
            throw new RuntimeException("requestQueue未实例化");
        }
        requestQueue.add(stringRequest);
    }

    /**
     * @param url           : post请求url
     * @param params        : 请求参数
     * @param listener      : 请求监听
     * @param errorListener : 请求失败监听
     */
    public void httpPostRequest(String url, final Map<String, String> params, final String body, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, listener, errorListener) {

            @Override
            public byte[] getBody() throws AuthFailureError {
                return body.getBytes();
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                if(params.isEmpty()){
                    Map<String, String> head = new HashMap<>();
                    head.put("content-type", "application/json;charset=utf-8");
                    head.put("Accept", "application/json;charset=UTF-8");
                    return head;
                }else{
                    return params;
                }
            }
        };
        requestQueue.add(stringRequest);
    }

    public void httpPutRequest(String url, final Map<String, String> params, Response.Listener<String> listener,
                               Response.ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.PUT, url, listener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void httpDeleteRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        StringRequest stringRequest = new StringRequest(Request.Method.DELETE, url, listener, errorListener);
        requestQueue.add(stringRequest);
    }
}
