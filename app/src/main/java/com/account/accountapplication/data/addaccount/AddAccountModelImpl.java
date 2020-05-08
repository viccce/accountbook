package com.account.accountapplication.data.addaccount;

import android.os.Handler;

import com.account.accountapplication.account.AccountActivity;
import com.account.accountapplication.data.record.Account;
import com.account.accountapplication.utils.Constant;
import com.account.accountapplication.utils.VolleyUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.HashMap;

public class AddAccountModelImpl implements AddAccountModel {
    private VolleyUtil volleyUtil;

    public AddAccountModelImpl() {
        volleyUtil = VolleyUtil.getInstance(AccountActivity.getInstance());
    }

    @Override
    public void createAccount(final Account account, final FinishListener finishListener) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                String url = Constant.URL_ACCOUNT_SAVE;
                Gson gson = new Gson();
                String body = gson.toJson(account, Account.class);
                volleyUtil.httpPostRequest(url, new HashMap<String, String>(), body, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonObject result = new JsonParser().parse(response).getAsJsonObject();
                        if("0".equals(result.get("resultCode").getAsString())){
                            finishListener.createAccountSuccess();
                        }else{
                            finishListener.onError(result.get("message").getAsString());
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        finishListener.onError(error.getMessage());
                    }
                });
            }
        });
    }
}
