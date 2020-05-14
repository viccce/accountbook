package com.account.accountapplication.data.accountuser;

import android.os.Handler;

import com.account.accountapplication.accountuser.AccountUserActivity;
import com.account.accountapplication.data.my.User;
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
import java.util.Map;

public class AccountUserModelImpl implements AccountUserModel {

    VolleyUtil volleyUtil;

    public AccountUserModelImpl() {
        AccountUserActivity accountUserActivity = AccountUserActivity.getInstance();
        volleyUtil = VolleyUtil.getInstance(accountUserActivity);
    }

    @Override
    public void deleteAccountUser(final User user, final AccountUserModel.FinishListener listener) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                user.setActionType("delete");
                String url = Constant.URL_ACCOUNT_USER_MODIFY;
                Gson gson = new Gson();
                String body = gson.toJson(user);
                volleyUtil.httpPostRequest(url, new HashMap<String, String>(), body, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonObject res = new JsonParser().parse(response).getAsJsonObject();
                        String resultCode = res.get("resultCode").getAsString();
                        if("0".equals(resultCode)){
                            listener.modifyAccountUserSuccess(user);
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

    @Override
    public void addAccountUser(final User user, final AccountUserModel.FinishListener listener) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                user.setActionType("create");
                String url = Constant.URL_ACCOUNT_USER_MODIFY;
                Gson gson = new Gson();
                String body = gson.toJson(user);
                volleyUtil.httpPostRequest(url, new HashMap<String, String>(), body, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonObject res = new JsonParser().parse(response).getAsJsonObject();
                        String resultCode = res.get("resultCode").getAsString();
                        if("0".equals(resultCode)){
                            listener.modifyAccountUserSuccess(user);
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

    @Override
    public void getAccountUserList(final long accountId, final AccountUserModel.FinishListener listener) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                Map<String, Object> map = new HashMap<>();
                map.put("accountId", accountId);
                String url = Constant.URL_ACCOUNT_USER_LIST;
                final Gson gson = new Gson();
                String body = gson.toJson(map);
                volleyUtil.httpPostRequest(url, new HashMap<String, String>(), body, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        JsonObject res = new JsonParser().parse(response).getAsJsonObject();
                        String resultCode = res.get("resultCode").getAsString();
                        if("0".equals(resultCode)){
                            List<User> list = new ArrayList<>();
                            JsonArray users = res.get("result").getAsJsonArray();
                            for (JsonElement user : users) {
                                list.add(gson.fromJson(user, User.class));
                            }
                            listener.getAccountUserListSuccess(list);
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

    @Override
    public void getUserList( final AccountUserModel.FinishListener listener) {
        Map<String, Object> map = new HashMap<>();
        String url = Constant.URL_USER_LIST;
        final Gson gson = new Gson();
        String body = gson.toJson(map);
        volleyUtil.httpPostRequest(url, new HashMap<String, String>(), body, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JsonObject res = new JsonParser().parse(response).getAsJsonObject();
                String resultCode = res.get("resultCode").getAsString();
                if("0".equals(resultCode)){
                    List<User> list = new ArrayList<>();
                    JsonArray users = res.get("result").getAsJsonArray();
                    for (JsonElement user : users) {
                        list.add(gson.fromJson(user, User.class));
                    }
                    listener.getUserListSuccess(list);
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
}
