package com.account.accountapplication.my;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.account.accountapplication.R;
import com.account.accountapplication.data.my.User;
import com.account.accountapplication.databinding.FragmentMyBinding;
import com.account.accountapplication.record.RecordActivity;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class MyFragment extends Fragment implements MyContract.MyView{

    private MyContract.MyPresenter myPresenter;
    private FragmentMyBinding myBinding;
    private User data;

    private static MyFragment myFragment;
    public MyFragment(){
        myFragment = this;
    }
    public static MyFragment getInstance(){
        return myFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        Context context = view.getContext();
        myBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_my, container, false);

        myPresenter = new MyPresenterImpl(this);
        myPresenter.getUserInfo();
        data = new User();
        myBinding.setData(data);
        return myBinding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RelativeLayout myInfo = (RelativeLayout) getActivity().findViewById(R.id.my_info);
        myInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordActivity recordActivity = (RecordActivity) getActivity();
                if(recordActivity != null){
                    recordActivity.editUserInfo();
                }
            }
        });

        Button logoutButton = (Button) getActivity().findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecordActivity recordActivity = (RecordActivity) getActivity();
                if(recordActivity != null){
                    recordActivity.logout();

                }
            }
        });
    }

    @Override
    public void displayUserInfo(User user) {
        data = user;
        myBinding.setData(user);
    }
}
