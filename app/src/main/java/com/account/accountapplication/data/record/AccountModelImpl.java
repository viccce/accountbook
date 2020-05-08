package com.account.accountapplication.data.record;

import android.os.Handler;

import com.account.accountapplication.record.RecordActivity;
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
        RecordActivity recordActivity = RecordActivity.getInstance();
        volleyUtil = VolleyUtil.getInstance(recordActivity);
    }

    @Override
    public void getAccountInfoList(final Long userId, final FinishListener listener) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                String url = Constant.URL_ACCOUNT_INFO_LIST;
                final Gson gson = new Gson();
                HashMap<String, Long> param = new HashMap<>();
                param.put("userId", userId);
                String body = gson.toJson(param);
                volleyUtil.httpPostRequest(url, new HashMap<String, String>(), body, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonObject res = new JsonParser().parse(response).getAsJsonObject();
                        String resultCode = res.get("resultCode").getAsString();
                        if("0".equals(resultCode)){
                            JsonArray result = res.get("result").getAsJsonArray();
                            List<Account> list = new ArrayList<>();
                            for (JsonElement account : result) {
                                Account acc = gson.fromJson(account, Account.class);
                                list.add(acc);
                            }
                            listener.getAccountInfoListSuccess(list);
                        }else{
                            listener.onError(res.get("message").toString());
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

    @Override
    public void deleteAccount(final Account account, final FinishListener listener) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                String url = Constant.URL_ACCOUNT_DELETE;
                final Gson gson = new Gson();
                HashMap<String, Long> param = new HashMap<>();
                param.put("accountId", account.getAccountId());
                String body = gson.toJson(param);
                volleyUtil.httpPostRequest(url, new HashMap<String, String>(), body, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonObject res = new JsonParser().parse(response).getAsJsonObject();
                        String resultCode = res.get("resultCode").getAsString();
                        if("0".equals(resultCode)){
                            listener.deleteAccountSuccess();
                        }else{
                            listener.onError(res.get("message").toString());
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
