package com.account.accountapplication.data.login;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.account.accountapplication.MainActivity;
import com.account.accountapplication.login.Constant;
import com.account.accountapplication.record.recordActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


/**
 * Class Note:延时模拟登陆（2s），如果名字或者密码为空则登陆失败，否则登陆成功
 */
public class LoginModelImpl implements LoginModel {


    @Override
    public void login(final String username, final String password, final OnLoginFinishedListener listener) {


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
                if (!error){
                    String loginUrlStr = Constant.URL_Login + "?name=" + username + "&password=" + password;
                    new MyAsyncTask().execute(loginUrlStr);

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
    public static class MyAsyncTask extends AsyncTask<String, Integer, String> {

        @Override
        protected void onPreExecute() {
            Log.w("WangJ", "task onPreExecute()");
        }

        /**
         * @param params 这里的params是一个数组，即AsyncTask在激活运行是调用execute()方法传入的参数
         */
        @Override
        protected String doInBackground(String... params) {
            Log.w("WangJ", "task doInBackground()");
            HttpURLConnection connection = null;
            StringBuilder response = new StringBuilder();
            try {
                URL url = new URL(params[0]); // 声明一个URL,注意如果用百度首页实验，请使用https开头，否则获取不到返回报文
                connection = (HttpURLConnection) url.openConnection(); // 打开该URL连接
                connection.setRequestMethod("GET"); // 设置请求方法，“POST或GET”，我们这里用GET，在说到POST的时候再用POST
                connection.setConnectTimeout(80000); // 设置连接建立的超时时间
                connection.setReadTimeout(80000); // 设置网络报文收发超时时间
                InputStream in = connection.getInputStream();  // 通过连接的输入流获取下发报文，然后就是Java的流处理
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.w("WangJ", response.toString());

            return response.toString(); // 这里返回的结果就作为onPostExecute方法的入参
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // 如果在doInBackground方法，那么就会立刻执行本方法
            // 本方法在UI线程中执行，可以更新UI元素，典型的就是更新进度条进度，一般是在下载时候使用
        }

        /**
         * 运行在UI线程中，所以可以直接操作UI元素
         * @param s
         */
        @Override
        protected void onPostExecute(String s) {
            Log.w("WangJ", "task onPostExecute()");
            if(s.equals("code:200")){
                Intent intent = new Intent();
                intent.setClass(MainActivity.getMainActivity(), recordActivity.class);
                MainActivity.getMainActivity().startActivity(intent);
            }
            if(s.equals("code:100")){
                Toast.makeText(MainActivity.getMainActivity(), "账号或密码输入错误！", Toast.LENGTH_SHORT).show();
            }

        }


    }

}
