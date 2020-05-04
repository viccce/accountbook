package com.account.accountapplication.data.login;

import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;

import com.account.accountapplication.data.my.User;
import com.account.accountapplication.utils.Constant;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class RegisterModelImpl implements RegisterModel {
    @Override
    public void register(final String username, final String password, final String email, final String mobile, final OnRegisterFinishedListener listener) {
        new Handler().post(new Runnable() {
            @Override public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(username)){
                    listener.onUsernameError();//model层里面回调listener
                    error = true;
                }
                if (TextUtils.isEmpty(password)){
                    listener.onPasswordError();
                    error = true;
                }
                if (TextUtils.isEmpty(email)){
                    listener.onEmailError();
                    error = true;
                }
                if (!error){
                    String registerUrlStr = Constant.URL_SAVE_USER;
                    User user = new User();
                    user.setNickname(username);
                    user.setPassword(password);
                    user.setMobile(mobile);
                    user.setEmail(email);
                    user.setUrl(registerUrlStr);
                    user.setActionType("create");
                    new MyAsyncTask(listener).execute(user);
                }
            }
        });
    }

    /**
     * AsyncTask类的三个泛型参数：
     * （1）Param 在执行AsyncTask是需要传入的参数，可用于后台任务中使用
     * （2）后台任务执行过程中，如果需要在UI上先是当前任务进度，则使用这里指定的泛型作为进度单位
     * （3）任务执行完毕后，如果需要对结果进行返回，则这里指定返回的数据类型
     */
    public static class MyAsyncTask extends AsyncTask<User, Integer, String> {

        @Override
        protected void onPreExecute() {
            Log.w("WangJ", "task onPreExecute()");
        }

        private OnRegisterFinishedListener listener;

        public MyAsyncTask(OnRegisterFinishedListener listener) {
            this.listener = listener;
        }

        /**
         * @param params 这里的params是一个数组，即AsyncTask在激活运行是调用execute()方法传入的参数
         */
        @Override
        protected String doInBackground(User... params) {
            Log.w("WangJ", "task doInBackground()");
            HttpURLConnection connection = null;
            StringBuilder response = new StringBuilder();
            try {
                User user= params[0];
                URL url = new URL(user.getUrl()); // 声明一个URL,注意如果用百度首页实验，请使用https开头，否则获取不到返回报文
                
                Gson gson = new Gson();
                String body = gson.toJson(user);
                connection = (HttpURLConnection) url.openConnection(); // 打开该URL连接
                connection.setRequestMethod("POST"); // 设置请求方法，“POST或GET”，我们这里用GET，在说到POST的时候再用POST
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                connection.connect();
                connection.setConnectTimeout(80000); // 设置连接建立的超时时间
                connection.setReadTimeout(80000); // 设置网络报文收发超时时间

                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"));
                writer.write(body);
                writer.close();

                InputStream in = connection.getInputStream();  // 通过连接的输入流获取下发报文，然后就是Java的流处理
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response.toString(); // 这里返回的结果就作为onPostExecute方法的入参
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            listener.onSuccess();
        }
    }
}
