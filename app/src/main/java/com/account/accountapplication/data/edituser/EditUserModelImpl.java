package com.account.accountapplication.data.edituser;

import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;

import com.account.accountapplication.data.my.User;
import com.account.accountapplication.data.my.UserRepository;
import com.account.accountapplication.edituser.EditUserActivity;
import com.account.accountapplication.utils.Constant;
import com.account.accountapplication.utils.VolleyUtil;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EditUserModelImpl implements EditUserModel {

    private UserRepository userRepository;

    private VolleyUtil volleyUtil;

    public EditUserModelImpl() {
        this.userRepository = UserRepository.getInstance();
        EditUserActivity editUserActivity = EditUserActivity.getEditUserActivity();
        volleyUtil = VolleyUtil.getInstance(editUserActivity);
    }

    @Override
    public void saveRemoteUserInfo(final User user, final FinishListener finishListener) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(user.getNickname())) {
                    finishListener.onError("用户名为空");
                    return;
                }
                if (TextUtils.isEmpty(user.getMobile())) {
                    finishListener.onError("手机号为空");
                    return;
                }
                if (TextUtils.isEmpty(user.getEmail())) {
                    finishListener.onError("邮箱为空");
                    return;
                }
                user.setActionType("update");
                Map<String, String> head = new HashMap<>();
                head.put("content-type", "application/json;charset=utf-8");
                Gson gson = new Gson();
                String body = gson.toJson(user);
                volleyUtil.httpPostRequest(Constant.URL_SAVE_USER, head, body, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        finishListener.remoteSaveSuccess(user);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        finishListener.onError("保存失败");
                    }
                });
            }
        });
    }

    @Override
    public void getUserInfo(final FinishListener listener) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                new UserInfoAsyncTask(userRepository, listener).execute();
            }
        });
    }

    @Override
    public void updateUserInfo(final User user, final FinishListener listener) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                new UpdateUserInfoAsyncTask(userRepository, listener).execute(user);
            }
        });
    }

    static class UserInfoAsyncTask extends AsyncTask<Void, Void, User> {

        private UserRepository userRepository;
        private FinishListener finishListener;

        UserInfoAsyncTask(UserRepository userRepository, FinishListener finishListener) {
            this.userRepository = userRepository;
            this.finishListener = finishListener;
        }

        @Override
        protected User doInBackground(Void... voids) {
            User user = new User();
            try {
                List<User> users = userRepository.getUser();
                user = users.get(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return user;
        }

        @Override
        protected void onPostExecute(User user) {
            finishListener.getUserInfoSuccess(user);
        }
    }

    static class UpdateUserInfoAsyncTask extends AsyncTask<User, Void, User> {

        private UserRepository userRepository;
        private FinishListener finishListener;

        UpdateUserInfoAsyncTask(UserRepository userRepository, FinishListener finishListener) {
            this.userRepository = userRepository;
            this.finishListener = finishListener;
        }

        @Override
        protected User doInBackground(User... users) {
            if (users != null && users[0] != null) {
                userRepository.updateUser(users[0]);
                return users[0];
            }else{
                return null;
            }
        }

        @Override
        protected void onPostExecute(User user) {
            finishListener.updateUserInfoSuccess(user);
        }
    }
}
