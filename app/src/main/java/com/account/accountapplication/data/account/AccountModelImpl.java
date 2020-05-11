package com.account.accountapplication.data.account;

import android.os.Handler;

import com.account.accountapplication.account.AccountActivity;
import com.account.accountapplication.data.record.Account;
import com.account.accountapplication.data.record.AccountLine;
import com.account.accountapplication.utils.Constant;
import com.account.accountapplication.utils.VolleyUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccountModelImpl implements AccountModel {
    
    private VolleyUtil volleyUtil;
    
    public AccountModelImpl(){
        volleyUtil = VolleyUtil.getInstance(AccountActivity.getInstance());
    }

    @Override
    public void getAccountLine(final Account account, final AccountModel.FinishListener listener) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                String url = Constant.URL_ACCOUNT_DETAIL;
                final Gson gson = new Gson();
                String body = gson.toJson(account, Account.class);
                volleyUtil.httpPostRequest(url, new HashMap<String, String>(), body, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonObject res = new JsonParser().parse(response).getAsJsonObject();
                        String resultCode = res.get("resultCode").getAsString();
                        if("0".equals(resultCode)){
                            JsonObject account = res.get("result").getAsJsonObject();
                            JsonArray detailList = account.get("detailList").getAsJsonArray();
                            List<AccountLine> list = new ArrayList<>();
                            for(JsonElement obj : detailList){
                                AccountLine line = gson.fromJson(obj, AccountLine.class);
                                list.add(line);
                            }
                            listener.getAccountLineFinished(list);
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
