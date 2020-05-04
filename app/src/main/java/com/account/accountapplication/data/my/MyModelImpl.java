package com.account.accountapplication.data.my;


import android.os.AsyncTask;
import android.os.Handler;

import java.util.List;

public class MyModelImpl implements MyModel {

    private UserRepository userRepository;

    public MyModelImpl() {
        this.userRepository = UserRepository.getInstance();
    }

    @Override
    public void getUserInfo(final FinishListener finishListener) {
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                new UserInfoAsyncTask(userRepository, finishListener).execute();
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
            finishListener.getUserInfoFinished(user);
        }
    }
}
