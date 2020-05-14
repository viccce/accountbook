package com.account.accountapplication.data.addaccountline;

import android.os.Handler;

import com.account.accountapplication.account.AccountActivity;
import com.account.accountapplication.data.record.AccountLine;
import com.account.accountapplication.utils.Constant;
import com.account.accountapplication.utils.VolleyUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;

public class AddAccountLineModelImpl implements AddAccountLineModel {

    private VolleyUtil volleyUtil;

    public AddAccountLineModelImpl(){
        volleyUtil = VolleyUtil.getInstance(AccountActivity.getInstance());
    }

    @Override
    public void saveAccountLine(final AccountLine line, final AddAccountLineModel.FinishListener listener) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                String url = Constant.URL_ACCOUNT_LINE_SAVE;
                final Gson gson = new Gson();
                String body = gson.toJson(line);
                volleyUtil.httpPostRequest(url, new HashMap<String, String>(), body, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonObject res = new JsonParser().parse(response).getAsJsonObject();
                        String resultCode = res.get("resultCode").getAsString();
                        if("0".equals(resultCode)){
                            listener.saveSuccess();
                        }else{
                            listener.onError(res.get("message").getAsString());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error.getMessage());
                    }
                });
            }
        });
    }
}
